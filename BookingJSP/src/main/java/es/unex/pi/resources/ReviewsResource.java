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
import es.unex.pi.dao.ReviewDAO;
import es.unex.pi.model.BookingsAccommodations;
import es.unex.pi.model.Property;
import es.unex.pi.model.Review;
import es.unex.pi.model.User;

@Path("/reviews")
public class ReviewsResource {
	
	  @Context
	  ServletContext sc;
	  @Context
	  UriInfo uriInfo;
  
	  @GET
	  @Path("/{idp}")
	  @Produces(MediaType.APPLICATION_JSON)
	  public List<Review> getReviews (@PathParam("idp") long idp, @Context HttpServletRequest request) {
		Connection conn = (Connection) sc.getAttribute("dbConn");
		ReviewDAO reviewsDao = new es.unex.pi.dao.JDBCReviewDAOImpl();		
		reviewsDao.setConnection(conn);
		List<Review> listareviews = reviewsDao.getAllByProperty(idp);
		
		return listareviews;
	  }
	  

		@DELETE
		@Path("/delete/{idp}")
		public Response eliminarFav(@PathParam("idp") long idp, @Context HttpServletRequest request ) {
			Connection conn = (Connection) sc.getAttribute("dbConn");
			ReviewDAO reviewsDao = new es.unex.pi.dao.JDBCReviewDAOImpl();		
			reviewsDao.setConnection(conn);
			HttpSession session=request.getSession();
			User user=(User) session.getAttribute("user");
			if(user==null) {
				throw new CustomBadRequestException("notLogin");
			}
			long idu=user.getId();
			if (!reviewsDao.delete(idu,idp)){
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
			@Produces(MediaType.APPLICATION_JSON)
			public Response addFav(Review myReview, @Context HttpServletRequest request ) {
				Connection conn = (Connection) sc.getAttribute("dbConn");
				ReviewDAO reviewsDao = new es.unex.pi.dao.JDBCReviewDAOImpl();		
				reviewsDao.setConnection(conn);
				HttpSession session=request.getSession();
				User user=(User) session.getAttribute("user");
				if(user==null) {
					throw new CustomBadRequestException("notLogin");
				}

				if (!reviewsDao.add(myReview)){
					throw new CustomBadRequestException("notFaved");		
				}
				  Response res = Response //return 201 and Location: /orders/newid
						   .created( 
							uriInfo.getAbsolutePathBuilder()
								   .path(Long.toString(myReview.getIdp()))
								   .build())
						   .contentLocation(
							uriInfo.getAbsolutePathBuilder()
							       .path(Long.toString(myReview.getIdp()))
							       .build())
							.build();
			      return res;	
				
			  }
	  
	  	
	
} 
