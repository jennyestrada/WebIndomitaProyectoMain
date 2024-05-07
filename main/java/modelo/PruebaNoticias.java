package modelo;

import java.sql.SQLException;

import dao.ArticleEditorDao;
import dao.DaoNoticias;

public class PruebaNoticias {
	private int id;
	private String title;
	private String text;
	private String image;
	
	
	
	
	public PruebaNoticias() {
		super();
	}




	public PruebaNoticias(int id, String title, String text, String image) {
		super();
		this.id = id;
		this.title = title;
		this.text = text;
		this.image = image;
	}




	public PruebaNoticias(String title, String text, String image) {
		super();
		this.title = title;
		this.text = text;
		this.image = image;
	}




	public PruebaNoticias(int id) {
		super();
		this.id = id;
	}




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

	
	// espacio para anadir metodos
	
	// crea un objeto de la clase Dao dentro de insertar de la clase
	// este objeto llama al metodo insertar de la dao y de parametro se
	// pasa a si mismo.
	
	public void insert() throws SQLException {
		DaoNoticias daonoticias = new DaoNoticias();
		daonoticias.insertdao(this);
	}
	
	/*
	 * @Metodo para borrar articulo.
	 * creamos objeto tipo DaoNoticias y llamamos a su metodo "delete"
	 * de parametro le pasamos el get id de la clase.
	 * ahora este metodo "borrarNoticia" lo llamamos desde el doPost del Servlet.
	 */
	
	public void delete () throws SQLException {
		DaoNoticias daonoticias = new DaoNoticias();
		daonoticias.borrarNoticia(this.getId());
	}
	
	
	public void update() throws SQLException {
		DaoNoticias daonoticias = new DaoNoticias();
		daonoticias.updatedao(this);
	}

	


	@Override
	public String toString() {
		return "PruebaNoticias [id=" + id + ", title=" + title + ", text=" + text + ", image=" + image + "]";
	}
	
	
	

	
	
}

