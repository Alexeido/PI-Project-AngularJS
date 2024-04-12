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

import es.unex.pi.dao.PropertyDAO;
import es.unex.pi.model.Property;

/**
 * Servlet implementation class ListaAlojamientosLinkServlet
 */

@WebServlet("/ListaAlojamientosLinkServlet.do")
public class ListaAlojamientosLinkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListaAlojamientosLinkServlet() {
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
		HttpSession session=request.getSession();
		
		String Lugar=(String) session.getAttribute("lugar");
		List<Property> listaAlojamientos = propertyDao.getAllBySearchName(Lugar);

		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/listaalojamientos.jsp");
		request.setAttribute("listaalojamiento", listaAlojamientos);
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
