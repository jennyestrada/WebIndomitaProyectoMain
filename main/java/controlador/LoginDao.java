package controlador;

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
	
	
		public void searchLogin(Access searchlogin) throws SQLException {
			
			 String sql = "SELECT ID FROM access WHERE EMAIL = ? AND PASSWORD = ?";
					
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			
			preparedStatement.setString(1,searchlogin.getEmail());
			preparedStatement.setString(2,searchlogin.getPassword());
			
			
						
			 ResultSet resultSet = preparedStatement.executeQuery();
			 System.out.println(resultSet);
			 
			 
			 int iduser = -1;
			 
		    // Verificar si se encontró algún resultado
		    if (resultSet.next()) {
		    	// Se encontró un usuario con el email y la contraseña proporcionados
		        // Obtén el ID del usuario de la fila obtenida
		    	iduser = resultSet.getInt("ID");
		    	System.out.println("El email y la contraseña son correctos.");
		        System.out.println(iduser);
		        
		    } else {
		        // No se encontró ningún usuario con el email y la contraseña proporcionados
		        System.out.println("El email o la contraseña son incorrectos.");
		    }
			
			
		    // Cerrar recursos
		    resultSet.close();
		    preparedStatement.close();
			
		 
		}
		
	
	
	
	
	
	
	
	
	
	

}
