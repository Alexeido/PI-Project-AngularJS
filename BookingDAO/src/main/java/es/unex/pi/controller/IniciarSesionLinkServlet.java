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
import java.util.logging.Logger;

import es.unex.pi.model.User;
import es.unex.pi.dao.UserDAO;
/**
 * Servlet implementation class IniciarSesionLinkServlet
 */

@WebServlet("/IniciarSesionLinkServlet.do")
public class IniciarSesionLinkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
    /**
     * @see HttpServlet#HttpServlet()
     */
    public IniciarSesionLinkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/Iniciosesion.jsp");
		view.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		UserDAO userDao = new es.unex.pi.dao.JDBCUserDAOImpl();		
		userDao.setConnection(conn);
		
		String nombre = request.getParameter("nombre");
		String password = request.getParameter("password");
		User user = userDao.get(nombre);
			
		if ((user != null) &&(password.equals(user.getPassword()))) {
			logger.info("User " + user.getId());
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			response.sendRedirect("IndexLinkServlet.do");
		}
		else {
			request.setAttribute("messages", "datos de entrada incorrectos!!");
			RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/Iniciosesion.jsp");
			view.forward(request, response);
		}
	}
}
