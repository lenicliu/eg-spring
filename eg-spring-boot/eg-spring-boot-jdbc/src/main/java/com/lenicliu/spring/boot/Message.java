package com.lenicliu.spring.boot;

import java.util.Date;

public class Message {
	private String	id;
	private String	author;
	private String	email;
	private String	content;
	private Date	created;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", author=" + author + ", email=" + email + ", content=" + content + ", created=" + created + "]";
	}
}