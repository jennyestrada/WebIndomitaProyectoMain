

package modelo;

import java.sql.SQLException;
import java.util.List;

import com.google.gson.Gson;

import dao.ArticleEditorDao;



/**
 * Esta Clase Provee los metodos y atributos necesarios para gestionar los articulos.
 */
public class ArticleEditor {
	
	private int id;
	
	private String title;
	
	private String text;
	
	private String image;
	
	private String excerpt;
	
	private List<Comment> comments;
	
	
	
	
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
	
	
	public ArticleEditor(int id, String title, String text, String image) {
		super();
		this.id = id;
		this.title = title;
		this.text = text;
		this.image = image;
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


	/**
	 * Metodo para insertar informacion de un articulo.Se crea un objeto que llama
	 * al metodo insert del Dao.
	 */
	public void insert() throws SQLException {
		ArticleEditorDao articleEditordao = new ArticleEditorDao();
		articleEditordao.insert(this);
	}
	
	

	/**
	 * Metodo para actualizar informacion de un articulo. Se crea un objeto que llama
	 *  al metodo update del Dao.
	 */
	public void update() throws SQLException {
		ArticleEditorDao articleEditordao = new ArticleEditorDao();
		articleEditordao.update(this);
	}
	
	
	/**
	 * Metodo para borrar articulo. se crea un objeto que llama al metodo delete del Dao.
	 *
	 */
	public void delete() throws SQLException {
		ArticleEditorDao articleEditordao = new ArticleEditorDao();
		articleEditordao.delete(this.getId());
	}
	
	
	
	
	



	@Override
	public String toString() {
		return "ArticleEditor [title=" + title + ", text=" + text + ", image=" + image + ", excerpt=" + excerpt + "]";
	}




	public List<Comment> getComments() {
		return comments;
	}




	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}
	
	
	
	

	
	
	
	
	
	
	

}
