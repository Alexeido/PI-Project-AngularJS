package es.unex.pi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;

import es.unex.pi.dao.favoritosDAO;
import es.unex.pi.model.User;

/**
 * Servlet implementation class EliminarFavoritoServlet
 */
@WebServlet("/user/EliminarFavoritoServlet.do")

public class EliminarFavoritoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarFavoritoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		favoritosDAO favoritosDao = new es.unex.pi.dao.JDBCfavoritosDAOImpl();		
		favoritosDao.setConnection(conn);
		long idp=Long.parseLong(request.getParameter("idalojamiento"));
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		long idu=user.getId();
		favoritosDao.delete(idp,idu);
        response.sendRedirect(request.getHeader("referer"));
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
