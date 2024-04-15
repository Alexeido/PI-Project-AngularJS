package es.unex.pi.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import es.unex.pi.dao.UserDAO;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import es.unex.pi.model.BookingsAccommodations;
import es.unex.pi.model.User;
/**
 * Servlet implementation class RegistrarseLinkServlet
 */
@WebServlet("/RegistrarseLinkServlet.do")
public class RegistrarseLinkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

    
	private String securePassword(String contraseña) {
	    // Comprueba si la contraseña tiene al menos 8 caracteres
	    if (contraseña.length() < 8) {
	        return "La contraseña debe tener al menos 8 caracteres.";
	    }
	    
	    // Comprueba si la contraseña contiene al menos una letra mayúscula
	    if (!contraseña.matches(".*[A-Z].*")) {
	        return "La contraseña debe contener al menos una letra mayúscula.";
	    }
	    
	    // Comprueba si la contraseña contiene al menos una letra minúscula
	    if (!contraseña.matches(".*[a-z].*")) {
	        return "La contraseña debe contener al menos una letra minúscula.";
	    }
	    
	    // Comprueba si la contraseña contiene al menos un dígito
	    if (!contraseña.matches(".*\\d.*")) {
	        return "La contraseña debe contener al menos un dígito.";
	    }
	    
	    // Comprueba si la contraseña contiene al menos un carácter especial
	    if (!contraseña.matches(".*[!@#$%^&*().].*")) {
	        return "La contraseña debe contener al menos un carácter especial.";
	    }
	    
	    // Si la contraseña pasa todas las comprobaciones, devuelve null (sin error)
	    return null;
	}

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegistrarseLinkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/registrarse.jsp");
		view.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		UserDAO userDao = new es.unex.pi.dao.JDBCUserDAOImpl();		
		userDao.setConnection(conn);
		String username = request.getParameter("name");
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String SPass = securePassword(password);
		if(SPass!=null) {
			request.setAttribute("error", SPass);
			doGet(request, response);
		}
		User user = new User();
		user.setEmail(email);
		user.setName(username);
		user.setPassword(password);
		user.setSurname(apellido);

		if(userDao.add(user)==-1){
			request.setAttribute("error", "Este email ya tiene cuenta en Booking");
			doGet(request, response);
		}
		
		HttpSession session = request.getSession();
		ArrayList<BookingsAccommodations> carrito = new ArrayList<>();
        session.setAttribute("carrito", carrito);
		session.setAttribute("user", user);
	
		response.sendRedirect("IndexLinkServlet.do");
	}

}
