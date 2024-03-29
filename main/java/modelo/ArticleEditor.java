package modelo;

import java.sql.SQLException;

import dao.ArticleEditorDao;

public class ArticleEditor {
	private String title;
	private String text;
	private String image;
	
	
	
	
	
	public ArticleEditor() {
		super();
	}



	public ArticleEditor(String title, String text, String image) {
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


	
	
	
	
	// creo metodo insert en la clase ArticleEditor 
	
	
	
	public void insert() throws SQLException {
		ArticleEditorDao articleEditordao = new ArticleEditorDao();
		articleEditordao.insert(this);
	}
	
	

	@Override
	public String toString() {
		return "ArticleEditor [title=" + title + ", text=" + text + ", image=" + image + "]";
	}
	
	
	
	
	
	
	

}
