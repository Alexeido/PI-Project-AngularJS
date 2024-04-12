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

import es.unex.pi.dao.PropertyDAO;
import es.unex.pi.model.Property;
import es.unex.pi.model.User;

/**
 * Servlet implementation class registroAlojamientoLinkServlet
 */
@WebServlet("/registroAlojamientoLinkServlet.do")
public class registroAlojamientoLinkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public registroAlojamientoLinkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/registroAlojamiento.jsp");
		view.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		PropertyDAO propertyDao = new es.unex.pi.dao.JDBCPropertyDAOImpl();		
		propertyDao.setConnection(conn);
		HttpSession session = request.getSession();

		String name = request.getParameter("name");
		String address = request.getParameter("address");
		String telephone = request.getParameter("telephone");
		String city = request.getParameter("city");
		String centerDistanceStr = request.getParameter("centerDistance");
		double centerDistance = Double.parseDouble(centerDistanceStr);
		String description = request.getParameter("description");
		String availableStr = request.getParameter("available");
		int available = Integer.parseInt(availableStr);
		int petFriendly = request.getParameter("mascotas").equals("si") ? 1 : 0; // 1 si permite mascotas, 0 si no
		String[] servicios = request.getParameterValues("servicios"); // Obtener todos los servicios seleccionados
		User user = new User();
		user=(User) session.getAttribute("user");
		
		
		Property alojamiento = new Property();
		alojamiento.setName(name);
		alojamiento.setAddress(address);
		alojamiento.setTelephone(telephone);
		alojamiento.setCity(city);
		alojamiento.setCenterDistance(centerDistance);
		alojamiento.setDescription(description);
		alojamiento.setPetFriendly(petFriendly);
		alojamiento.setAvailable(available);
		alojamiento.setIdu((int) user.getId());
		propertyDao.add(alojamiento);
		
	}

}
