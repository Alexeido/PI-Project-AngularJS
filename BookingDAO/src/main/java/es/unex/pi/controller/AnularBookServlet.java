package es.unex.pi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;

import es.unex.pi.dao.BookingDAO;
import es.unex.pi.dao.BookingsAccommodationsDAO;
import es.unex.pi.model.BookingsAccommodations;

/**
 * Servlet implementation class AnularBookServlet
 */
@WebServlet("/user/AnularBookServlet.do")

public class AnularBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AnularBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		BookingDAO bookingDao = new es.unex.pi.dao.JDBCBookingDAOImpl();
		bookingDao.setConnection(conn);
		
        long idb = Long.parseLong(request.getParameter("idb"));
        String idaParam = request.getParameter("ida");
        if(idaParam==null) {
            bookingDao.delete(idb);
        }
        else {
    		BookingsAccommodationsDAO accommodationsBookingsDao = new es.unex.pi.dao.JDBCBookingsAccommodationsDAOImpl();	
    		accommodationsBookingsDao.setConnection(conn);
            long ida = Long.parseLong(idaParam);
            BookingsAccommodations ba=accommodationsBookingsDao.get(idb, ida);
            if(ba.getNumAccommodations()>1) {
            	ba.setNumAccommodations(ba.getNumAccommodations()-1);
                accommodationsBookingsDao.update(ba, ba);
            }
            else {
                accommodationsBookingsDao.delete(idb, ida);
                if(accommodationsBookingsDao.getAllByBooking(idb).isEmpty()) {
                    bookingDao.delete(idb);
                }
            }
            bookingDao.updateTotalPrice(bookingDao.get(idb));
        }
        
        response.sendRedirect("reservasUsuarioLinkServlet.do");
	}

}
