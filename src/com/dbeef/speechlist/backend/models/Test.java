package com.dbeef.speechlist.backend.models;

import javax.persistence.Entity;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement(name = "test")
public class Test {
	private static final long serialVersionUID = 1L;

	private int id;
	private int uniqueId;
	private String name;
	private String[] vocabulary;
	private String[] sentences;

	public Test() {
	}

	public int getUniqueId() {
		return uniqueId;
	}

	@XmlElement
	public void setUniqueId(int uniqueId) {
		this.uniqueId = uniqueId;
	}

	public int getId() {
		return id;
	}

	@XmlElement
	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public String[] getVocabulary() {
		return vocabulary;
	}

	@XmlElement
	public void setVocabulary(String[] vocabulary) {
		this.vocabulary = vocabulary;
	}

	public String[] getSentences() {
		return sentences;
	}

	@XmlElement
	public void setSentences(String[] sentences) {
		this.sentences = sentences;
	}
}