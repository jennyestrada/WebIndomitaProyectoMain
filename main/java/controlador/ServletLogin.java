package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Access;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Servlet implementation class ServletLogin
 */
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// atributo que inicializa objeto session.
    HttpSession session;
    
    public ServletLogin() {
        super();
       
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	// request.getSession abre la session.
		
	
		
		
		
	
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		/*
		 * @Metodo donde se recogen los (name="email")
		 * (name="password") del formulario.
		 * estos se guardan en variables y luego en un 
		 * objeto de tipo Access "userlogin". 
		 * Creo variable "accessLogin" de tipo Access, llamo al objeto "userlogin" y llamo 
		 * al metodo "searchUser" de la clase Access.
		 * 
		 */
		
		
		
		String email= request.getParameter("email");
		String password= request.getParameter("password");
		Access userlogin = new Access(password,email);
		
		
		try {
			Access accessLogin = userlogin.searchUser();
			System.out.println("prueba "+accessLogin);
			
			
			session = request.getSession();
			
				
			// lo datos pasan a estar en la session, set atributos para guardarlos	
				
				session.setAttribute("name",accessLogin.getFullname());
				session.setAttribute("id", accessLogin.getId());
				session.setAttribute("isAdmin",accessLogin.isAdmin());
				
				
				
				session.invalidate();
				
			
				if(accessLogin.getId() > 0) {
					if (accessLogin.getIsAdmin()) {
						System.out.println("Logueado admin");
						response.sendRedirect("Admiarea.html");
						
					} else if (!accessLogin.getIsAdmin()) {
						System.out.println("Logueado user");
						response.sendRedirect("Userarea.html");
						
					}
				}else {
					System.out.println("el usuario no existe o contrasenia mal");
					response.sendRedirect("Loginform.html");
					
				}
			
				
			
			
			
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
