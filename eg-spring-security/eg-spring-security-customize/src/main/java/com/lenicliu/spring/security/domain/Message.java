package com.lenicliu.spring.security.domain;

import java.util.Date;

public class Message {

	private Long	id;
	private Long	uid;
	private String	content;
	private Date	created;
	private Date	updated;

	public Message() {
	}

	public Message(Message message) {
		if (message != null) {
			this.setId(message.getId());
			this.setUid(message.getUid());
			this.setContent(message.getContent());
			this.setCreated(message.getCreated());
			this.setUpdated(message.getUpdated());
		}
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUid() {
		return uid;
	}

	public void setUid(Long uid) {
		this.uid = uid;
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

	public Date getUpdated() {
		return updated;
	}

	public void setUpdated(Date updated) {
		this.updated = updated;
	}
	
	@Override
	public String toString() {
		return "Message [id=" + id + ", uid=" + uid + ", content=" + content + ", created=" + created + ", updated=" + updated + "]";
	}
}