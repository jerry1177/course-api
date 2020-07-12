package com.beastytech.springboot.course;

import java.util.List;



public interface CourseDAO {
	
	public List<Course> getAllCourses(String topicId);
	
	public Course getCourse(String topicId, String id);
	
	public boolean addCourse(String topicId, Course course);
	
	public boolean updateCourse(String topicId, Course course, String id);

	public boolean deleteCourse(String id);

}
