package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ArtiFavoritesDao {
	
public static Connection con = null;
	
	/*
	 * @Metodo para conectarnos a BDconexion.
	 */
	public ArtiFavoritesDao () throws SQLException {
		this.con = DBConexion.getConexion();
	}
	
	/*
	 * @Metodo enviar el id de access y id article a la BBDD
	 */
	
	public void insertFavorites (int idAccess, int idArticle) throws SQLException {
		String sql = "INSERT INTO favorite_articles (ID_ACCESS,ID_ARTICLE)"
				+ " VALUES (?,?)";
		
		PreparedStatement preparedstatement = con.prepareStatement(sql);
		
		 preparedstatement.setInt(1,idAccess);
		 preparedstatement.setInt(2,idArticle);
		 int rows = preparedstatement.executeUpdate();
		 preparedstatement.close();
		
			 
	}
	
	
	
	
	
	
	
	

}
