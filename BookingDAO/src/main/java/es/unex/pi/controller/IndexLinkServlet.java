package es.unex.pi.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.logging.Logger;

/**
 * Servlet implementation class IndexLinkServlet
 */

@WebServlet("/IndexLinkServlet.do")
public class IndexLinkServlet extends HttpServlet {
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public IndexLinkServlet() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/index.jsp");
		view.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String lugar=request.getParameter("Lugar");
		logger.info("Busqueda de estancia");
		HttpSession session=request.getSession();
		session.setAttribute("lugar", lugar);
		response.sendRedirect("ListaAlojamientosLinkServlet.do");
	}

}
