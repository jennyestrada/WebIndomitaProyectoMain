package controlador;


// estas librerias las buscamos en buil path - project facets - rutimes selecionamos el servidor que usamos
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Access;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Servlet implementation class ServletAccess
 */
public class ServletAccess extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public ServletAccess() {
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
	 * Metodo para obtener los valores de los campos del formulario de registro 
	 * y enviarlos al metodo insert de AccessDao.
	 * @param name, password, email
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		String name = request.getParameter("name");
		String email= request.getParameter("email");
		String password= request.getParameter("password");
		
		
		System.out.println(name + email + password);
		
		
		// en los parametros name email .. se guardan los valores de tipo string que se obtuvieron por el request
		
		Access access = new Access(name, password, email);
		System.out.println(access.toString());
		
		try {
			access.insert();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Error al insertar datos");
		}
		
	}
	


}
