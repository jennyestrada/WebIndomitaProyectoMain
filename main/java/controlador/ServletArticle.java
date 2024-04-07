package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import modelo.ArticleEditor;
import modelo.Utils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;

/**
 * Servlet implementation class ServletArticle
 */
@MultipartConfig
public class ServletArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	// creo obejto uploads de tipo File y le doy como parametro pathFiles que es el que tiene la ruta guradada
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		// request solo lo lee como string 
		
		String title = request.getParameter("title");
		String text = request.getParameter("text");
		//String image = request.getParameter("image");// hace falta el path
		
		Part part = request.getPart("image");
		// nos leer los datos binarios de la imagen insertada, 
		
		/*Path path = Paths.get(part.getSubmittedFileName());
		// nos da el nombre del archivo original
		
		String fileName = path.getFileName().toString();
		// path tiene la ubicac de la imagen, la convierte en string y guarda en fileName
		
		// fileName es lo que usare en bbdd o el contrutor.
		
		
		InputStream input = part.getInputStream();
		//CREAMOS EL CAMINO PARA EL INTERCAMBIO DE DATOS, BUFFER
		
		File file = new File (uploads,fileName);
		
		// copiamos la lectura de la imagen que esta en input en file
		
		try {
			Files.copy(input,file.toPath());
		}catch (Exception e) {
			System.out.println("Upps no se ha copiado la foto");
			PrintWriter error = response.getWriter();
			error.print("<h3>Upp error a subir la foto, contactar con el administrador</h3> ");
			
		}
		*/
		
		// en esta variable fileName guardamos el los datos que nos creo el metodo getImage
	    String fileName = Utils.getImage(part);
	    
	    String excerpt = request.getParameter("excerpt");
	
		System.out.println(title + text + fileName + excerpt );
		
		ArticleEditor articleeditor = new ArticleEditor (title,text,fileName,excerpt);
		
		try {
			articleeditor.insert();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Upps!! ha habido un error al insertar datos");
		}
			
		
}
}