package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.google.gson.Gson;

import modelo.Access;

public class AccessDao {
	// patron Singelton aun mejor. son soluciones genericas a problemas que ya estan hechos.
	
	public static Connection con = null;
	
	public AccessDao() throws SQLException {
		this.con = DBConexion.getConexion();
	}
	
	
	// creo metodo insertar con un variable que llamare tambien access
	// pero puede llamarse de otra manera y sera de tipo Access
	
	public void insert(Access access) throws SQLException {
		
		String sql = "INSERT INTO access (FULLNAME,PASSWORD,EMAIL)"
				+ " VALUES (?,?,?)";// interregantes por cada valor nombre clase matricula...falta la foto
		PreparedStatement preparedStatement = con.prepareStatement(sql);
		
		preparedStatement.setString(1,access.getFullname());
		preparedStatement.setString(2,access.getPassword());
		preparedStatement.setString(3,access.getEmail());
		
					
		int filas = preparedStatement.executeUpdate();
		preparedStatement.close();
		
		// NO HACE FALTA CERRAR CONEXION PORQUE DBConexion ES UN OBJETO, CUANDO 
		// DEJE DE FUNCIONAR EL RECOGERDOR JAVA SE LO CARGA.
	}
	
	
	private ArrayList<Access> userList() throws SQLException{
		
		PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM access");
		ResultSet rs = preparedStatement.executeQuery();
		
		ArrayList<Access> result = new ArrayList<Access>();
		
		while(rs.next()) {
			result.add(new Access(rs.getInt("ID"),rs.getString("FULLNAME"),rs.getString("PASSWORD"),rs.getString("EMAIL")));
			
		}
		
		return result;
	}
	
	// necesitamos convertir la info a un formato JSON  creamo el metodo.
		public String convertJson() throws SQLException {
			String convertJson= " ";
			Gson gson= new Gson();
			convertJson = gson.toJson(this.userList());
			System.out.println(convertJson);
			return convertJson;
		}

}
