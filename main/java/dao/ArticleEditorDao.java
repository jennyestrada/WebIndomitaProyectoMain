package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import dao.DBConexion;
import modelo.ArticleEditor;

public class ArticleEditorDao {
	
	public static Connection con = null;
	
	// constructor
	
	public ArticleEditorDao () throws SQLException {
		this.con = DBConexion.getConexion();
	}
	
	
	
	public void insert (ArticleEditor articleeditor ) throws SQLException {
		String sql = "INSERT INTO article (TITLE,TEXT,IMAGE)"
				+ " VALUES (?,?,?)";// interregantes por cada valor nombre clase matricula...falta la foto
		
		PreparedStatement preparedstatement = con.prepareStatement(sql);
		
		 preparedstatement.setString(1,articleeditor.getTitle());
		 preparedstatement.setString(2,articleeditor.getText());
		 preparedstatement.setString(3,articleeditor.getImage());
		 
		 int rows = preparedstatement.executeUpdate();
		 preparedstatement.close();
		 
		
	
		
	}
}
