package modelo;

import java.sql.SQLException;

import com.google.gson.Gson;

import dao.ArticleEditorDao;

public class ArticleEditor {
	private int id;
	private String title;
	private String text;
	private String image;
	private String excerpt;
	
	
	
	
	
	
	public ArticleEditor() {
		super();
	}
	
	


	// Sin id
	public ArticleEditor(String title, String text, String image,String excerpt) {
		super();
		this.title = title;
		this.text = text;
		this.image = image;
		this.excerpt = excerpt;
	}
	
	
	// Con id
	
	public ArticleEditor(int id, String title, String text, String image,String excerpt) {
		super();
		this.id = id;
		this.title = title;
		this.text = text;
		this.image = image;
		this.excerpt = excerpt;
	}
	
	

	
	public ArticleEditor(int id) {
		super();
		this.id = id;
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
	
	


	public String getExcerpt() {
		return excerpt;
	}




	public void setExcerpt(String excerpt) {
		this.excerpt = excerpt;
	}


	/*
	 * @Metodo para insertar informacion en el formulario articulo
	 */
	public void insert() throws SQLException {
		ArticleEditorDao articleEditordao = new ArticleEditorDao();
		articleEditordao.insert(this);
	}
	
	

	/*
	 * @Metodo para actualizar informacion de un articulo
	 */
	public void update() throws SQLException {
		ArticleEditorDao articleEditordao = new ArticleEditorDao();
		articleEditordao.update(this);
	}
	
	/*
	 * @Metodo para borrar articulo.
	 * creamos objeto tipo ArticleEditorDao y llamamos a su metodo "delete"
	 * de parametro le pasamos el get id de la clase.
	 * ahora este metodo "delete" lo llamamos desde el doPost del Servlet.
	 */
	
	public void delete() throws SQLException {
		ArticleEditorDao articleEditordao = new ArticleEditorDao();
		articleEditordao.delete(this.getId());
	}
	
	
	
	
	



	@Override
	public String toString() {
		return "ArticleEditor [title=" + title + ", text=" + text + ", image=" + image + ", excerpt=" + excerpt + "]";
	}
	
	
	
	

	
	
	
	
	
	
	

}
