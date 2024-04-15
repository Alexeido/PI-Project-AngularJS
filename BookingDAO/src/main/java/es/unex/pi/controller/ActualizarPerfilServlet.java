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

import es.unex.pi.dao.UserDAO;
import es.unex.pi.model.User;

/**
 * Servlet implementation class ActualizarPerfilServlet
 */
@WebServlet("/user/ActualizarPerfilServlet.do")

public class ActualizarPerfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActualizarPerfilServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/EditarPerfil.jsp");
		view.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		UserDAO userDao = new es.unex.pi.dao.JDBCUserDAOImpl();		
		userDao.setConnection(conn);
		String username = request.getParameter("nombre");
		String apellido = request.getParameter("apellido");
		String email = request.getParameter("email");
		HttpSession session=request.getSession();
		
		User useroriginal=(User) session.getAttribute("user");
		long id=useroriginal.getId();
		User user = new User();
		user.setEmail(email);
		user.setName(username);
		user.setSurname(apellido);
		user.setId(id);
		user.setPassword(useroriginal.getPassword());
		userDao.update(user);
		
		session.setAttribute("user", user);
		response.sendRedirect("UsuarioLinkServlet.do");
	}
}
