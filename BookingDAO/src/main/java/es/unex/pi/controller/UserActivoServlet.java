
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import es.unex.pi.dao.BookingDAO;
import es.unex.pi.dao.BookingsAccommodationsDAO;
import es.unex.pi.model.Accommodation;
import es.unex.pi.model.Booking;
import es.unex.pi.model.Property;
import es.unex.pi.model.User;

/**
 * Servlet implementation class UserActivoServlet
 */
@WebServlet("/UserActivoServlet.do")
public class UserActivoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserActivoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = (Connection) getServletContext().getAttribute("dbConn");
        BookingDAO bookingDao = new es.unex.pi.dao.JDBCBookingDAOImpl();   
        bookingDao.setConnection(conn);
        List<Booking> userBooks = bookingDao.getAll();
        Map<Long, Integer> iduCountMap = new HashMap<>();
        long mostFrequentIdu = -1; // Inicializamos con un idu inválido
        int maxCount = 0;
        
        for (Booking reserva : userBooks){
            long id = reserva.getIdu();
            int count = iduCountMap.getOrDefault(id, 0) + 1;
            iduCountMap.put(id, count);
            
            if (count > maxCount) {
                maxCount = count;
                mostFrequentIdu = id;
            }
        }
        request.setAttribute("maxCount", maxCount);
        request.setAttribute("mostFrequentIdu", mostFrequentIdu);
        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/masactivo.jsp");
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
