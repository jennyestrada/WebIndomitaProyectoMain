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
	
	public static Connection con = null;
	
	/*
	 * @Metodo para conectarnos a BDconexion.
	 */
	
	public ArticleEditorDao () throws SQLException {
		this.con = DBConexion.getConexion();
	}
	

	/*
	 * @Metodo insertar datos de articulo en la BBDD 
	 */
	
	
	public void insert (ArticleEditor articleEditor ) throws SQLException {
		String sql = "INSERT INTO article (TITLE,TEXT,IMAGE,EXCERPT)"
				+ " VALUES (?,?,?,?)";// interregantes por cada valor nombre clase matricula...falta la foto
		
		PreparedStatement preparedstatement = con.prepareStatement(sql);
		
		 preparedstatement.setString(1,articleEditor.getTitle());
		 preparedstatement.setString(2,articleEditor.getText());
		 preparedstatement.setString(3,articleEditor.getImage());
		 preparedstatement.setString(4,articleEditor.getExcerpt());
		
		 
		 int rows = preparedstatement.executeUpdate();
		 preparedstatement.close();
		
	}
	
	/*
	 * @Metodo para actualizar la informacion del articulo, el objeto 
	 * articleEditor llama al get Id de la clase y le pasa el parametro 
	 * que se saca de la URL.
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
	
	
	/*
	 * @Metodo para borrar un articulo, cerramos el PreparedStatement 
	 * para liberar recursos y evitar posibles pérdidas de memoria 
	 * o conexiones no cerradas.
	 */
	
	public void delete (int articleId) throws SQLException {
		String sql = "UPDATE article SET deleted = 1 WHERE id = "+ articleId;
		
			
		PreparedStatement preparedstatement = con.prepareStatement(sql);
		
		 int rows = preparedstatement.executeUpdate();
		 preparedstatement.close();
		
	}
	
	
	
	
	
	
	/*
	 * @Metodo para obtener datos de articulos al que corresponda el id. 
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

	
	
	
	
	/*
	 * @Metodo para obtener datos y meterlos en el arrylista de tipo ArticleEditor
	 * mediante un select ordenando los datos en orden descendiente.
	 */
	
	
	public ArrayList<ArticleEditor> ArticleList() throws SQLException{
		
		// quiero que me salgas los ultimos articulos metidos, es 
		// decir los mas nuevos tendra un id mas alto.
		
		String sql = "SELECT * FROM article WHERE DELETED = 0 ORDER BY id DESC";
		
		// le envio la selentencia sql que acabo de hacer al PreparedStatement
		PreparedStatement preparedstatement = con.prepareStatement(sql);
		
		// recupeero los datos mediante la conexipon Resutset y loe meto en la variable result
		// con el executeQuery me devuelve datos.
		ResultSet result = preparedstatement.executeQuery();
		
		// creo un arraylist donde pasare los datos que trae el ResultSet result
		ArrayList<ArticleEditor> articleList = new ArrayList<ArticleEditor>();
		
		// le digo recorre la coleccion resulset de tipo ResultSet, al usa next recorre de fila en fila
		// y nos va dando los datos de cada una
		
		while (result.next()) {
	
			// al array article  de tipo ArticleEditor, creo una nueva nava para darle 
			// toda la fila que me devuelve 
			// el orden de id title tex .... es el mismo que el la BBDD
			
			articleList.add(new ArticleEditor(result.getInt("id"),result.getString("title"),result.getString("text"),result.getString("image"),result.getString("excerpt")));
						
				
		}	
		return 	articleList;
	}
	
	

}
