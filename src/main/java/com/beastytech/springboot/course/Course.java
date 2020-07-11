package com.beastytech.springboot.course;


public class Course {

	private String id;
	private String name;
	private String description;
	
	
	
	public Course() {
		super();
		this.id = null;
		this.name = null;
		this.description = null;
	}
	
	public Course(String id, String name, String description, String topicId) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
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
