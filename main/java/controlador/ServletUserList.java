package controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import modelo.Access;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

import org.eclipse.jdt.internal.compiler.ast.ThisReference;

import com.google.gson.Gson;

import dao.AccessDao;

/**
 * Servlet implementation class ServletUserList
 */
public class ServletUserList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletUserList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// creamos variable de tipo string con la respuesta que envia JSON 
		
		String responseJson="";
		
		try {
			responseJson = new AccessDao().convertJson();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// una vez lo vemos en formato json llamamos al objeto PrinWriter para 
		//que se vea en la pantalla del usuario.
		// con el obejeto response llamamo a su metodo getWiter
		
		PrintWriter printWriterResponse = response.getWriter();
		printWriterResponse.print(responseJson);
		
		
		
		
		
		
		
		
		
		
		// esto solo impirme los dato en consola, necesitamos convertir esta info a formato JSON 
		
		/*try {
		ArrayList<Access> accessList = new AccessDao().userList();
		
		for (Access a : accessList) {
			System.out.println(a.toString());
			
		}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
		}
		*/
		
		
		
	}
	
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
