package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dao.DBConexion;
import modelo.Access;

public class LoginDao {
	
public static Connection con = null;
	
	public LoginDao() throws SQLException {
		this.con = DBConexion.getConexion();
	}
		/*
		 * @Metodo para verificar si el email y contrasenia existen en
		 * la bbdd,si existe, toma en id del usuario y retorna 
		 * el valor del Id, este se guarda en la varible iduser. 
		 * desde el Servlet (doPost) llamo a este metodo.
		 * finalmente cerramos recursos con el resultSet.close
		 * y preparedStatement.close.
		 */
	
		public int searchLogin(Access searchlogin) throws SQLException {
			
			String sql = "SELECT ID FROM access WHERE EMAIL = ? AND PASSWORD = ?";
					
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			
			preparedStatement.setString(1,searchlogin.getEmail());
			preparedStatement.setString(2,searchlogin.getPassword());
			
			
						
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println(resultSet);
			 
			 
			int iduser = -1;
			 
		    if (resultSet.next()) {
		    	
		    	iduser = resultSet.getInt("ID");
		    	System.out.println("El email y la contraseña son correctos.");
		        System.out.println(iduser);
		    } else {
		        System.out.println("El email o la contraseña son incorrectos.");
		    }
			
		    resultSet.close();
		    preparedStatement.close();
			return iduser;
		 
		}
		
	
	
	
	
	
	
	
	
	
	

}


