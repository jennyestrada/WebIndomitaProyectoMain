package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.WhoWAEditor;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class ServletWhoWA
 */
public class ServletWhoWA extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletWhoWA() {
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
	 * Metodo para insertar toda la informacion de la pagina Quienes somos.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//AQUII REQUEST.REQUEST..REQUEST
		
		String title = request.getParameter("title");
		String text = request.getParameter("text");
		String image = request.getParameter("image");// hace falta el path
		
		System.out.println(title + text + image);
		
		WhoWAEditor whowaeditor = new WhoWAEditor(title,text,image);
		
		try {
			whowaeditor.insert();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Upps!! ha habido un error al insertar datos");
		}
		
	}

}
