package dao;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Access;
import modelo.ArticleEditor;


/**
 * Esta clase Dao conecta y trabaja con los datos de los articulos favoritos de los usuarios
 * almacenados en la  BBDD de la aplicacion
 */

public class ArtiFavoritesDao {
	
public static Connection con = null;
	
	/*
	 * @Metodo para conectarnos a BDconexion.
	 */
	public ArtiFavoritesDao () throws SQLException {
		this.con = DBConexion.getConexion();
	}
	
	
	/*
	 * @Metodo enviar el id de usuario y id article a la BBDD
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
	
	/*
	 * @ metodo para selecionar los articulos favoritos ascociados al Id logueado.
	 * 
	 */
	
	public ArrayList<ArticleEditor> articleFavoritesList(int idUser) throws SQLException{
		ArrayList<ArticleEditor> favoriteArticles = new ArrayList<>();
	    
		
	    String sql = "SELECT a.ID, a.TITLE, a.TEXT, a.IMAGE, a.EXCERPT FROM article a " +
                "INNER JOIN favorite_articles af ON a.ID= af.ID_ARTICLE " +
                "WHERE af.ID_ACCESS = ? AND a.DELETED = 0 " +
                "ORDER BY a.id DESC";
	    
	    
	    PreparedStatement preparedStatement = con.prepareStatement(sql);
	    preparedStatement.setInt(1, idUser);
	    ResultSet result = preparedStatement.executeQuery();
	    
	    
	    
	    while (result.next()) {
            ArticleEditor article = new ArticleEditor(
                result.getInt("id"),
                result.getString("title"),
                result.getString("text"),
                result.getString("image"),
                result.getString("excerpt")
            );
            favoriteArticles.add(article);
        }
		
	    return favoriteArticles;	 
	}
	
	    
	    
	   
	  
}
	
	 


