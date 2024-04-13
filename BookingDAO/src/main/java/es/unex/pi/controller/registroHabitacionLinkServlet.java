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

import es.unex.pi.dao.AccommodationDAO;
import es.unex.pi.model.Accommodation;

/**
 * Servlet implementation class registroHabitacionLinkServlet
 */
public class registroHabitacionLinkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registroHabitacionLinkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/registroHabitacion.jsp");
		view.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		AccommodationDAO accomodationDao = new es.unex.pi.dao.JDBCAccommodationDAOImpl();		
		accomodationDao.setConnection(conn);
		HttpSession session = request.getSession();

		String name = request.getParameter("name");
		//long idAlojamiento=Long.parseLong(request.getParameter("idAlojamiento"));
		String pricestr = request.getParameter("price");
		double price = Double.parseDouble(pricestr);
		int priceInt = (int) price;


		String numAccommodationsstr = request.getParameter("available");
		double numAccommodations = Double.parseDouble(numAccommodationsstr);
		int numAccommodationsint = (int) numAccommodations;

		String description = request.getParameter("description");

		Accommodation habitacion= new Accommodation();
		//habitacion.setIdp(idAlojamiento);
		habitacion.setName(name);
		habitacion.setNumAccommodations(numAccommodationsint);
		habitacion.setDescription(description);
		habitacion.setPrice(priceInt);
		accomodationDao.add(habitacion);
		response.sendRedirect("AlojamientoUsuarioLinkServlet.do");

	}

}
