package es.unex.pi.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import es.unex.pi.dao.AccommodationDAO;
import es.unex.pi.dao.BookingsAccommodationsDAO;
import es.unex.pi.model.Accommodation;
import es.unex.pi.model.BookingsAccommodations;

/**
 * Servlet implementation class reservasDetalleLinkServlet
 */
@WebServlet("/user/reservasDetalleLinkServlet.do")

public class reservasDetalleLinkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public reservasDetalleLinkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		AccommodationDAO accommodationDao = new es.unex.pi.dao.JDBCAccommodationDAOImpl();
		BookingsAccommodationsDAO accommodationsBookingsDao = new es.unex.pi.dao.JDBCBookingsAccommodationsDAOImpl();	
		long idb = Long.parseLong(request.getParameter("idb"));
		accommodationsBookingsDao.setConnection(conn);
	    accommodationDao.setConnection(conn);
	    
		List<BookingsAccommodations> carrito = accommodationsBookingsDao.getAllByBooking(idb);
		ArrayList<Accommodation> habitaciones =  new ArrayList<>();
        int sumaPrecios = 0;
        
        for (BookingsAccommodations booking : carrito) {
        	for(int i=0;i<booking.getNumAccommodations();i++) {
        		Accommodation hab=accommodationDao.get(booking.getIdacc());
        		habitaciones.add(hab);
			    sumaPrecios += hab.getPrice();
        	}	
        }
		request.setAttribute("habitaciones", habitaciones);
		request.setAttribute("sumaPrecios", sumaPrecios);
		request.setAttribute("idb", idb);
		
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/reservasDetalle.jsp");
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
