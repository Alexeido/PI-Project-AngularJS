package es.unex.pi.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import es.unex.pi.dao.AccommodationDAO;
import es.unex.pi.dao.BookingDAO;
import es.unex.pi.dao.BookingsAccommodationsDAO;
import es.unex.pi.model.Accommodation;
import es.unex.pi.model.Booking;
import es.unex.pi.model.BookingsAccommodations;
import es.unex.pi.model.User;

/**
 * Servlet implementation class ReservarHabitacionesServlet
 */
public class ReservarHabitacionesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ReservarHabitacionesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		BookingDAO bookingDao = new es.unex.pi.dao.JDBCBookingDAOImpl();
		BookingsAccommodationsDAO accommodationsBookingsDao = new es.unex.pi.dao.JDBCBookingsAccommodationsDAOImpl();
		AccommodationDAO accommodationDao = new es.unex.pi.dao.JDBCAccommodationDAOImpl();
		bookingDao.setConnection(conn);
		accommodationsBookingsDao.setConnection(conn);
	    accommodationDao.setConnection(conn);
	    

		HttpSession session=request.getSession();
		Booking book = new Booking();
	    User user = (User) session.getAttribute("user");
		ArrayList<BookingsAccommodations> carrito = (ArrayList<BookingsAccommodations>) session.getAttribute("carrito");
        
        book.setIdu(user.getId());
        book.setTotalPrice(Integer.parseInt(request.getParameter("precioTotal")));
		long idb =bookingDao.add(book);

        for (BookingsAccommodations booking : carrito) {
            booking.setIdb(idb); // Establecer el idb en cada elemento del carrito
            accommodationsBookingsDao.add(booking);
        }
        

		carrito = new ArrayList<>();
        session.setAttribute("carrito", carrito);
		response.sendRedirect("IndexLinkServlet.do");
	}

}
