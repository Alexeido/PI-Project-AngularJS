package es.unex.pi.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;

import es.unex.pi.dao.ReviewDAO;
import es.unex.pi.dao.UserDAO;
import es.unex.pi.model.User;

/**
 * Servlet implementation class Influencer
 */
@WebServlet("/Influencer.do")
public class Influencer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Influencer() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection conn = (Connection) getServletContext().getAttribute("dbConn");
		ReviewDAO reviewDao = new es.unex.pi.dao.JDBCReviewDAOImpl();
		UserDAO userDao = new es.unex.pi.dao.JDBCUserDAOImpl();
		reviewDao.setConnection(conn);
	    userDao.setConnection(conn);
	    
	    
	    

		User influencer = userDao.get(reviewDao.getInfluencerIDU());
		int numReviews=reviewDao.getAllByUser(influencer.getId()).size();

		request.setAttribute("influencer", influencer);
		request.setAttribute("numReviews", numReviews);
		RequestDispatcher view = request.getRequestDispatcher("WEB-INF/influencers.jsp");
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
