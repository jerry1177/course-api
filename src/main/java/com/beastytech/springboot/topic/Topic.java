package com.beastytech.springboot.topic;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.beastytech.springboot.course.Course;

@Entity
@Table(name="topic")
public class Topic {
	@Id
	private String id;
	private String name;
	private String description;
	
	@OneToMany(mappedBy="topic")
	List<Course> courses;
	
	
	public Topic() {
		super();
		this.id = null;
		this.name = null;
		this.description = null;
		courses = new ArrayList<>();
	}
	
	public Topic(String id, String name, String description) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		courses = new ArrayList<>();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
