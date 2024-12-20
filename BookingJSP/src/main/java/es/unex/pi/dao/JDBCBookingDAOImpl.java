package es.unex.pi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import es.unex.pi.model.Accommodation;
import es.unex.pi.model.Booking;
import es.unex.pi.model.BookingsAccommodations;


public class JDBCBookingDAOImpl implements BookingDAO {

	private Connection conn;
	private static final Logger logger = Logger.getLogger(JDBCBookingDAOImpl.class.getName());
	
	@Override
	public Booking get(long id) {
		if (conn == null) return null;
		
		Booking booking = null;		
		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM bookings WHERE id ="+id);			 
			if (!rs.next()) return null; 
			booking  = new Booking();	 
			fromRsToBookingObject(rs,booking);
			logger.info("fetching Booking by id: "+id+" -> "+booking.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return booking;
	}
	
	@Override
	public Booking get(long idu,int totalPrice){
		if (conn == null) return null;
		Booking booking = null;		
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM bookings WHERE idu ="+idu + " AND totalPrice =" + totalPrice);			 
			if (!rs.next()) return null; 
			booking  = new Booking();	 
			fromRsToBookingObject(rs,booking);
			logger.info("fetching Booking id: "+booking.getId());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return booking;
	}

	
	public List<Booking> getAll() {

		if (conn == null) return null;
		
		ArrayList<Booking> bookings = new ArrayList<Booking>();
		try {
			Statement stmt;
			ResultSet rs;
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM bookings");
			while ( rs.next() ) {
				Booking booking = new Booking();
				fromRsToBookingObject(rs,booking);
				bookings.add(booking);
				logger.info("fetching Bookings: "+booking.getId());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookings;
	}
	

	public List<Booking> getUserBooks(long idu){

		if (conn == null) return null;
		
		ArrayList<Booking> bookings = new ArrayList<Booking>();
		try {
			Statement stmt;
			ResultSet rs;
			stmt = conn.createStatement();
			rs = stmt.executeQuery("SELECT * FROM bookings WHERE idu="+idu);
			while ( rs.next() ) {
				Booking booking = new Booking();
				fromRsToBookingObject(rs,booking);
				if(booking!=null) {
					bookings.add(booking);
					logger.info("fetching Bookings: "+booking.getId());
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bookings;
		
	}
	
	
	@Override
	public long add(Booking booking) {
		long id=-1;
		long lastid=-1;
		if (conn != null){

			Statement stmt;
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM sqlite_sequence WHERE name ='bookings'");			 
				if (!rs.next()) return -1; 
				lastid=rs.getInt("seq");
								
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("INSERT INTO bookings (idu,totalPrice) VALUES(" + booking.getIdu() +", " + booking.getTotalPrice()+ ")");
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM sqlite_sequence WHERE name ='bookings'");			 
				if (!rs.next()) return -1; 
				id=rs.getInt("seq");
				if (id<=lastid) return -1;
											
				logger.info("CREATING Booking("+id+"): ");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return id;
	}

	@Override
	public boolean update(Booking booking) {
		boolean done = false;
		if (conn != null){
			
			Statement stmt;
			try {
				stmt = conn.createStatement();
				
				stmt.executeUpdate("UPDATE bookings SET id = " + booking.getId() +", idu= " + booking.getIdu()+ ", totalPrice= " + booking.getTotalPrice() 
				+ " WHERE id = "+booking.getId());
				
				logger.info("updating Booking: "+booking.getId());
				done= true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return done;
	}

	public boolean updateTotalPrice(Booking b) {
	    boolean done = false;

	    if (conn != null && b!=null) {
	        BookingsAccommodationsDAO accommodationsBookingsDao = new es.unex.pi.dao.JDBCBookingsAccommodationsDAOImpl();
	        accommodationsBookingsDao.setConnection(conn);
	        AccommodationDAO accommodationsDao = new es.unex.pi.dao.JDBCAccommodationDAOImpl();
	        accommodationsDao.setConnection(conn);
	        List<BookingsAccommodations> accom = accommodationsBookingsDao.getAllByBooking(b.getId());
	        long totalPrice = 0;
	        for (BookingsAccommodations ac : accom) {
	            Accommodation j = accommodationsDao.get(ac.getIdacc());
	            long price = j.getPrice() * ac.getNumAccommodations();
	            totalPrice += price;
	        }


	        Statement stmt;
	        try {
	            stmt = conn.createStatement();
	            stmt.executeUpdate("UPDATE bookings SET totalPrice = " + totalPrice + " WHERE id = " + b.getId());
	            logger.info("Updating totalPrice Booking: " + b.getId());
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
				stmt.executeUpdate("DELETE FROM bookings WHERE id = " + id);
				
				logger.info("deleting Booking: "+id);
				
				done= true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return done;
	}
	
	public void fromRsToBookingObject(ResultSet rs, Booking booking) throws SQLException  {
		booking.setId(rs.getInt("id"));
		booking.setIdu(rs.getInt("idu"));
		booking.setTotalPrice(rs.getInt("totalPrice"));
	}

	@Override
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	
}
