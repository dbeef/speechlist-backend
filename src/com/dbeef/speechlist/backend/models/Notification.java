package com.dbeef.speechlist.backend.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "notification")
public class Notification implements Serializable {

	private static final long serialVersionUID = 1L;
	private int id;
	private String type;
	private String content;

	public Notification() {
	}

	public int getId() {
		return id;
	}

	@XmlElement

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	@XmlElement
	public void setType(String type) {
		this.type = type;
	}

	public String getContent() {
		return content;
	}

	@XmlElement
	public void setContent(String content) {
		this.content = content;
	}

}