package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import modelo.WhoWAEditor;

public class WhoWADao {
	
	public static Connection con = null;
	
	public WhoWADao() throws SQLException {
		this.con = DBConexion.getConexion();
	}
	
	public void insert(WhoWAEditor whowaeditor) throws SQLException {
		String sql = "INSERT INTO whoware (TITLE,MAIN_TEXT,IMAGE) "
				+ "VALUES (?,?,?)";
		
		PreparedStatement preparedstatement = con.prepareStatement(sql);
		
		preparedstatement.setString(1, whowaeditor.getTitle());
		preparedstatement.setString(2, whowaeditor.getText());
		preparedstatement.setString(3, whowaeditor.getImage());
		
		int rows = preparedstatement.executeUpdate();
		preparedstatement.close();
	}

}
