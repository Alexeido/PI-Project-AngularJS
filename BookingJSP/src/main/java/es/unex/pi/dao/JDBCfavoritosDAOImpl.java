package es.unex.pi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import es.unex.pi.model.favoritos;

public class JDBCfavoritosDAOImpl implements favoritosDAO {

	private Connection conn;


	
	@Override
	public List<favoritos> getAllByIdu(long idu) {
		
		if (conn == null) return null;
						
		ArrayList<favoritos> favoritoslist = new ArrayList<favoritos>();
		try {
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM favoritos WHERE idu="+idu);

			while ( rs.next() ) {
				favoritos fav = new favoritos();
				fromRsTofavoritosObject(rs,fav);		
				favoritoslist.add(fav);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return favoritoslist;
	}
	

	public boolean isFavourite(long idu, long idp) {
		boolean favourite=false;
		if (conn != null){
			
			Statement stmt;
			try {
				stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery("SELECT * FROM favoritos WHERE idu = " + idu + " AND idp = " + idp);
				if(rs.next()) {
					favourite= true;
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return favourite;
	}

	
	

	@Override
	public boolean add(favoritos fav) {
		boolean done = false;
		if (conn != null){
			
			Statement stmt;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("INSERT INTO favoritos (Idu,Idp) VALUES("+
									fav.getIdu()+","+
									fav.getIdp()+")");
						
				done= true;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return done;
	}

	

	@Override
	public boolean delete(long idu, long idp) {
		boolean done = false;
		if (conn != null){

			Statement stmt;
			try {
				stmt = conn.createStatement();
				stmt.executeUpdate("DELETE FROM favoritos WHERE idu ="+idu+" AND idp="+idp);
				done= true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return done;
	}

	public void fromRsTofavoritosObject(ResultSet rs,favoritos fav) throws SQLException {
		fav.setIdu(rs.getInt("idu"));
		fav.setIdp(rs.getInt("idp"));
	}
	
	@Override
	public void setConnection(Connection conn) {
		// TODO Auto-generated method stub
		this.conn = conn;
	}
	
}
