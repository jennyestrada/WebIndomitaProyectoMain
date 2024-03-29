package modelo;


import java.sql.SQLException;

import dao.AccessDao;



public class Access {
    private int id=0;
	private String fullname;
    private String password;
    private String email;
    
    
    
    
  	public Access() {
  		super();
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
	
	
	public void insert() throws SQLException {
		AccessDao accessdao = new AccessDao ();
		accessdao.insert(this);
	}



	@Override
	public String toString() {
		return "Access [fullname=" + fullname + ", password=" + password + ", email=" + email + "]";
	}
	
	
	
	
    
    
    
   
    
    




}




