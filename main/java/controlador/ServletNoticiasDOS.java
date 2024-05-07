package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import modelo.PruebaNoticias;
import modelo.Utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import dao.DaoNoticias;

/**
 * Servlet implementation class ServletNoticiasDOS
 */
@MultipartConfig
public class ServletNoticiasDOS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletNoticiasDOS() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		/*
		 * @Metodo para pedir los datos de articulo,
		 * que ha traido el dao de la bbdd y convertirlos a Json
		 * en mi caso cree un metodo convertToJson en la clase Utils
		 * 
		 * parseInt id porque el atributo id en la clase es tipo int
		 * y en el paramentro viene como String.
		 * 
		 * creo objeto y llamo al Dao y el metodo que tiene que
		 * devuelve la noticia completa, creo otro objeto
		 * y llamo al metodo que convierte a JSON, le paso el objeto
		 * noticiasAJson como parametro.
		 * la clase de java PrintWriter imprime archivos de texto formateados.
		 * 
		 */
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			PruebaNoticias noticiasAJson = new DaoNoticias().noticiasById(id);
			String convertidoAJson = new Utils().convertToJson(noticiasAJson);
			PrintWriter out = response.getWriter();
			out.print(convertidoAJson);
			System.out.println(convertidoAJson);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		/*
		 * @Metodo request solo lo lee como string por ello
		// las imagenes las pasamos a string, en mi caso este
		// conversion la hace un metodo de la clase Utils. 
		 * OJO importante usamos enctype="multipart/form-data 
		 * asi que en el servlet ponemos @MultipartConfig
		 * para que lea el part.
		 */
		
		int op = Integer.parseInt(request.getParameter("op"));
		;
		
		if (op == 1 || op == 2) {
	
			String title = request.getParameter("title");
			String text = request.getParameter("text");
			Part part = request.getPart("image");
			String filename = Utils.getImage(part);
			
			
			
			
			
			if (op == 1) {
				PruebaNoticias pruebanoticias = new PruebaNoticias(title,text,filename);
				try {
					pruebanoticias.insert();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else if(op == 2) {
				
				int id = Integer.parseInt(request.getParameter("id"));
				PruebaNoticias pruebanoticias = new PruebaNoticias(id,title,text,filename);
				System.out.println("PRUEBA" +title + text +filename);
					try {
						pruebanoticias.update();
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			
			}
			
		}else if(op == 3){
		
		/*
		 * @Metodo para eliminar, este llama el metodo "delete" de la clase 
		 * este a su vez ya ha llamado al Dao y su metodo "borrarNoticia"
		 * donde se ejecuta la query, este tiene el ID que queremos borrar.
		 * 
		 */
	
		int id = Integer.parseInt(request.getParameter("id"));
		PruebaNoticias pruebanoticias = new PruebaNoticias(id);
		try {
			pruebanoticias.delete();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
	}
	
	

}
