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
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import es.unex.pi.dao.AccommodationDAO;
import es.unex.pi.dao.PropertyDAO;
import es.unex.pi.model.Accommodation;
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
        AccommodationDAO accommodationDao = new es.unex.pi.dao.JDBCAccommodationDAOImpl();
        propertyDao.setConnection(conn);
        accommodationDao.setConnection(conn);
        HttpSession session=request.getSession();
        
        String Lugar=(String) session.getAttribute("lugar");
        String mostrar=(String) request.getAttribute("mostrar");
        List<Property> listaAlojamientos = propertyDao.getAllBySearchName(Lugar);
        

        
        if (mostrar != null && !mostrar.equals("Todos")) {
            Iterator<Property> iterator = listaAlojamientos.iterator();
            while (iterator.hasNext()) {
                Property alojamiento = iterator.next();
                switch (mostrar) {
                    case "Disponibles":
                        if (alojamiento.getAvailable() < 1) {
                            iterator.remove();
                        }
                        break;
                    case "No Disponibles":
                        if (alojamiento.getAvailable() > 0) {
                            iterator.remove();
                        }
                        break;
                    default:
                        // Si el valor de mostrar no coincide con ninguno de los casos anteriores
                        // Puedes manejar este caso según sea necesario
                        break;
                }
            }
        }

        String ordenar=(String) request.getAttribute("ordenar");
        System.out.println("Valor del parámetro 'ordenar': " + ordenar);
        if (ordenar != null) {

            switch (ordenar) {
                case "valoracionesAsc":
                	Collections.sort(listaAlojamientos, Collections.reverseOrder());
                    break;
                case "valoracionesDesc":
                	Collections.sort(listaAlojamientos);
                    break;
                default:
                    break;
            }
            
        }
        // Lista para almacenar los precios más bajos de las habitaciones
        List<Integer> preciosMasBajos = new ArrayList<>();

        // Iterar sobre la lista de alojamientos
        for (Property alojamiento : listaAlojamientos) {
        	propertyDao.updateGradesAverage(alojamiento); //Descomentar si quieres actualizar la nota media de todos los alojamientos
            long idp = alojamiento.getId();
            List<Accommodation> listaAccommodations = accommodationDao.getAllByProperties(idp);

            // Encontrar el precio mínimo entre todas las habitaciones del alojamiento
            Integer precioMinimo = Integer.MAX_VALUE; // Inicializar con un valor alto
            for (Accommodation accommodation : listaAccommodations) {
                if (accommodation.getPrice() < precioMinimo) {
                    precioMinimo = accommodation.getPrice();
                }
            }
            if(precioMinimo == Integer.MAX_VALUE) {
            	precioMinimo=0;
            }
            // Almacenar el precio mínimo encontrado en la lista de precios más bajos
            preciosMasBajos.add(precioMinimo);
        }


        
        RequestDispatcher view = request.getRequestDispatcher("WEB-INF/listaalojamientos.jsp");
        request.setAttribute("listaalojamiento", listaAlojamientos);
        request.setAttribute("preciosBajos", preciosMasBajos);
        view.forward(request,response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mostrar=(String) request.getParameter("Mostrar-por");
        request.setAttribute("mostrar", mostrar);
        String ordenar=(String) request.getParameter("Ordenar-por");
        request.setAttribute("ordenar", ordenar);
		doGet(request, response);
	}

}
