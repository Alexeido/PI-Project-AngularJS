package es.unex.pi.dao;

import java.sql.Connection;
import java.util.List;

import es.unex.pi.model.favoritos;


public interface favoritosDAO {

	/**
	 * set the database connection in this DAO.
	 * 
	 * @param conn
	 *            database connection.
	 */
	public void setConnection(Connection conn);

	/**
todos los favoritos de un usuario
	 */
	

	public List<favoritos> getAllByIdu(long idu);
	

//añadadir favorito
	
	public boolean add(favoritos fav);


//eliminar favorito

	
	public boolean delete(long idp, long idu);
}