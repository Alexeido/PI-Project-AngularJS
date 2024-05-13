package es.unex.pi.resources;

import java.io.Console;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collections;
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
import es.unex.pi.dao.JDBCBookingsAccommodationsDAOImpl;
import es.unex.pi.dao.BookingsAccommodationsDAO;
import es.unex.pi.dao.UserDAO;
import es.unex.pi.dao.favoritosDAO;
import es.unex.pi.model.Accommodation;
import es.unex.pi.model.BookingsAccommodations;
import es.unex.pi.model.Property;
import es.unex.pi.model.User;
import es.unex.pi.model.favoritos;

@Path("/alojamientos")
public class AlojamientosResource {
	
	  @Context
	  ServletContext sc;
	  @Context
	  UriInfo uriInfo;
  
	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Property> getSubidos (@Context HttpServletRequest request) {
		System.out.println("sssss");
		Connection conn = (Connection) sc.getAttribute("dbConn");
		PropertyDAO propertyDao = new es.unex.pi.dao.JDBCPropertyDAOImpl();		
		propertyDao.setConnection(conn);
		HttpSession session=request.getSession();
		
		User user=(User) session.getAttribute("user");
		List<Property> listaAlojamientos = propertyDao.getAllByUser(user.getId());
		
		return listaAlojamientos;
	  }
	  
		@GET
		@Path("/{idp}")
		@Produces(MediaType.APPLICATION_JSON)
		public Property getAlojamiento(@PathParam("idp") long idp, @Context HttpServletRequest request ) {
			Connection conn = (Connection) sc.getAttribute("dbConn");
			PropertyDAO propertyDao = new es.unex.pi.dao.JDBCPropertyDAOImpl();		
			propertyDao.setConnection(conn);
			Property alojamientouser=propertyDao.get(idp);
			alojamientouser.setGradesAverage(propertyDao.updateGradesAverage(alojamientouser)); //Descomentar si quieres actualizar la nota media de todos los alojamientos
			/* Esto se hace en los propios controllers mejor...
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			if(user.getId()!= alojamientouser.getIdu()){
				throw new CustomBadRequestException("notYours");	
			}*/
			return alojamientouser;
		}
	  
		@GET
		@Path("/reserva/{idb}")
		@Produces(MediaType.APPLICATION_JSON)
		public Property getAlojamientoBook(@PathParam("idb") long idb, @Context HttpServletRequest request ) {
			Connection conn = (Connection) sc.getAttribute("dbConn");
					
			BookingsAccommodationsDAO accommodationsBookingsDao = new es.unex.pi.dao.JDBCBookingsAccommodationsDAOImpl();		
			accommodationsBookingsDao.setConnection(conn);

			Accommodation i = accommodationsBookingsDao.getOneByBooking(idb);
			if (i == null) {
				Property p= new Property();
				p.setName("Error");
				p.setCity(" Base de Datos, reserva sin habitaciones");
				i=new Accommodation();
				i.setPrice(-1);
				i.setProp(p);
			}

			/* Esto se hace en los propios controllers mejor...
			HttpSession session = request.getSession();
			User user = (User)session.getAttribute("user");
			if(user.getId()!= alojamientouser.getIdu()){
				throw new CustomBadRequestException("notYours");	
			}*/
			return i.getProp();
		}

		
		@GET
		@Path("/list/{lugar}/{order}/{filter}")
		@Produces(MediaType.APPLICATION_JSON)
		public List<Property> getBusquedaAlojamientos (@PathParam("lugar") String lugar, @PathParam("order")  int order, @PathParam("filter")  int filter, @Context HttpServletRequest request) {
			System.out.println("Buscando en "+lugar+" Orden:"+order+ " Filtro:"+ filter);
			Connection conn = (Connection) sc.getAttribute("dbConn");
			PropertyDAO propertyDao = new es.unex.pi.dao.JDBCPropertyDAOImpl();		
			propertyDao.setConnection(conn);
			List<Property> listaAlojamientos; 
			
			if(lugar.equals("all")){
				listaAlojamientos= propertyDao.getAll();
			}
			else{
				listaAlojamientos = propertyDao.getAllBySearchName(lugar);
			}
			
			/*   Orden  | Filtro
			0  Como sea | Todos
			1  ValDesc  | Disponibles
			2  ValAsc   | No Disponibles
			*/
			
        
		Iterator<Property> iterator = listaAlojamientos.iterator();
		while (iterator.hasNext()) {
			Property alojamiento = iterator.next();
        	alojamiento.setGradesAverage(propertyDao.updateGradesAverage(alojamiento)); //Descomentar si quieres actualizar la nota media de todos los alojamientos
			switch (filter) {
				case 1:
					if (alojamiento.getAvailable() < 1) {
						iterator.remove();
					}
					break;
				case 2:
					if (alojamiento.getAvailable() > 0) {
						iterator.remove();
					}
					break;
				default:
					break;
			}
		}

		if (order != 0) {

            switch (order) {
                case 2:
                	Collections.sort(listaAlojamientos, Collections.reverseOrder());
                    break;
                case 1:
                	Collections.sort(listaAlojamientos);
                    break;
                default:
                    break;
            }
            
        }



			return listaAlojamientos;
		}
	
