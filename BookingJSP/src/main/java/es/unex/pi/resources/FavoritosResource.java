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
import es.unex.pi.model.BookingsAccommodations;
import es.unex.pi.model.Property;
import es.unex.pi.model.User;
import es.unex.pi.model.favoritos;

@Path("/favoritos")
public class FavoritosResource {
	
	  @Context
	  ServletContext sc;
	  @Context
	  UriInfo uriInfo;
  
	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Property> getFavoritos (@Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		favoritosDAO favoritosDao = new es.unex.pi.dao.JDBCfavoritosDAOImpl();		
		favoritosDao.setConnection(conn);
		PropertyDAO propertyDao = new es.unex.pi.dao.JDBCPropertyDAOImpl();		
		propertyDao.setConnection(conn);


		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		if(user==null) {
			throw new CustomBadRequestException("notLogin");
		}
		
		List<favoritos> listafavoritos = favoritosDao.getAllByIdu(user.getId());
		List<Property> listaAlojamientos = new ArrayList<>();
		for (favoritos favorito : listafavoritos) {
			listaAlojamientos.add(propertyDao.get(favorito.getIdp()));
		}
		
		return listaAlojamientos;
	  }
	  

	  @GET
	  @Path("/{idp}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public Boolean getFavorito (@PathParam("idp") long idp, @Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		favoritosDAO favoritosDao = new es.unex.pi.dao.JDBCfavoritosDAOImpl();		
		favoritosDao.setConnection(conn);


		HttpSession session=request.getSession();
		User user=(User) session.getAttribute("user");
		if(user==null) {
			throw new CustomBadRequestException("notLogin");
		}
		Boolean respuesta = favoritosDao.isFavourite(user.getId(), idp);
		return respuesta;
	  }
	  
		@DELETE
		@Path("/delete/{idp}")
		public Response eliminarFav(@PathParam("idp") long idp, @Context HttpServletRequest request ) {
			Connection conn = (Connection) sc.getAttribute("dbConn");
			favoritosDAO favoritosDao = new es.unex.pi.dao.JDBCfavoritosDAOImpl();		
			favoritosDao.setConnection(conn);
			HttpSession session=request.getSession();
			User user=(User) session.getAttribute("user");
			if(user==null) {
				throw new CustomBadRequestException("notLogin");
			}
			long idu=user.getId();
			if (!favoritosDao.delete(idu,idp)){
				throw new CustomBadRequestException("notFaved");		
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
			@Path("/{idp}")
			public Response addFav(@PathParam("idp") long idp, @Context HttpServletRequest request ) {
				Connection conn = (Connection) sc.getAttribute("dbConn");
				favoritosDAO favoritosDao = new es.unex.pi.dao.JDBCfavoritosDAOImpl();		
				favoritosDao.setConnection(conn);
				HttpSession session=request.getSession();
				User user=(User) session.getAttribute("user");
				if(user==null) {
					throw new CustomBadRequestException("notLogin");
				}

		    		long idu=user.getId();
		        	favoritos fav=new favoritos();
		        	fav.setIdp(idp);
		        	fav.setIdu(idu);

				if (!favoritosDao.add(fav)){
					throw new CustomBadRequestException("notFaved");		
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
