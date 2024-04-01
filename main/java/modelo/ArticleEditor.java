package modelo;

import java.sql.SQLException;

import dao.ArticleEditorDao;

public class ArticleEditor {
	private int id;
	private String title;
	private String text;
	private String image;
	
	
	
	
	
	public ArticleEditor() {
		super();
	}
	
	


	// Sin id
	public ArticleEditor(String title, String text, String image) {
		super();
		this.title = title;
		this.text = text;
		this.image = image;
	}
	
	
	// Con id
	
	public ArticleEditor(int id, String title, String text, String image) {
		super();
		this.id = id;
		this.title = title;
		this.text = text;
		this.image = image;
	}
	
	
	
	
	
	
	
	// creo metodo insert en la clase ArticleEditor 
	
	
	
	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
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
		ArticleEditorDao articleEditordao = new ArticleEditorDao();
		articleEditordao.insert(this);
	}
	
	
	
	

	@Override
	public String toString() {
		return "ArticleEditor [title=" + title + ", text=" + text + ", image=" + image + "]";
	}
	
	
	
	
	
	
	

}
