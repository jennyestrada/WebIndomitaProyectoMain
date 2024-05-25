package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Access;

import modelo.Utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;



/**
 * Esta clase gestion la sesion de un usuario
 */
public class ServletLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// atributo que inicializa objeto session.
    HttpSession session;
    
    public ServletLogin() {
        super();
       
    }
    

	

    /**
	 * Metodo para sacar lo atributos isAdmin y fullname de la sesion
	 * @return isAdmin y fullname convertidos a JSON
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// saco los datos de admini y nombre de la sesion que cree en el POST.
		session = request.getSession();
		System.out.println("PRUEBA "+ session);
		
		
		boolean isAdmin = ((boolean)session.getAttribute("isAdmin"));
		String name =((String)session.getAttribute("name"));
		
		
		// creo obejto y le asigno los valores sacados de los sesion id de admin y name
		Access accessLogin = new Access();
		accessLogin.setIsAdmin(isAdmin);
		accessLogin.setFullname(name);
		
		// ahora el obejto lo convetimos a JSON.
		
		String convertidoAJson = new Utils().convertToJson(accessLogin);
		PrintWriter out = response.getWriter();
		out.print(convertidoAJson);
		System.out.println("prueba "+convertidoAJson);
		
		
		
		
	
		
		
		
	
	}
	
	
	
	/**
	 * Metodo para recoger los datos de acceso de un usuario y gurdarlos en una session.
	 * aqui se comprueba mediante el boolean isAdmin si es administrador o usuario.
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
		
		
		
		// recogemos los datos de los campos email y password
		// y se meten el un objetouserlogin de tipo Access.
		String email= request.getParameter("email");
		String password= request.getParameter("password");
		Access userlogin = new Access(password,email);
		
		
		try {
			// Ahora con el objeto userlogin llamamos al metodo searchUser de la clase, este se comunica con 
			// el Dao, la dao devuelve el objeto accessLogin con el id fullname isAdmin. ver metodo serachUser de la DAO.
			// lo uque trae este metodo lo guardamos en la variable.
			Access accessLogin = userlogin.searchUser();
			//System.out.println("prueba "+accessLogin);
			
			// Creamos un objeto sesion o abrimos una sesion
			session = request.getSession();
			
			// Se extraen el email y password para pasarlo a la session.
				
				session.setAttribute("name",accessLogin.getFullname());
				session.setAttribute("id", accessLogin.getId());
				session.setAttribute("isAdmin",accessLogin.getIsAdmin());
				
			// ahora el emai y password esta en la session guardados, el siguiente paso 
			// es comprobar si cumple nuetsras condiciones.
				
			// si el id es mayor que cero entra en el primer if
				
				if(accessLogin.getId() > 0) {
					// si isAdmin es true (1) entonces entra y llevalo a la pagina de admnistracion
					if (accessLogin.getIsAdmin()) {
						System.out.println("Logueado admin");
						response.sendRedirect("Admiarea.html");
						//System.out.println("prueba para ver que devuelve " + accessLogin.getId());
					} else {
						// si isAdmin es false (0) entonces es un usuario y llevalo a la pagina de User area
						System.out.println("Logueado user");
						response.sendRedirect("Userarea.html");
						//System.out.println("prueba para ver que devuelve " + accessLogin.getId());
						
					}
				}else {
					// si id no es mayor que cero, esta mal le envio al el login otra vez.
					System.out.println("el usuario no existe o contrasenia mal");
					response.sendRedirect("Loginform.html");
					
				}
			
		
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}

}
