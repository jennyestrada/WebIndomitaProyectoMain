package modelo;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import jakarta.servlet.http.Part;

public class Utils {
	
	public static String getImage(Part part) throws IOException {
		

		String pathFiles = "C:\\DAW\\Proyectos\\ProyectoWebIndomita\\src\\main\\webapp\\images";
		// donde guardare los archivos
		
		File uploads = new File (pathFiles);
		
		Path path = Paths.get(part.getSubmittedFileName());
		String originalFileName = path.getFileName().toString();
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
	}
}

