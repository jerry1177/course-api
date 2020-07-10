package com.beastytech.springboot.course;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class CourseService {
	@Autowired
	private CourseRepository courseRepository;
	
	public List<CourseModel> getAllCourses(String topicId) {
		return  getCourseModels(courseRepository.findByTopicId(topicId));
	}
	
	public CourseModel getCourse(String id) {
		CourseModel courseModel = new CourseModel();
		Optional<Course> oTopic = courseRepository.findById(id);
		
		if (!oTopic.isEmpty()) 
			courseModel = getCourseModel(oTopic.get());
		
		return courseModel;
		
	}
	
	private CourseModel getCourseModel(Course course) {	
		return new CourseModel(course.getId(), course.getName(), course.getDescription());		
	}
	
	private List<CourseModel> getCourseModels(List<Course> courses) {
		List<CourseModel> courseModels = new ArrayList<>();
		
		for (Course course : courses)
			courseModels.add(new CourseModel(course.getId(), course.getName(), course.getDescription()));
		
		return courseModels;
	}
	
	public void addCourse(Course course) {
		courseRepository.save(course);
	}
	
	public void updateCourse(Course course) {
		courseRepository.save(course);
	}

	public void deleteCourse(String id) {
		courseRepository.deleteById(id);
	}
	
}
