package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConexion {
	//CREAMOS CLASE INDEPENDIENTE QUE VA ESTABLECER CONEXION ENTRE JAVA Y BBDD
	// FINAL ES PORQUE ES UN DATO ESTACTICO QUE NO CAMBIARA 
	
	
	// FINAL ES POR QUE ES ESTATICO NO CAMBIA.
	public static final String JDBC_URL = "jdbc:mysql://localhost:3306/webindomita";
									// 3306 es el puerto // nombre la BBDD
			
	//CREAMOS EL OBJETO CONEXION		
	public static Connection instance = null;
	
	public static Connection getConexion() throws SQLException{
		if (instance == null){
			
			
			// ESTOS DATOS SON OPCIONALES
			Properties props = new Properties ();
			props.put("user", "root");
			props.put("password", "");
			props.put("charset", "UTF-8");
			
			// root es usuario "" espacio sin texto es sin contrasenia
			instance = DriverManager.getConnection(JDBC_URL, props);
			
			
		}
		
		return instance;
	


	}
}