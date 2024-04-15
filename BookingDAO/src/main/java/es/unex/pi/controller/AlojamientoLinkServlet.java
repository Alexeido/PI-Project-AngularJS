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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import es.unex.pi.dao.PropertyDAO;
import es.unex.pi.dao.ReviewDAO;
import es.unex.pi.dao.UserDAO;
import es.unex.pi.dao.AccommodationDAO;
import es.unex.pi.model.Property;
import es.unex.pi.model.Review;
import es.unex.pi.model.User;
import es.unex.pi.model.Accommodation;

/**
 * Servlet implementation class AlojamientoLinkServlet
 */
@WebServlet("/AlojamientoLinkServlet.do")
public class AlojamientoLinkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AlojamientoLinkServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		PropertyDAO propertyDao = new es.unex.pi.dao.JDBCPropertyDAOImpl();	
		ReviewDAO reviewDao = new es.unex.pi.dao.JDBCReviewDAOImpl();
		UserDAO userDao = new es.unex.pi.dao.JDBCUserDAOImpl();
		AccommodationDAO accommodationDao = new es.unex.pi.dao.JDBCAccommodationDAOImpl();
		reviewDao.setConnection(conn);
		propertyDao.setConnection(conn);
	    userDao.setConnection(conn);
	    accommodationDao.setConnection(conn);
		HttpSession session=request.getSession();
		
		boolean commented=false;
		Long idp=(Long) session.getAttribute("idp");
		Property alojamiento = propertyDao.get(idp);
		List<Accommodation> listaAccommodations = accommodationDao.getAllByProperties(idp);
		List<Review> listaReviews = reviewDao.getAllByProperty(idp);
		
		// Para cada reseña, obtener el nombre del usuario y almacenarlo en una lista de nombres
	    List<String> nombresUsuarios = new ArrayList<>();
        User user= (User)session.getAttribute("user");
	    for (Review review : listaReviews) {
	        User usuario = userDao.get(review.getIdu());
	        nombresUsuarios.add(usuario.getName());
	        if(user==null||usuario.getId()==user.getId()) {
	        	commented=true;
	        }
	    }
	    

		request.setAttribute("alojamiento", alojamiento);
		request.setAttribute("commented", commented);
		request.setAttribute("listaReviews", listaReviews);
		request.setAttribute("listaAccommodations", listaAccommodations);
		request.setAttribute("nombresUsuarios", nombresUsuarios); // Pasar la lista de nombres de usuario a la vista
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/alojamiento.jsp");
		view.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idpStr = request.getParameter("idp");
        long idp=-1;
		if (idpStr != null && !idpStr.isEmpty()) {
		    try {
		        idp = Long.parseLong(idpStr);
		    } catch (NumberFormatException e) {
		        e.printStackTrace();
		    }
		}
		logger.info("Estancia seleccionada");
		HttpSession session=request.getSession();
		session.setAttribute("idp", idp);
		doGet(request, response);
	}

}
