package modelo;


import java.sql.SQLException;


import dao.AccessDao;
import dao.LoginDao;



public class Access {
    private int id=0;
	private String fullname;
    private String password;
    private String email;
    private boolean isAdmin;
    
    
    
  

	public Access() {
  		super();
  	}
	
	
	
	
  	


	// para usar en el post del servlet
  	public Access(String password, String email) {
		super();
		this.password = password;
		this.email = email;
	}



 // para usar en el login 
	public Access(int id, String fullname, boolean isAdmin) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.isAdmin = isAdmin;
	}
	
	

	public boolean isAdmin() {
		return isAdmin;
	}



	// sin id
	public Access(String fullname, String password, String email) {
		super();
		this.fullname = fullname;
		this.password = password;
		this.email = email;
	}




	// con id
	public Access(int id, String fullname, String password, String email) {
		super();
		this.id = id;
		this.fullname = fullname;
		this.password = password;
		this.email = email;
	}





	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}



	public String getFullname() {
		return fullname;
	}



	public void setFullname(String fullname) {
		this.fullname = fullname;
	}



	public String getPassword() {
		return password;
	}



	public void setPassword(String password) {
		this.password = password;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}
	

	
	public boolean getIsAdmin() {
		return isAdmin;
	}
	
	
	
	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}







	public void insert() throws SQLException {
		AccessDao accessdao = new AccessDao ();
		accessdao.insert(this);
	}
	
	
	public Access searchUser() throws SQLException {
		AccessDao accessdao = new AccessDao ();
		 Access accessLogin = accessdao.searchLogin(this);
		return accessLogin;
	}



	@Override
	public String toString() {
		return "Access [id=" + id + ", fullname=" + fullname + ", password=" + password + ", email=" + email
				+ ", isAdmin=" + isAdmin + "]";
	}
	
	

	
	
	
	
    
    
    
   
    
    




}




