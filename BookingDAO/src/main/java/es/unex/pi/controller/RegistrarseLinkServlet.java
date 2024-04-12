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
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import es.unex.pi.model.User;
/**
 * Servlet implementation class RegistrarseLinkServlet
 */
public class RegistrarseLinkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
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

		User user = new User();
		user.setEmail(email);
		user.setName(username);
		user.setPassword(password);
		user.setSurname(apellido);

		userDao.add(user);
		HttpSession session = request.getSession();
		session.setAttribute("user", user);
	
		response.sendRedirect("IndexLinkServlet.do");
	}

}
