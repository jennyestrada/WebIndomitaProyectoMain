package modelo;

import java.sql.SQLException;

import dao.WhoWADao;




/**
 * Esta Clase Provee los metodos y atributos necesarios para gestionar quienes somos.
 */
public class WhoWAEditor {
	private String title;
	private String text;
	private String image;
	
	
	
	
	
	public WhoWAEditor() {
		super();
	}



	public WhoWAEditor(String title, String text, String image) {
		super();
		this.title = title;
		this.text = text;
		this.image = image;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public String getText() {
		return text;
	}



	public void setText(String text) {
		this.text = text;
	}



	public String getImage() {
		return image;
	}



	public void setImage(String image) {
		this.image = image;
	}


	
	public void insert() throws SQLException {
		WhoWADao whowadao = new WhoWADao();
		whowadao.insert(this);
	}

	@Override
	public String toString() {
		return "WhoWAEditor [title=" + title + ", text=" + text + ", image=" + image + "]";
	}
	
	
	
	
	

}
