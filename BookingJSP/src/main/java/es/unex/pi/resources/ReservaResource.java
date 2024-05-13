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
import es.unex.pi.dao.BookingDAO;
import es.unex.pi.dao.AccommodationDAO;

import es.unex.pi.dao.BookingsAccommodationsDAO;
import es.unex.pi.model.Accommodation;
import es.unex.pi.model.Booking;
import es.unex.pi.model.BookingsAccommodations;
import es.unex.pi.model.Property;
import es.unex.pi.model.User;
import es.unex.pi.model.favoritos;

@Path("/reserva")
public class ReservaResource {
	
	  @Context
	  ServletContext sc;
	  @Context
	  UriInfo uriInfo;





		

	  @GET
	  @Path("/{id}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Accommodation> gethabitaciones (@PathParam("id") long idb, @Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		AccommodationDAO accommodationDao = new es.unex.pi.dao.JDBCAccommodationDAOImpl();
		BookingsAccommodationsDAO accommodationsBookingsDao = new es.unex.pi.dao.JDBCBookingsAccommodationsDAOImpl();	
		accommodationsBookingsDao.setConnection(conn);
	    accommodationDao.setConnection(conn);
	    
		List<BookingsAccommodations> carrito = accommodationsBookingsDao.getAllByBooking(idb);
		
		if(carrito.size()==0) {
			throw new CustomBadRequestException("noExiste la reserva");
		}

		
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		BookingDAO bookingDao = new es.unex.pi.dao.JDBCBookingDAOImpl();
		bookingDao.setConnection(conn);
		if(user==null) {
			throw new CustomBadRequestException("notSession");
		}
		if(user.getId()!=bookingDao.get(idb).getIdu()) {
			throw new CustomBadRequestException("notYours");
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


	  @POST
	  @Path("/{totalprice}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response post (@PathParam("totalprice") int totalprice,@Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		BookingDAO bookingDao = new es.unex.pi.dao.JDBCBookingDAOImpl();
		BookingsAccommodationsDAO accommodationsBookingsDao = new es.unex.pi.dao.JDBCBookingsAccommodationsDAOImpl();
		AccommodationDAO accommodationDao = new es.unex.pi.dao.JDBCAccommodationDAOImpl();
		bookingDao.setConnection(conn);
		accommodationsBookingsDao.setConnection(conn);
	    accommodationDao.setConnection(conn);
	    

		HttpSession session=request.getSession();
		Booking book = new Booking();
	    User user = (User) session.getAttribute("user");
		if(user==null) {
			throw new CustomBadRequestException("notSession");
		}
		ArrayList<BookingsAccommodations> carrito = (ArrayList<BookingsAccommodations>) session.getAttribute("carrito");
        
        book.setIdu(user.getId());
        book.setTotalPrice(totalprice);
		long idb =bookingDao.add(book);

        for (BookingsAccommodations booking : carrito) {
            booking.setIdb(idb); // Establecer el idb en cada elemento del carrito
            accommodationsBookingsDao.add(booking);
        }
        

		carrito = new ArrayList<>();
        session.setAttribute("carrito", carrito);

		Response res = Response //return 201 and Location: /orders/newid
		.accepted( 
		 uriInfo.getAbsolutePathBuilder()
				.path(Long.toString(idb))
				.build())
		.contentLocation(
		 uriInfo.getAbsolutePathBuilder()
				.path(Long.toString(idb))
				.build())
		 .build();
		return res;	
	  }

	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Booking> getSubidos (@Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		BookingDAO bookingDao = new es.unex.pi.dao.JDBCBookingDAOImpl();	
		BookingsAccommodationsDAO accommodationsBookingsDao = new es.unex.pi.dao.JDBCBookingsAccommodationsDAOImpl();	
		bookingDao.setConnection(conn);
		accommodationsBookingsDao.setConnection(conn);
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		if(user==null) {
			throw new CustomBadRequestException("notSession");
		}

		List<Booking> userBooks = bookingDao.getUserBooks(user.getId());
		
		return userBooks;
	  }



	  @DELETE
	  @Path("/{id}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response post (@PathParam("id") long idr,@Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		BookingDAO bookingDao = new es.unex.pi.dao.JDBCBookingDAOImpl();
		bookingDao.setConnection(conn);
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		if(user==null) {
			throw new CustomBadRequestException("notSession");
		}
		if(user.getId()!=bookingDao.get(idr).getIdu()) {
			throw new CustomBadRequestException("notYours");
		}
		bookingDao.delete(idr);


		Response res = Response //return 201 and Location: /orders/newid
		.accepted( 
		 uriInfo.getAbsolutePathBuilder()
				.path(Long.toString(idr))
				.build())
		.contentLocation(
		 uriInfo.getAbsolutePathBuilder()
				.path(Long.toString(idr))
				.build())
		 .build();
		return res;	
	  }

	  
	  @DELETE
	  @Path("/{id}/{idacc}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public Response post (@PathParam("id") long idb,@PathParam("idacc") long ida,@Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		BookingDAO bookingDao = new es.unex.pi.dao.JDBCBookingDAOImpl();
		bookingDao.setConnection(conn);
		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		if(user==null) {
			throw new CustomBadRequestException("notSession");
		}
		if(user.getId()!=bookingDao.get(idb).getIdu()) {
			throw new CustomBadRequestException("notYours");
		}
		

		
		BookingsAccommodationsDAO accommodationsBookingsDao = new es.unex.pi.dao.JDBCBookingsAccommodationsDAOImpl();	
		accommodationsBookingsDao.setConnection(conn);
		BookingsAccommodations ba=accommodationsBookingsDao.get(idb, ida);
		if(ba.getNumAccommodations()>1) {
			ba.setNumAccommodations(ba.getNumAccommodations()-1);
			accommodationsBookingsDao.update(ba, ba);
		}
		else {
			accommodationsBookingsDao.delete(idb, ida);
			if(accommodationsBookingsDao.getAllByBooking(idb).isEmpty()) {
				bookingDao.delete(idb);
			}
		}
		bookingDao.updateTotalPrice(bookingDao.get(idb));

			



		Response res = Response //return 201 and Location: /orders/newid
		.accepted( 
		 uriInfo.getAbsolutePathBuilder()
				.path(Long.toString(idb))
				.build())
		.contentLocation(
		 uriInfo.getAbsolutePathBuilder()
				.path(Long.toString(idb))
				.build())
		 .build();
		return res;	
	  }

} 
