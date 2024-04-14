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

import es.unex.pi.dao.PropertyDAO;
import es.unex.pi.dao.favoritosDAO;
import es.unex.pi.model.Property;
import es.unex.pi.model.User;
import es.unex.pi.model.favoritos;

/**
 * Servlet implementation class FavoritosUserServlet
 */
public class FavoritosUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FavoritosUserServlet() {
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
		HttpSession session=request.getSession();
		PropertyDAO propertyDao = new es.unex.pi.dao.JDBCPropertyDAOImpl();		
		propertyDao.setConnection(conn);
		
		User user=(User) session.getAttribute("user");
		
		List<favoritos> listafavoritos = favoritosDao.getAllByIdu(user.getId());
		List<Property> listaAlojamientos = new ArrayList<>();
		for (favoritos favorito : listafavoritos) {
			listaAlojamientos.add(propertyDao.get(favorito.getIdp()));
		}
		request.setAttribute("alojamientosuserfav", listaAlojamientos);
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/alojamientosfavoritos.jsp");
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
