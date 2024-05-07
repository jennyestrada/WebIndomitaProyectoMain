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
	
	/*
	 * @Metodo para conectarnos a BDconexion, usamos la clase de java 
	 * Connection
	 */
	public static Connection con = null;
	
	public ArticleEditorDao () throws SQLException {
		this.con = DBConexion.getConexion();
	}
	

	/*
	 * @Metodo insertar datos de articulo en la BBDD 
	 * usamos el PreparedStatement preparedstatement
	 * interregantes por cada valor title, text....
	 * 
	 * creamo un objeto de tipo ArticleEditor llamado articleEditor y 
	 * usando los getters llamamos al title text image...
		
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
	 * o conexiones no cerradas.Metodo que se llamara desde 
	 * el Servlet mediante un doGet.
	 * @param en este caso debe ser un int porque id es int.
	 * el valor de este viene desde el metodo delete de la clase.
	 * este sera el id al que se le asigne 1 en la tabla.
	 */
	
	
	public void delete (int articleId) throws SQLException {
		String sql = "UPDATE article SET deleted = 1 WHERE id = "+ articleId;
		
			
		PreparedStatement preparedstatement = con.prepareStatement(sql);
		
		 int rows = preparedstatement.executeUpdate();
		 preparedstatement.close();
		
	}
	
	
	
	
	
	/*
	 * @Metodo para obtener y devolver todos los datos del articulo al que corresponda 
	 * el id.Se crea un objeto, en este se mete la seleccion que hace el execute.
	 * por tanto este metodo nos devuelve toda la inform. que compone del articulo.
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
	 * @Metodo para obtener datos y meterlos en el arrylist de tipo ArticleEditor
	 * mediante un select ordenando los datos en orden descendiente.
	 * y que tome aquellos cuya columna deleted sea 0.
	 * 
	 * Envio la selentencia sql que acabo de hacer al PreparedStatement.
	 * Ejecuto, mediante el "executeQuery" y lo guardo el la variable de tipo
	 * ResulSet "result"
	 * 
	 * Creo un ArrayList "arraylist" aqui meto los datos que trae el "ResultSet result"
	 * 
	 * El "arraylist" creado anteriormente, lo meto dentro del bucle while
	 * y le digo que mientras hayan filas llenas en la variable "result",saque 
	 * los datos de esta y los añada en el mismo orden en el que
	 * tenemos ordenada las columnas en la BBDD. 
	 * 
	 * Nos devolvera una coleccion que debemos convertir a JSON en el 
	 * doGet del Servlet.
	 * 
	 * Ojo, los SELECT nos devuelven un ResultSet. 
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
