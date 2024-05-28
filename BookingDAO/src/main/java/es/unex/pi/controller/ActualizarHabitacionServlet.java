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
import es.unex.pi.dao.PropertyDAO;
import es.unex.pi.model.Accommodation;
import es.unex.pi.model.Property;
import es.unex.pi.model.User;

/**
 * Servlet implementation class ActualizarHabitacionServlet
 */
@WebServlet("/user/ActualizarHabitacionServlet.do")

public class ActualizarHabitacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActualizarHabitacionServlet() {
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
		PropertyDAO propertyDao = new es.unex.pi.dao.JDBCPropertyDAOImpl();		
		propertyDao.setConnection(conn);
		long id=Long.parseLong(request.getParameter("idhabitacion"));
		
		Accommodation habitacion=accomodationDao.get(id);
		Property alojamiento=propertyDao.get(habitacion.getIdp());
		HttpSession session = request.getSession();
	    User user = (User) session.getAttribute("user");
	    if(alojamiento.getIdu()!= user.getId()) {
			response.sendRedirect("HabitacionesUserServlet.do");
	    }
	    else {
		request.setAttribute("habitacion", habitacion);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/editarhabitacion.jsp");
		view.forward(request,response);
	    }

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
		String pricestr = request.getParameter("price");
		double price = Double.parseDouble(pricestr);
		int priceInt = (int) price;
		long idAlojamiento=(long) session.getAttribute("IdAlojamiento");

 
		String numAccommodationsstr = request.getParameter("available");
		double numAccommodations = Double.parseDouble(numAccommodationsstr);
		int numAccommodationsint = (int) numAccommodations;
		
		
		String idhabitacionstr = request.getParameter("idhabitacion");
		long idhabitacion = Long.parseLong(idhabitacionstr);
				

		
		String description = request.getParameter("description");

		Accommodation habitacion= new Accommodation();
		habitacion.setId(idhabitacion);
		habitacion.setIdp(idAlojamiento);
		habitacion.setName(name);
		habitacion.setNumAccommodations(numAccommodationsint);
		habitacion.setDescription(description);
		habitacion.setPrice(priceInt);
		accomodationDao.update(habitacion);
		response.sendRedirect("HabitacionesUserServlet.do");
	}

}