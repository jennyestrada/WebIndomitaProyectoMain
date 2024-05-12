package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import com.google.gson.Gson;

import dao.DBConexion;

import modelo.ArticleEditor;

public class ArticleEditorDao {
	
	/**
	 * Metodo para conectarnos a BDconexion
	 */
	public static Connection con = null;
	
	public ArticleEditorDao () throws SQLException {
		this.con = DBConexion.getConexion();
	}
	

	
	/**
	 * Metodo para insertar datos de un articulo en la BBDD 
	 * @param articleEditor el objeto que contiene el articulo que se va insertar.
	 */
	
	public void insert (ArticleEditor articleEditor ) throws SQLException {
		String sql = "INSERT INTO article (TITLE,TEXT,IMAGE,EXCERPT)"
				+ " VALUES (?,?,?,?)";
		PreparedStatement preparedstatement = con.prepareStatement(sql);
		
		 preparedstatement.setString(1,articleEditor.getTitle());
		 preparedstatement.setString(2,articleEditor.getText());
		 preparedstatement.setString(3,articleEditor.getImage());
		 preparedstatement.setString(4,articleEditor.getExcerpt());
		
		 
		 int rows = preparedstatement.executeUpdate();
		 preparedstatement.close();
		
	}
	
	
	/**
	 * Metodo para actualizar datos de un articulo en la BBDD 
	 * @param articleEditor el objeto que contiene el articulo que se va modificar.
	 */
	public void update (ArticleEditor articleEditor ) throws SQLException {
		String sql = "UPDATE article SET TITLE= ?, TEXT= ?, IMAGE= ?,EXCERPT= ?"
				+ " WHERE id = "+ articleEditor.getId();
		
		
			
		PreparedStatement preparedstatement = con.prepareStatement(sql);
		
		 preparedstatement.setString(1,articleEditor.getTitle());
		 preparedstatement.setString(2,articleEditor.getText());
		 preparedstatement.setString(3,articleEditor.getImage());
		 preparedstatement.setString(4,articleEditor.getExcerpt());
		
		 
		 int rows = preparedstatement.executeUpdate();
		 preparedstatement.close();
		
	}
	
	
	
	/**
	 * Metodo para borrar un articulo en la BBDD 
	 * @param articleId  contiene el Id del  articulo que se va borrar, este id viene del this.getId 
	 * de la clase ArticleEditor.
	 */
	
	public void delete (int articleId) throws SQLException {
		String sql = "UPDATE article SET deleted = 1 WHERE id = "+ articleId;
		
			
		PreparedStatement preparedstatement = con.prepareStatement(sql);
		
		 int rows = preparedstatement.executeUpdate();
		 preparedstatement.close();
		
	}
	
	
	
	
	
	/**
	 * Metodo para obtener un articulo segun el id que se le pasa como parametro.
	 * @param id del articulo que se quiere obtener.
	 * @return un articulo completo
	 */
	public ArticleEditor articleById(int id) throws SQLException {
		
		String sql = "SELECT * FROM article WHERE id = ?";
		
		PreparedStatement preparedstatement = con.prepareStatement(sql);
		preparedstatement.setInt(1, id);
		ResultSet result = preparedstatement.executeQuery();
		result.next();
		
		ArticleEditor article = new ArticleEditor(result.getInt("id"),result.getString("title"),result.getString("text"),result.getString("image"),result.getString("excerpt"));
		
		return article;	
	}

	
	

	
	
	/**
	 * Metodo para obtener la lista de los articulo ordenados en id descendiente
	 * @return devolvera una lista de articulos que debemos convertir a JSON.
	 */
	
	public ArrayList<ArticleEditor> ArticleList() throws SQLException{
		
		
		String sql = "SELECT * FROM article WHERE DELETED = 0 ORDER BY id DESC";
		
		PreparedStatement preparedstatement = con.prepareStatement(sql);
		ResultSet result = preparedstatement.executeQuery();
		
		
		ArrayList<ArticleEditor> articleList = new ArrayList<ArticleEditor>();
		
		
		while (result.next()) {
	
			articleList.add(new ArticleEditor(result.getInt("id"),result.getString("title"),result.getString("text"),result.getString("image"),result.getString("excerpt")));
								
		}	
		return 	articleList;
	}
	
	

}
