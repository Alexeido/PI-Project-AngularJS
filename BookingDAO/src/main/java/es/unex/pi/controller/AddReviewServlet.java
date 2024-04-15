package es.unex.pi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;

import es.unex.pi.model.*;
import es.unex.pi.dao.*;
/**
 * Servlet implementation class AddReviewServlet
 */
@WebServlet("/user/AddReviewServlet.do")

public class AddReviewServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddReviewServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String text=request.getParameter("texto");

		if(text!=null&&text!="") {
	        Connection conn = (Connection) getServletContext().getAttribute("dbConn");
			ReviewDAO reviewDao = new es.unex.pi.dao.JDBCReviewDAOImpl();
			reviewDao.setConnection(conn);
			PropertyDAO propertyDao = new es.unex.pi.dao.JDBCPropertyDAOImpl();	
			propertyDao.setConnection(conn);
			
	        HttpSession session = request.getSession();
	        User user= (User)session.getAttribute("user");
	        if (user == null) {
	            response.sendRedirect("IniciarSesionLinkServlet.do");
	            return;
	        }
	        long idp = Long.parseLong(request.getParameter("idp"));
	        int grade = Integer.parseInt(request.getParameter("nota"));
	        Review rev = new Review();
	        rev.setGrade(grade);
	        rev.setIdu(user.getId());
	        rev.setIdp(idp);
	        rev.setReview(text);
	        reviewDao.add(rev);
	        propertyDao.updateGradesAverage(propertyDao.get(idp));
	        
		}
        response.sendRedirect(request.getHeader("referer"));

	}

}
