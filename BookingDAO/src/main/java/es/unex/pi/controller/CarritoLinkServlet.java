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
import es.unex.pi.model.Accommodation;
import es.unex.pi.model.BookingsAccommodations;

/**
 * Servlet implementation class CarritoLinkServlet
 */
@WebServlet("/user/CarritoLinkServlet.do")
public class CarritoLinkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	/**
     * @see HttpServlet#HttpServlet()
     */
    public CarritoLinkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		AccommodationDAO accommodationDao = new es.unex.pi.dao.JDBCAccommodationDAOImpl();
		
	    accommodationDao.setConnection(conn);
	    

		HttpSession session=request.getSession();
		@SuppressWarnings("unchecked")
		ArrayList<BookingsAccommodations> carrito = (ArrayList<BookingsAccommodations>) session.getAttribute("carrito");
		
		
		// Si el carrito no existe en la sesión, debe iniciar sesión
        if (carrito == null) {
            response.sendRedirect("IniciarSesionLinkServlet.do");
            return;
        }
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
		
		
		
		
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/carrito.jsp");
		view.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
