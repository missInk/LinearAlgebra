package lxx.linearAlgebra.entity;

import java.sql.Date;

public class Topic {

	private int idtopic;
	private String title;
	private String content;
	private String uname;
	/**
	 * 回复次数
	 */
	private int comment_count;
	/**
	 * 问题状态
	 * 0：未解决，1：已解决
	 */
	private int status;
	/**
	 * 问题上传时间
	 */
	private Date topic_time;
	/**
	 * 发布该问题人的id
	 */
	private int topics_user_id;
	/**
	 * 是否为新的问题
	 * 1：是，0：不是
	 */
	private int nice_topic;
	
	public Topic() {
	}
	
	public Topic(int idtopic) {
		this.idtopic = idtopic;
	}
	
	public Topic(String title, String content, int topics_user_id) {
		this.title = title;
		this.content = content;
		this.topics_user_id = topics_user_id;
	}
	
	public String getUname() {
		return uname;
	}

	public void setUname(String uname) {
		this.uname = uname;
	}

	public int getIdtopic() {
		return idtopic;
	}
	public void setIdtopic(int idtopic) {
		this.idtopic = idtopic;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getComment_count() {
		return comment_count;
	}
	public void setComment_count(int comment_count) {
		this.comment_count = comment_count;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public Date getTopic_time() {
		return topic_time;
	}
	public void setTopic_time(Date topic_time) {
		this.topic_time = topic_time;
	}
	public int getTopics_user_id() {
		return topics_user_id;
	}
	public void setTopics_user_id(int topics_user_id) {
		this.topics_user_id = topics_user_id;
	}
	public int getNice_topic() {
		return nice_topic;
	}
	public void setNice_topic(int nice_topic) {
		this.nice_topic = nice_topic;
	}
	
	
	
}
