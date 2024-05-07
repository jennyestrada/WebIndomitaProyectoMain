package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.PruebaNoticias;

public class DaoNoticias {
	private static final String PreparedStatement = null;
	
	// funciona como un atributo no esta dentro de un metodo
	// Connection es una clase de java
	public static Connection con = null;
	
	// es un constructor?????
	public DaoNoticias () throws SQLException {
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
	
	public void insertdao (PruebaNoticias pruebanoticias) throws SQLException {
		String sql = "INSERT INTO noticias (TITLE,TEXT,IMAGE)VALUES (?,?,?)";
		
		PreparedStatement preparedstatement = con.prepareStatement(sql);
		
		 preparedstatement.setString(1, pruebanoticias.getTitle());
		 preparedstatement.setString(2, pruebanoticias.getText());
		 preparedstatement.setString(3, pruebanoticias.getImage());
		 
		 int rows = preparedstatement.executeUpdate();
		 preparedstatement.close();
		 
	}
	
	/*
	 * @Metodo para actualizar la informacion de la noticia, el objeto 
	 * articleEditor llama al get Id de la clase y le pasa el parametro 
	 * que se saca de la URL.
	 * creo objeto tipo PruebaNoticias para llamar al get id de la clase.
	 */
	
	public void updatedao(PruebaNoticias pruebanoticias) throws SQLException {
		String sql = "UPDATE noticias SET TITLE= ?,TEXT= ?,IMAGE= ? "
				+ "WHERE id = "+ pruebanoticias.getId();
		System.out.println("PRUEBA dao" + pruebanoticias.getId());
		
		PreparedStatement preparedstatement = con.prepareStatement(sql);
		preparedstatement.setString(1, pruebanoticias.getTitle());
		preparedstatement.setString(2, pruebanoticias.getText());
		preparedstatement.setString(3, pruebanoticias.getImage());
		int rows = preparedstatement.executeUpdate();
		 preparedstatement.close();
			
	}
	
	
	

	
	/*
	 * @Metodo para obtener y devolver todos los datos de noticia a la que corresponda el id.
	 * se crea un objeto, en este se mete la seleccion que hace el execute.
	 */
	
	public PruebaNoticias noticiasById(int id) throws SQLException {
		String sql ="SELECT * FROM noticias WHERE id=?";
		
		PreparedStatement preparedstatement = con.prepareStatement(sql);
		preparedstatement.setInt(1, id);
		ResultSet result = preparedstatement.executeQuery();
		result.next();
		
		PruebaNoticias pruebasnoticias = new PruebaNoticias(result.getInt("id"),
		result.getString("title"),result.getString("text"),result.getString("image"));
		return pruebasnoticias;
		
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
	 */
	
	
	public ArrayList<PruebaNoticias> listaNoticias () throws SQLException{
		
		String slq = "SELECT * FROM noticias WHERE DELETED = 0 ORDER BY id DESC";
		PreparedStatement ps = con.prepareStatement(slq);
		ResultSet resultado = ps.executeQuery();
		
		ArrayList<PruebaNoticias> listaNoticias = new ArrayList<PruebaNoticias>();
		
		while (resultado.next()) {
			
			listaNoticias.add(new PruebaNoticias(resultado.getInt("id"),resultado.getString("title"),resultado.getString("text"),
			resultado.getString("image")));
			
		}
		return listaNoticias;
		
		
		
		
	}
	/*
	 * @Metodo para ejecutar la slq de update para borrar.
	 * @param en este caso debe ser un int porque id es int.
	 * el valor de parametro viene desde el metodo de la clase.
	 * este sera el id al que se le asigne 1.
	 */
	public void borrarNoticia (int noticiaId) throws SQLException {
		String sql= "UPDATE noticias SET deleted = 1 WHERE id = "+ noticiaId;
		
		PreparedStatement preparedstatement = con.prepareStatement(sql);
		int row = preparedstatement.executeUpdate();
		preparedstatement.close();
	}
	
	
}
