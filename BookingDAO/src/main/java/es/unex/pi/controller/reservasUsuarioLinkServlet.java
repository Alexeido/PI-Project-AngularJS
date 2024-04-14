package es.unex.pi.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import es.unex.pi.dao.BookingDAO;
import es.unex.pi.dao.BookingsAccommodationsDAO;
import es.unex.pi.model.Accommodation;
import es.unex.pi.model.Booking;
import es.unex.pi.model.Property;
import es.unex.pi.model.User;

/**
 * Servlet implementation class reservasUsuarioLinkServlet
 */
public class reservasUsuarioLinkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public reservasUsuarioLinkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		BookingDAO bookingDao = new es.unex.pi.dao.JDBCBookingDAOImpl();	
		BookingsAccommodationsDAO accommodationsBookingsDao = new es.unex.pi.dao.JDBCBookingsAccommodationsDAOImpl();	
		bookingDao.setConnection(conn);
		accommodationsBookingsDao.setConnection(conn);
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");

		List<Booking> userBooks = bookingDao.getUserBooks(user.getId());
		List<Accommodation> userBookedAccommodations = new ArrayList<Accommodation>();

        for (Booking booking : userBooks) {
            Accommodation i = accommodationsBookingsDao.getOneByBooking(booking.getId());
            if (i == null) {
            	Property p= new Property();
            	p.setName("Error");
            	p.setCity(" Base de Datos, reserva sin habitaciones");
            	i=new Accommodation();
            	i.setPrice(-1);
            	i.setProp(p);
            }
            userBookedAccommodations.add(i);
        }
		
        request.setAttribute("userBooks", userBooks);
        request.setAttribute("userBookedAccommodations", userBookedAccommodations);
        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/reservasUsuario.jsp");
        view.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
