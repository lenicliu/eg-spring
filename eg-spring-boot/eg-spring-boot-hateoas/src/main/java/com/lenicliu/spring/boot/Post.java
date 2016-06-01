package com.lenicliu.spring.boot;

import java.io.Serializable;
import java.util.Date;

public class Post implements Serializable {

	private static final long serialVersionUID = 6851179850516169610L;

	public enum State {
		PUBLISH, DRAFT;
	}

	private Long	id;
	private String	title;
	private String	author;
	private Date	created;
	private State	state;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public State getState() {
		return state;
	}

	public void setState(State state) {
		this.state = state;
	}
}