package es.unex.pi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import es.unex.pi.model.BookingsAccommodations;

/**
 * Servlet implementation class CarritoSessionDelServlet
 */
public class CarritoSessionDelServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarritoSessionDelServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        @SuppressWarnings("unchecked")
		ArrayList<BookingsAccommodations> carrito = (ArrayList<BookingsAccommodations>) session.getAttribute("carrito");
        
        if (carrito == null) {
            response.sendRedirect("IniciarSesionLinkServlet.do");
            return;
        }

        long ida = Long.parseLong(request.getParameter("ida"));
        Iterator<BookingsAccommodations> iterator = carrito.iterator();
        while (iterator.hasNext()) {
            BookingsAccommodations booking = iterator.next();
            if (booking.getIdacc() == ida) {
                booking.setNumAccommodations(booking.getNumAccommodations() - 1);
                if (booking.getNumAccommodations() == 0) {
                    iterator.remove();
                }
                break;
            }
        }
        session.setAttribute("carrito", carrito);
        response.sendRedirect(request.getHeader("referer"));
    }


}