		@DELETE
		@Path("/delete/{idp}")
		public Response eliminarAlojamiento(@PathParam("idp") long idp, @Context HttpServletRequest request ) {
			Connection conn = (Connection) sc.getAttribute("dbConn");
			PropertyDAO propertyDao = new es.unex.pi.dao.JDBCPropertyDAOImpl();		
			propertyDao.setConnection(conn);
			propertyDao.delete(idp);
			
			if (!propertyDao.delete(idp)){
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

		  
		@PUT
		@Path("/put/{idp}")
		public Response cambiarDisponibilidad(@PathParam("idp") long idp, @Context HttpServletRequest request ) {
			Connection conn = (Connection) sc.getAttribute("dbConn");
			PropertyDAO propertyDao = new es.unex.pi.dao.JDBCPropertyDAOImpl();		
			propertyDao.setConnection(conn);
			Property alojamiento = propertyDao.get(idp);
			alojamiento.setAvailable((alojamiento.getAvailable()-1)*-1);
			if (!propertyDao.update(alojamiento)){
				throw new CustomBadRequestException("notUpdated");		
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
		  
		

		@PUT
		public Response editaralojamiento(Property newalojamiento, @Context HttpServletRequest request ) {
			Connection conn = (Connection) sc.getAttribute("dbConn");
			PropertyDAO propertyDao = new es.unex.pi.dao.JDBCPropertyDAOImpl();		
			propertyDao.setConnection(conn);
			if (!propertyDao.update(newalojamiento)){
				throw new CustomBadRequestException("notUpdated");		
			}
			  Response res = Response //return 201 and Location: /orders/newid
					   .accepted( 
						uriInfo.getAbsolutePathBuilder()
							   .path(Long.toString(newalojamiento.getId()))
							   .build())
					   .contentLocation(
						uriInfo.getAbsolutePathBuilder()
						       .path(Long.toString(newalojamiento.getId()))
						       .build())
						.build();
		      return res;	
		  }


	  	@POST
		@Consumes(MediaType.APPLICATION_JSON)
		public Response post(Property newalojamiento,
							@Context HttpServletRequest request) throws Exception{
		  Connection conn = (Connection) sc.getAttribute("dbConn");
		  PropertyDAO propertyDao = new es.unex.pi.dao.JDBCPropertyDAOImpl();      
		  propertyDao.setConnection(conn);
		  HttpSession session = request.getSession();
		  
		  User user = (User) session.getAttribute("user");

		  newalojamiento.setIdu((int) user.getId());
		   long idp=propertyDao.add(newalojamiento);

		  if(idp==-1){
		        return Response.status(Response.Status.BAD_REQUEST)
		                .entity("{\"error\": \"Erro al registrar\"}")
		                .type("application/json")
		                .build();	
		  }
		  Response res = Response //return 201 and Location: /orders/newid
				   .created( 
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
	  
	
} 
