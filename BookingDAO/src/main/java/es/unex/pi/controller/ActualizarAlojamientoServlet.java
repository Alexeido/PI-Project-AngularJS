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
 * Servlet implementation class ActualizarAlojamientoServlet
 */
@WebServlet("/user/ActualizarAlojamientoServlet.do")
public class ActualizarAlojamientoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActualizarAlojamientoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		PropertyDAO propertyDao = new es.unex.pi.dao.JDBCPropertyDAOImpl();		
		propertyDao.setConnection(conn);
		long id=Long.parseLong(request.getParameter("idalojamiento"));
		HttpSession session = request.getSession();
	    User user = (User) session.getAttribute("user");
		Property alojamiento=propertyDao.get(id);

	    if(alojamiento.getIdu()!= user.getId()) {
			response.sendRedirect("AlojamientoUsuarioLinkServlet.do");
	    }
	    else {
		session.setAttribute("IdAlojamiento", id);
		
		request.setAttribute("alojamiento", alojamiento);
		RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/editaralojamiento.jsp");
		view.forward(request,response);
	    }
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
	    int petFriendly = request.getParameter("mascotas").equals("si") ? 1 : 0; // 1 si permite mascotas, 0 si no
	    int restaurante = request.getParameter("restaurante") != null ? 1 : 0;
	    int desayuno = request.getParameter("desayuno") != null ? 1 : 0;
	    int wifi = request.getParameter("wifi") != null ? 1 : 0;
	    int gym = request.getParameter("gym") != null ? 1 : 0;
	    int piscina = request.getParameter("piscina") != null ? 1 : 0;
	    int spa = request.getParameter("spa") != null ? 1 : 0;

	    User user = (User) session.getAttribute("user");
	    long idAlojamiento=(long) session.getAttribute("IdAlojamiento");
	    
	    Property alojamiento2 = propertyDao.get(idAlojamiento);
	    Property alojamiento = new Property();
	    alojamiento.setAvailable(alojamiento2.getAvailable());
	    alojamiento.setName(name);
	    alojamiento.setAddress(address);
	    alojamiento.setTelephone(telephone);
	    alojamiento.setCity(city);
	    alojamiento.setCenterDistance(centerDistance);
	    alojamiento.setDescription(description);
	    alojamiento.setPetFriendly(petFriendly);
	    alojamiento.setIdu((int) user.getId());
	    alojamiento.setRestaurante(restaurante);
	    alojamiento.setDesayuno(desayuno);
	    alojamiento.setWifi(wifi);
	    alojamiento.setGym(gym);
	    alojamiento.setPiscina(piscina);
	    alojamiento.setSpa(spa);
	    alojamiento.setId(idAlojamiento);
		propertyDao.update(alojamiento);
		response.sendRedirect("AlojamientoUsuarioLinkServlet.do");

	}

}
