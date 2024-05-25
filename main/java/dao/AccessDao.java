package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Access;



/**
 * Esta clase Dao conecta y trabaja con los datos de acceso almacenados en la BBDD de la aplicacion
 */
public class AccessDao {
	// patron Singelton aun mejor. son soluciones genericas a problemas que ya estan hechos.
	
	public static Connection con = null;
	
	public AccessDao() throws SQLException {
		this.con = DBConexion.getConexion();
	}
	
	
	
	/**
	 * Metodo para insertar datos de registro de un usuario
	 * @param access objeto con los datos de fullname, password, email
	 */
	
	public void insert(Access access) throws SQLException {
		
		String sql = "INSERT INTO access (FULLNAME,PASSWORD,EMAIL)"
				+ " VALUES (?,?,?)";// interregantes por cada valor nombre clase matricula...falta la foto
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		
		preparedStatement.setString(1,access.getFullname());
		preparedStatement.setString(2,access.getPassword());
		preparedStatement.setString(3,access.getEmail());
		
					
		int filas = preparedStatement.executeUpdate();
		preparedStatement.close();

		// No hace falta cerrar conexion porque DBConexion es un objeto, cuando deje de fucnionar
		// el recogedor de java se lo carga
	}
	
	
	
	/**
	 * Metodo para traer lista de usuarios registrados
	 */
	private ArrayList<Access> userList() throws SQLException{
		
		PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM access");
		ResultSet rs = preparedStatement.executeQuery();
		
		ArrayList<Access> result = new ArrayList<Access>();
		
		while(rs.next()) {
			result.add(new Access(rs.getInt("ID"),rs.getString("FULLNAME"),rs.getString("PASSWORD"),rs.getString("EMAIL")));
			
		}
		
		return result;
	}
	
		
	
	
		/**
		 * Metodo para verificar si el email y contrasenia existen en
		 * la bbdd,si existe, toma en id fullname isAdmin y lo trae.
		 * @param Access searchlogin objeto que trae el email y password.
		 * @return objeto con id fullname y isAdmin
		 */
	
		public Access searchLogin(Access searchlogin) throws SQLException {
			
			String sql = "SELECT ID, FULLNAME, ISADMIN  FROM access WHERE EMAIL = ? AND PASSWORD = ?";
					
			PreparedStatement preparedStatement = con.prepareStatement(sql);
			
			preparedStatement.setString(1,searchlogin.getEmail());
			preparedStatement.setString(2,searchlogin.getPassword());
						
			ResultSet resultSet = preparedStatement.executeQuery();
			System.out.println(resultSet);
			
			
		    if (resultSet.next()) {
		    	// si esta en la bbdd devuelve objeto lleno.
		    	System.out.println("El email y la contraseña son correctos.");
		    	Access 	accessLogin = new Access(resultSet.getInt("id"),resultSet.getString("fullname"),resultSet.getBoolean("isAdmin")); 
		    	resultSet.close();
				preparedStatement.close();
		    	return accessLogin;    	
		   
		    } else {
		        System.out.println("El email o la contraseña son incorrectos.");
		    }
			
		    resultSet.close();
		    preparedStatement.close();
		    // si no hay nada en bbdd devuelve objeto vacio.
		    return new Access();
		 
		}
		
		
		
		

}
