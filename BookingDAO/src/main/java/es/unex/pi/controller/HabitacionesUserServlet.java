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
import java.util.List;

import es.unex.pi.model.Accommodation;
import es.unex.pi.model.Property;
import es.unex.pi.dao.AccommodationDAO;
import es.unex.pi.model.User;

/**
 * Servlet implementation class HabitacionesUserServlet
 */
@WebServlet("/user/HabitacionesUserServlet.do")
public class HabitacionesUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HabitacionesUserServlet() {
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
		HttpSession session = request.getSession();
		long id=(long) session.getAttribute("IdAlojamiento");

		List<Accommodation> listahabitaciones = accomodationDao.getAllByProperties(id);

		request.setAttribute("habitacionesuser", listahabitaciones);
		request.setAttribute("Habitacionidalojamiento", id);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/habitacionesuser.jsp");
		view.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
