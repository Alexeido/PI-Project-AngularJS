package es.unex.pi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

import es.unex.pi.dao.AccommodationDAO;

/**
 * Servlet implementation class EliminarHabitacionServlet
 */
@WebServlet("/user/EliminarHabitacionServlet.do")

public class EliminarHabitacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EliminarHabitacionServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		AccommodationDAO accomodationDao = new es.unex.pi.dao.JDBCAccommodationDAOImpl();		
		accomodationDao.setConnection(conn);
		long id=Long.parseLong(request.getParameter("idhabitacion"));
		accomodationDao.delete(id);
		response.sendRedirect("HabitacionesUserServlet.do");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
