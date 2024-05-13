package es.unex.pi.resources;

import java.io.Console;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.MultivaluedMap;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriInfo;
import es.unex.giiis.pi.resources.exceptions.CustomBadRequestException;
import es.unex.giiis.pi.resources.exceptions.CustomNotFoundException;
import es.unex.pi.dao.JDBCPropertyDAOImpl;
import es.unex.pi.dao.JDBCUserDAOImpl;
import es.unex.pi.dao.PropertyDAO;
import es.unex.pi.dao.UserDAO;
import es.unex.pi.dao.favoritosDAO;
import es.unex.pi.dao.AccommodationDAO;
import es.unex.pi.model.Accommodation;
import es.unex.pi.model.BookingsAccommodations;
import es.unex.pi.model.Property;
import es.unex.pi.model.User;
import es.unex.pi.model.favoritos;

@Path("/carrito")
public class CarritoResource {
	
	  @Context
	  ServletContext sc;
	  @Context
	  UriInfo uriInfo;

	  @POST
	  @Path("/{id}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response post (@PathParam("id") long ida, @Context HttpServletRequest request) {
		HttpSession session=request.getSession();
		@SuppressWarnings("unchecked")
		ArrayList<BookingsAccommodations> carrito = (ArrayList<BookingsAccommodations>) session.getAttribute("carrito");
		System.out.println("ESTAMOS en carrito");

        // Si el carrito no existe en la sesión, debe iniciar sesión
        if (carrito == null) {
            throw new CustomBadRequestException("No has inciado sesion");	
        }

		boolean encontrado = false;
        for (BookingsAccommodations booking : carrito) {
            if (booking.getIdacc() == ida) {
                // Si el alojamiento está en el carrito, incrementa numAccommodations
                booking.setNumAccommodations(booking.getNumAccommodations() + 1);
                encontrado = true;
                break;
            }
        }
        
        if(!encontrado) {
			BookingsAccommodations booking = new BookingsAccommodations();
	        booking.setIdacc(ida);
	        booking.setNumAccommodations(1);
	        carrito.add(booking);
        }

        session.setAttribute("carrito", carrito);

		Response res = Response //return 201 and Location: /orders/newid
		.accepted( 
		 uriInfo.getAbsolutePathBuilder()
				.path(Long.toString(ida))
				.build())
		.contentLocation(
		 uriInfo.getAbsolutePathBuilder()
				.path(Long.toString(ida))
				.build())
		 .build();
		return res;	
	  }
	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Accommodation> gethabitaciones (@Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		AccommodationDAO accommodationDao = new es.unex.pi.dao.JDBCAccommodationDAOImpl();
		
	    accommodationDao.setConnection(conn);
	    

		HttpSession session=request.getSession();
		@SuppressWarnings("unchecked")
		ArrayList<BookingsAccommodations> carrito = (ArrayList<BookingsAccommodations>) session.getAttribute("carrito");
		
		
		// Si el carrito no existe en la sesión, debe iniciar sesión
        if (carrito == null) {
            throw new CustomBadRequestException("No has inciado sesion");	
        }
		ArrayList<Accommodation> habitaciones =  new ArrayList<>();
        
        for (BookingsAccommodations booking : carrito) {
        	for(int i=0;i<booking.getNumAccommodations();i++) {
        		Accommodation hab=accommodationDao.get(booking.getIdacc());
        		habitaciones.add(hab);
        	}	
        }
		
		return habitaciones;
	  }



	  @DELETE
	  @Path("/{ida}")
	  public Response eliminarAlojamiento(@PathParam("ida") long ida, @Context HttpServletRequest request ) {
        HttpSession session = request.getSession();
		ArrayList<BookingsAccommodations> carrito = (ArrayList<BookingsAccommodations>) session.getAttribute("carrito");
        
        if (carrito == null) {
            throw new CustomBadRequestException("No has inciado sesion");	
        }

        Iterator<BookingsAccommodations> iterator = carrito.iterator();
        while (iterator.hasNext()) {
            BookingsAccommodations booking = iterator.next();
            if (booking.getIdacc() == ida) {
                booking.setNumAccommodations(booking.getNumAccommodations() - 1);
                if (booking.getNumAccommodations() == 0) {
                    iterator.remove();
                }
                break;
            }
        }
        session.setAttribute("carrito", carrito);
			Response res = Response //return 201 and Location: /orders/newid
					 .accepted( 
					  uriInfo.getAbsolutePathBuilder()
							 .path(Long.toString(ida))
							 .build())
					 .contentLocation(
					  uriInfo.getAbsolutePathBuilder()
							 .path(Long.toString(ida))
							 .build())
					  .build();
			return res;	
		  
		}




} 
