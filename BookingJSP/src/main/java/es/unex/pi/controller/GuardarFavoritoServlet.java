package es.unex.pi.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.util.logging.Logger;

import es.unex.pi.dao.favoritosDAO;
import es.unex.pi.model.User;
import es.unex.pi.model.favoritos;

/**
 * Servlet implementation class GuardarFavoritoServlet
 */
@WebServlet("/user/GuardarFavoritoServlet.do")
public class GuardarFavoritoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger logger = Logger.getLogger(HttpServlet.class.getName());

       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GuardarFavoritoServlet() {
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
		User user=(User) session.getAttribute("user");
		long idp=Long.parseLong(request.getParameter("idalojamiento"));

		// Si el usuario no existe en la sesión, debe iniciar sesión

    		long idu=user.getId();
        	favoritos fav=new favoritos();
        	fav.setIdp(idp);
        	fav.setIdu(idu);
			logger.info("fav idp " + idp + " ,fav idu " + idu);

        	favoritosDao.add(fav);
            response.sendRedirect(request.getHeader("referer"));

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
