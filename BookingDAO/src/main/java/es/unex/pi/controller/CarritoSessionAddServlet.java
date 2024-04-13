package es.unex.pi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;
import es.unex.pi.model.BookingsAccommodations;

/**
 * Servlet implementation class CarritoSessionAddServlet
 */
public class CarritoSessionAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CarritoSessionAddServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		ArrayList<BookingsAccommodations> carrito = (ArrayList<BookingsAccommodations>) session.getAttribute("carrito");

        // Si el carrito no existe en la sesión, debe iniciar sesión
        if (carrito == null) {
            response.sendRedirect("IniciarSesionLinkServlet.do");
            return;
        }

        long ida= Long.parseLong(request.getParameter("ida"));
        boolean encontrado = false;
        for (BookingsAccommodations booking : carrito) {
            if (booking.getIdacc() == ida) {
                // Si el alojamiento está en el carrito, incrementa numAccommodations
                booking.setNumAccommodations(booking.getNumAccommodations() + 1);
                encontrado = true;
    			logger.info("uno más al carritoOO");
                break;
            }
        }
        
        if(!encontrado) {
			BookingsAccommodations booking = new BookingsAccommodations();
	        booking.setIdacc(ida);
	        booking.setNumAccommodations(1);
	        carrito.add(booking);
			logger.info("Apartamento al carritoOO");
        }

        session.setAttribute("carrito", carrito);
        response.sendRedirect(request.getHeader("referer"));
	}

}
