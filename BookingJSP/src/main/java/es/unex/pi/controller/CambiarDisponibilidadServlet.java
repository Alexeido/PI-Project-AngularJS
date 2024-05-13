package es.unex.pi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

import es.unex.pi.dao.PropertyDAO;
import es.unex.pi.model.Property;

/**
 * Servlet implementation class CambiarDisponibilidadServlet
 */
@WebServlet("/user/CambiarDisponibilidadServlet.do")

public class CambiarDisponibilidadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CambiarDisponibilidadServlet() {
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
		Property alojamiento = propertyDao.get(id);
		if(alojamiento.getAvailable()==1) {
			alojamiento.setAvailable(0);
		}
		else {
			alojamiento.setAvailable(1);
		}
		propertyDao.update(alojamiento);
		response.sendRedirect("AlojamientoUsuarioLinkServlet.do");

		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
