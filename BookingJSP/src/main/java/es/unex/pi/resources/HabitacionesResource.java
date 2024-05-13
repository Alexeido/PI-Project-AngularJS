package es.unex.pi.resources;

import java.io.Console;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
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

@Path("/habitaciones")
public class HabitacionesResource {
	
	  @Context
	  ServletContext sc;
	  @Context
	  UriInfo uriInfo;

	  @GET
	  @Path("/{id}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Accommodation> getSubidos (@PathParam("id") long idp, @Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		AccommodationDAO accomodationDao = new es.unex.pi.dao.JDBCAccommodationDAOImpl();		
		accomodationDao.setConnection(conn);
		HttpSession session = request.getSession();
		session.setAttribute("IdAlojamiento", idp);
		List<Accommodation> listahabitaciones = accomodationDao.getAllByProperties(idp);
		
		return listahabitaciones;
	  }
	  
	@GET
	@Path("/one/{idp}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public Accommodation getHabitacion (@PathParam("idp") long idp, @Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		AccommodationDAO accomodationDao = new es.unex.pi.dao.JDBCAccommodationDAOImpl();		
		accomodationDao.setConnection(conn);
		Accommodation habitacion = accomodationDao.get(idp);
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user.getId()!= habitacion.getProp().getIdu()){
			throw new CustomBadRequestException("notYours");	
		}
		
		
		return habitacion;
	  }



		@DELETE
		@Path("/{id}")
		public Response eliminarAlojamiento(@PathParam("id") long idp, @Context HttpServletRequest request ) {
			Connection conn = (Connection) sc.getAttribute("dbConn");
			AccommodationDAO accomodationDao = new es.unex.pi.dao.JDBCAccommodationDAOImpl();		
			accomodationDao.setConnection(conn);
			
			if (!accomodationDao.delete(idp)){
				throw new CustomBadRequestException("notDeleted");		
			}
			  Response res = Response //return 201 and Location: /orders/newid
					   .accepted( 
						uriInfo.getAbsolutePathBuilder()
							   .path(Long.toString(idp))
							   .build())
					   .contentLocation(
						uriInfo.getAbsolutePathBuilder()
						       .path(Long.toString(idp))
						       .build())
						.build();
		      return res;	
			
		  }

	  	@POST
		@Consumes(MediaType.APPLICATION_JSON)
		public Response post(Accommodation newhabitacion, @Context HttpServletRequest request) throws Exception{
		 	Connection conn = (Connection) sc.getAttribute("dbConn");
		  	AccommodationDAO accomodationDao = new es.unex.pi.dao.JDBCAccommodationDAOImpl();		
			accomodationDao.setConnection(conn);
			HttpSession session = request.getSession();
			long idAlojamiento=(long) session.getAttribute("IdAlojamiento");
			newhabitacion.setIdp(idAlojamiento);
		  	long id=accomodationDao.add(newhabitacion);

		  if(id==-1){
		        return Response.status(Response.Status.BAD_REQUEST)
		                .entity("{\"error\": \"Erro al registrar\"}")
		                .type("application/json")
		                .build();	
		  }
		  Response res = Response //return 201 and Location: /orders/newid
				   .created( 
					uriInfo.getAbsolutePathBuilder()
						   .path(Long.toString(id))
						   .build())
				   .contentLocation(
					uriInfo.getAbsolutePathBuilder()
					       .path(Long.toString(id))
					       .build())
					.build();
	      return res;
		}
	  	@PUT
		public Response editarhabitaacion(Accommodation habitacion, @Context HttpServletRequest request ) {
			Connection conn = (Connection) sc.getAttribute("dbConn");
		  	AccommodationDAO accomodationDao = new es.unex.pi.dao.JDBCAccommodationDAOImpl();		
			accomodationDao.setConnection(conn);
			if (!accomodationDao.update(habitacion)){
				throw new CustomBadRequestException("notUpdated");		
			}
			  Response res = Response //return 201 and Location: /orders/newid
					   .accepted( 
						uriInfo.getAbsolutePathBuilder()
							   .path(Long.toString(habitacion.getId()))
							   .build())
					   .contentLocation(
						uriInfo.getAbsolutePathBuilder()
						       .path(Long.toString(habitacion.getId()))
						       .build())
						.build();
		      return res;	
		  }
	
} 
