package modelo;

public class Comment {
	private int id;
	private String comment;
	private Access commentUser;
	

	
	public Comment() {
		super();
	}

	

	
	/**
	 * metodo constructor para obtener comentario y el autor del comentario
	 */
	
	public Comment(String comment, Access commentUser) {
		super();
		this.comment = comment;
		this.commentUser = commentUser;
	}
	
	
	
	public String getComment() {
		return comment;
	}
	

	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Access getcommentUser() {
		return commentUser;
	}
	public void setcommentUser(Access commentUser) {
		this.commentUser = commentUser;
	}
	
	
	
	
	
	
	
	
}