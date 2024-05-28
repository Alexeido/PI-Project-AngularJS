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
import es.unex.pi.model.BookingsAccommodations;
import es.unex.pi.model.Property;
import es.unex.pi.model.User;

@Path("/users")
public class UsersResource {
	

    
		private String securePassword(String contraseña) {
		    // Comprueba si la contraseña tiene al menos 8 caracteres
		    if (contraseña.length() < 8) {
		        return "La contraseña debe tener al menos 8 caracteres.";
		    }
		    
		    // Comprueba si la contraseña contiene al menos una letra mayúscula
		    if (!contraseña.matches(".*[A-Z].*")) {
		        return "La contraseña debe contener al menos una letra mayúscula.";
		    }
		    
		    // Comprueba si la contraseña contiene al menos una letra minúscula
		    if (!contraseña.matches(".*[a-z].*")) {
		        return "La contraseña debe contener al menos una letra minúscula.";
		    }
		    
		    // Comprueba si la contraseña contiene al menos un dígito
		    if (!contraseña.matches(".*\\d.*")) {
		        return "La contraseña debe contener al menos un dígito.";
		    }
		    
		    // Comprueba si la contraseña contiene al menos un carácter especial
		    if (!contraseña.matches(".*[!@#$%^&*().].*")) {
		        return "La contraseña debe contener al menos un carácter especial.";
		    }
		    
		    // Si la contraseña pasa todas las comprobaciones, devuelve null (sin error)
		    return null;
		}

	  @Context
	  ServletContext sc;
	  @Context
	  UriInfo uriInfo;
  
	  @GET
	  @Produces(MediaType.APPLICATION_JSON)
	  public User getUserJSON(@Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		UserDAO userDao = new JDBCUserDAOImpl();
		userDao.setConnection(conn);
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("user");
		
		if (user != null) {
			User returnUser =userDao.get(user.getEmail());
			returnUser.setPassword("**********");
			return returnUser;
		}
		else throw new CustomNotFoundException("User in session is not found");
	  }
	  
	  
	  
	  
	  
	  
	  	@POST
		@Consumes(MediaType.APPLICATION_JSON)
		public Response post(User newUser,
							@Context HttpServletRequest request) throws Exception{

		  if(securePassword(newUser.getPassword())!=null) {
		        return Response.status(Response.Status.BAD_REQUEST)
		                .entity("{\"error\": \"La contraseña no es válida.\"}")
		                .type("application/json")
		                .build();
	      }	
		  Connection conn = (Connection)sc.getAttribute("dbConn");
		  UserDAO userDao = new es.unex.pi.dao.JDBCUserDAOImpl();		
		  userDao.setConnection(conn);
		  
		  Long uid=userDao.add(newUser);
		  
		  if(uid==-1){
		        return Response.status(Response.Status.BAD_REQUEST)
		                .entity("{\"error\": \"Este correo ya esta registrado en Booking\"}")
		                .type("application/json")
		                .build();	
		  }
		  newUser.setId(uid);
		  HttpSession session = request.getSession();
		  ArrayList<BookingsAccommodations> carrito = new ArrayList<>();
	      session.setAttribute("carrito", carrito);
	      session.setAttribute("user", newUser);
	      
		  Response res = Response //return 201 and Location: /orders/newid
				   .created( 
					uriInfo.getAbsolutePathBuilder()
						   .path(Long.toString(uid))
						   .build())
				   .contentLocation(
					uriInfo.getAbsolutePathBuilder()
					       .path(Long.toString(uid))
					       .build())
					.build();
	      return res;
		}
	  


	  	@PUT
		@Consumes(MediaType.APPLICATION_JSON)
		public Response put(User newUser, @Context HttpServletRequest request) throws Exception{

		  Connection conn = (Connection)sc.getAttribute("dbConn");
		  UserDAO userDao = new es.unex.pi.dao.JDBCUserDAOImpl();		
		  userDao.setConnection(conn);
		  HttpSession session = request.getSession();
		  User oldUser = (User) session.getAttribute("user");
		  newUser.setPassword(oldUser.getPassword());
		  newUser.setId(oldUser.getId());
		  
		  if(!userDao.update(newUser)){ 
				throw new CustomBadRequestException("Ese correo ya pertenece a otro usuario");
		  }

		  session.setAttribute("user", newUser);	
	        // Construir la respuesta con estado 200 y cuerpo de texto

		  Response res = Response //return 201 and Location: /orders/newid
				   .accepted( 
					uriInfo.getAbsolutePathBuilder()
						   .path(Long.toString(newUser.getId()))
						   .build())
				   .contentLocation(
					uriInfo.getAbsolutePathBuilder()
					       .path(Long.toString(newUser.getId()))
					       .build())
					.build();
	      return res;
		}
	  
	  	
  		@DELETE
		  public Response cerrarSessionUser(@Context HttpServletRequest request) {
			HttpSession session = request.getSession();
			User user = (User) session.getAttribute("user");
			
			if (user != null){
				session.removeAttribute("user");
				session.removeAttribute("carrito");
				return Response.noContent().build();
			}
			throw new CustomBadRequestException("notUser");		
		  }
  		
	  	
  		@DELETE
  		@Path("/delete/{id}")
  		public Response eliminarUser(@PathParam("id") long userId) {
  			Connection conn = (Connection)sc.getAttribute("dbConn");
  		    UserDAO userDao = new es.unex.pi.dao.JDBCUserDAOImpl();		
  		    userDao.setConnection(conn);
			if (!userDao.delete(userId)){
				throw new CustomBadRequestException("notUser");		
			}
			  Response res = Response //return 201 and Location: /orders/newid
					   .accepted( 
						uriInfo.getAbsolutePathBuilder()
							   .path(Long.toString(userId))
							   .build())
					   .contentLocation(
						uriInfo.getAbsolutePathBuilder()
						       .path(Long.toString(userId))
						       .build())
						.build();
		      return res;	
		  }

	
} 
