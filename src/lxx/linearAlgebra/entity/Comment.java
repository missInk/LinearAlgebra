package lxx.linearAlgebra.entity;

/**
 * @author asus
 *
 */
public class Comment {

	private int idComment;
	private String content;
	private int floor;
	private String comment_time;
	private int comment_user_id;
	private int comment_topic_id;
	private int status;
	private String uname;

	public Comment() {
	}

	public Comment(String content, int comment_user_id, int comment_topic_id) {
		super();
		this.content = content;
		this.comment_user_id = comment_user_id;
		this.comment_topic_id = comment_topic_id;
	}

	public Comment(int idComment, String content, int floor, String comment_time, int comment_user_id,
			int comment_topic_id, int status) {
		super();
		this.idComment = idComment;
		this.content = content;
		this.floor = floor;
		this.comment_time = comment_time;
		this.comment_user_id = comment_user_id;
		this.comment_topic_id = comment_topic_id;
		this.status = status;
	}

	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public int getIdComment() {
		return idComment;
	}

	public void setIdComment(int idComment) {
		this.idComment = idComment;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getFloor() {
		return floor;
	}

	public void setFloor(int floor) {
		this.floor = floor;
	}

	public String getComment_time() {
		return comment_time;
	}

	public void setComment_time(String comment_time) {
		this.comment_time = comment_time;
	}

	public int getComment_user_id() {
		return comment_user_id;
	}

	public void setComment_user_id(int comment_user_id) {
		this.comment_user_id = comment_user_id;
	}

	public int getComment_topic_id() {
		return comment_topic_id;
	}

	public void setComment_topic_id(int comment_topic_id) {
		this.comment_topic_id = comment_topic_id;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
