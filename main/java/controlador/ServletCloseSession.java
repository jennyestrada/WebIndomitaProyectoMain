package controlador;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Esta tiene un metodo para cerrar la  sesión de un usuario
 */
public class ServletCloseSession extends HttpServlet {
	private static final long serialVersionUID = 1L;
	;  
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletCloseSession() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
	 * Metodo cerrar la sesion de usuario, este le dara clic al enlace Cerrar Sesion del menu superior.
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		 HttpSession session = request.getSession();
		 session.invalidate();
		 RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/index.html");
		 dispatcher.forward(request, response);
		 

		
	}
	
	
	
	
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		
		
		
	}

}
