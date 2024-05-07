package modelo;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

import com.google.gson.Gson;

import jakarta.servlet.http.Part;

public class Utils<T> {
	
	public static String getImage(Part part) throws IOException {
		

		String pathFiles = "C:\\DAW\\Proyectos\\ProyectoWebIndomita\\src\\main\\webapp\\images";
		// donde guardare los archivos
		
		File uploads = new File (pathFiles);
		
		Path path = Paths.get(part.getSubmittedFileName());
		String originalFileName = path.getFileName().toString();
		if(originalFileName != "") {
			String extension = originalFileName.substring(originalFileName.lastIndexOf("."));
			// path tiene la ubicac de la imagen, la convierte en string y guarda en fileName
			
			String fileName = java.util.UUID.randomUUID().toString()+extension;
			// El ramdomUUID nos identifica las img mediante un id para que sean unicas.
			// path tiene la ubicac de la imagen, conviertela en string y guardala en fileName
			
			// fileName es lo que usare en bbdd o el contrutor.
			
			
			InputStream input = part.getInputStream();
			//CREAMOS EL CAMINO PARA EL INTERCAMBIO DE DATOS, BUFFER
			
			File file = new File (uploads,fileName);
			
			// copiamos la lectura de la imagen que esta en input en file
			
			try {
				Files.copy(input,file.toPath());
			}catch (Exception e) {
			
				
			}
			
			return fileName;	
		}else {
			return "";
		}
		
	
	}
	
	/*
	 * @Metodo para convertir los datos que trae el arraylist a JSON
	 * necesitamos importar librerias.tengo que crear un objeto para llamarla
	 * porque no me deja crearla como static.
	 */
	
	public String convertToJson(T object) {
		
		
		//genero un objeto gson de tipo Gson, 
		Gson gson = new Gson ();
		
		// llamo al metodo que crea el array articleListJson que tiene los datos de
		// traidos de la bbdd y le digo gson.toJson lo convierta formato json
		String txtJSON = gson.toJson(object);
		System.out.println(txtJSON);
		

		return txtJSON;
		
				
		
	}
}

