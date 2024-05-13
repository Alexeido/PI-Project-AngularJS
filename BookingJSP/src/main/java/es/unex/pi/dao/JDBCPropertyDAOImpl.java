package es.unex.pi.dao;

import java.math.RoundingMode;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import es.unex.pi.model.Review;
import es.unex.pi.model.Accommodation;
import es.unex.pi.model.Property;

public class JDBCPropertyDAOImpl implements PropertyDAO {

	private Connection conn;
	private static final Logger logger = Logger.getLogger(JDBCPropertyDAOImpl.class.getName());
	
	@Override
	public Property get(long id) {
		if (conn == null) return null;
		
		Property property = null;		
		
		try {
			AccommodationDAO accommodationDao = new es.unex.pi.dao.JDBCAccommodationDAOImpl();
			accommodationDao.setConnection(conn);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM properties WHERE id ="+id);			 
			if (!rs.next()) return null; 
			property  = new Property();	 
			fromRsToPropertyObject(rs,property);
			List<Accommodation> listaAccommodations = accommodationDao.getAllByProperties2(property.getId());
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
			property.setLowestPrice(precioMinimo);
			logger.info("fetching property: "+property.getId());
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return property;
	}
	
	public List<Property> getAll() {

		if (conn == null) return null;
		
		ArrayList<Property> properties = new ArrayList<Property>();
		try {
			Statement stmt;
			ResultSet rs;
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM properties");
			AccommodationDAO accommodationDao = new es.unex.pi.dao.JDBCAccommodationDAOImpl();
			accommodationDao.setConnection(conn);
			while ( rs.next() ) {
				Property property = new Property();
				property  = new Property();	 
				fromRsToPropertyObject(rs,property);
				List<Accommodation> listaAccommodations = accommodationDao.getAllByProperties2(property.getId());
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
				property.setLowestPrice(precioMinimo);
				properties.add(property);
				logger.info("fetching property: "+property.getId());	
				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return properties;
	}
	
	public List<Property> getAllBySearchName(String search) {
		search = search.toUpperCase();
		if (conn == null)
			return null;

		ArrayList<Property> properties = new ArrayList<Property>();
		try {
			AccommodationDAO accommodationDao = new es.unex.pi.dao.JDBCAccommodationDAOImpl();
			accommodationDao.setConnection(conn);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM properties WHERE UPPER(name) LIKE '%" + search + "%' or  UPPER(city) LIKE '%" + search + "%' or  UPPER(description) LIKE '%" + search + "%'");

			while (rs.next()) {
				Property property = new Property();
				fromRsToPropertyObject(rs,property);
				List<Accommodation> listaAccommodations = accommodationDao.getAllByProperties2(property.getId());
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
				property.setLowestPrice(precioMinimo);
				properties.add(property);
				
				logger.info("fetching property: "+property.getId());
				
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return properties;
	}
	

	
	public List<Property> getAllByUser(long idu) {
		
		if (conn == null)
			return null;

		ArrayList<Property> properties = new ArrayList<Property>();
		try {
			AccommodationDAO accommodationDao = new es.unex.pi.dao.JDBCAccommodationDAOImpl();
			accommodationDao.setConnection(conn);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM properties WHERE idu = "+ idu);

			while (rs.next()) {
				Property property = new Property();
				fromRsToPropertyObject(rs,property);
				List<Accommodation> listaAccommodations = accommodationDao.getAllByProperties2(property.getId());
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
				property.setLowestPrice(precioMinimo);
				properties.add(property);
		
				logger.info("fetching properties: "+property.getId());
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return properties;
	}

	@Override
	public long add(Property property) {
		long id=-1;
		long lastid=-1;
		if (conn != null){
			logger.info("CREATING property " + property.getName());
			Statement stmt;
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM sqlite_sequence WHERE name ='properties'");			 
				if (!rs.next()) return -1; 
				lastid=rs.getInt("seq");
								
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("INSERT INTO properties (name, address, telephone, idu, gradesAverage, city, centerDistance, description, petFriendly, available, Restaurante, Desayuno, Wifi, Gym, Piscina, Spa) VALUES('"
	                    + property.getName() + "','" + property.getAddress() + "','" + property.getTelephone() + "',"
	                    + property.getIdu() + "," + "ROUND(" + property.getGradesAverage() + ", 1)" + ",'" + property.getCity() + "'," + property.getCenterDistance() + ",'" + property.getDescription() + "',"
	                    + property.getPetFriendly() + "," + property.getAvailable() + "," + property.getRestaurante() + "," + property.getDesayuno() + "," + property.getWifi() + "," + property.getGym() + "," + property.getPiscina() + "," + property.getSpa() + ")");

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM sqlite_sequence WHERE name ='properties'");			 
				if (!rs.next()) return -1; 
				id=rs.getInt("seq");
				if (id<=lastid) return -1;
											
				logger.info("CREATING property("+id+"): "+property.getName());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return id;
	}
	public double updateGradesAverage(Property property) {
	    double done = -1;

	    if (conn != null && property!=null) {
	        ReviewDAO reviewDao = new es.unex.pi.dao.JDBCReviewDAOImpl();
	        reviewDao.setConnection(conn);
	        List<Review> reviews = reviewDao.getAllByProperty(property.getId());
	        if(reviews.size()==0) {
	        	return 0;
	        }
	        float totalVal = 0;
	        for (Review rev : reviews) {
	        	totalVal+=rev.getGrade();
	        }
	        double average=totalVal/reviews.size();


	        Statement stmt;
	        try {
	            stmt = conn.createStatement();
	            logger.info("Updating gradesAverage property: " + property.getId()+" with average:"+average);
	            stmt.executeUpdate("UPDATE properties SET gradesAverage = ROUND(" + average + ", 1) WHERE id = " + property.getId());
	            done = Math.round(average * 10d) / 10d;

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return done;
	}
	

	@Override
	public boolean update(Property property) {
	    boolean done = false;
	    if (conn != null){
	        Statement stmt;
	        try {
	            stmt = conn.createStatement();
	            stmt.executeUpdate("UPDATE properties SET name='"+property.getName()
	                +"', address='"+property.getAddress()
	                +"', telephone='"+property.getTelephone()
	                +"', idu="+property.getIdu()
	                +", gradesAverage="+property.getGradesAverage()
	                +", city='"+property.getCity()
	                +"', centerDistance="+property.getCenterDistance()
	                +", description='"+property.getDescription()                
	                +"', petFriendly="+property.getPetFriendly()
	                +", available="+property.getAvailable()
	                +", Restaurante="+property.getRestaurante()
	                +", Desayuno="+property.getDesayuno()
	                +", Wifi="+property.getWifi()
	                +", Gym="+property.getGym()
	                +", Piscina="+property.getPiscina()
	                +", Spa="+property.getSpa()
	                +" WHERE id = "+property.getId());
	            logger.info("updating property: "+property.getId());
	            done = true;
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    return done;
	}


	@Override
	public boolean delete(long id) {
		boolean done = false;
		if (conn != null){

			Statement stmt;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("DELETE FROM properties WHERE id ="+id);
				logger.info("deleting property: "+id);
				done= true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return done;
	}

	public void fromRsToPropertyObject(ResultSet rs, Property property) throws SQLException {
	    property.setId(rs.getInt("id"));
	    property.setName(rs.getString("name"));
	    property.setAddress(rs.getString("address"));
	    property.setTelephone(rs.getString("telephone"));
	    property.setIdu(rs.getInt("idu"));
	    property.setGradesAverage(rs.getDouble("gradesAverage"));
	    property.setCity(rs.getString("city"));
	    property.setCenterDistance(rs.getDouble("centerDistance"));
	    property.setDescription(rs.getString("description"));
	    property.setPetFriendly(rs.getInt("petFriendly"));
	    property.setAvailable(rs.getInt("available"));
	    property.setRestaurante(rs.getInt("Restaurante"));
	    property.setDesayuno(rs.getInt("Desayuno"));
	    property.setWifi(rs.getInt("Wifi"));
	    property.setGym(rs.getInt("Gym"));
	    property.setPiscina(rs.getInt("Piscina"));
	    property.setSpa(rs.getInt("Spa"));
	}

	
	@Override
	public void setConnection(Connection conn) {
		// TODO Auto-generated method stub
		this.conn = conn;
	}

	
}
