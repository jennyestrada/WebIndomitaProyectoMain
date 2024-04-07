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
	
	// constructor
	
	public ArticleEditorDao () throws SQLException {
		this.con = DBConexion.getConexion();
	}
	
	// METODO PARA INSERTAR
	
	public void insert (ArticleEditor articleeditor ) throws SQLException {
		String sql = "INSERT INTO article (TITLE,TEXT,IMAGE,EXCERPT)"
				+ " VALUES (?,?,?,?)";// interregantes por cada valor nombre clase matricula...falta la foto
		
		PreparedStatement preparedstatement = con.prepareStatement(sql);
		
		 preparedstatement.setString(1,articleeditor.getTitle());
		 preparedstatement.setString(2,articleeditor.getText());
		 preparedstatement.setString(3,articleeditor.getImage());
		 preparedstatement.setString(4,articleeditor.getExcerpt());
		
		 
		 int rows = preparedstatement.executeUpdate();
		 preparedstatement.close();
		
	}
	
	
	
	
	// METODO PARA LISTAR MEDIANTE ARRAYLIST
	
	
	// creo un metodo con un arraylist de tipo ArticleEditor que llamo ArticleList
	public ArrayList<ArticleEditor> ArticleList() throws SQLException{
		
		// quiero que me salgas los ultimos articulos metidos, es 
		// decir los mas nuevos tendra un id mas alto.
		String slq = "SELECT * FROM article ORDER BY id DESC";
		
		// le envio la selentencia sql que acabo de hacer al PreparedStatement
		PreparedStatement preparedstatement = con.prepareStatement(slq);
		
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
	
	//creamos metodo para listar con JSON, para ello necesito la libreria
	public String articleListJson() throws SQLException {
		
		
		//genero un objeto gson de tipo Gson, 
		Gson gson = new Gson ();
		
		// llamo al metodo que crea el array articleListJson que tiene los datos de
		// traidos de la bbdd y le digo gson.toJson lo convierta formato json
		String txtJSON = gson.toJson(this.ArticleList());
		System.out.println(txtJSON);
		

		return txtJSON;
		
				
		
	}
}
