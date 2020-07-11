package com.beastytech.springboot.course;

import java.util.List;

import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;

@Service
public class CourseService {
	
	public List<Course> getAllCourses(String topicId) {
		return  null;//courseRepository.findByTopicId(topicId);
	}
	
	public Course getCourse(String id) {
		return null;//courseRepository.findById(id).get();
	}
	
	public void addCourse(Course course) {
		//courseRepository.save(course);
	}
	
	public void updateCourse(Course course) {
		//courseRepository.save(course);
	}

	public void deleteCourse(String id) {
		//courseRepository.deleteById(id);
	}
	
}
