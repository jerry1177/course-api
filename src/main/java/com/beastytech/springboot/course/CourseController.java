package com.beastytech.springboot.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.beastytech.springboot.utilities.Message;



@RestController
public class CourseController {
	
	@Autowired
	private CourseDAOimpl courseDAO;
	
	@RequestMapping("/topics/{id}/courses")
	public List<Course> getAllTopics(@PathVariable String id) {
		return courseDAO.getAllCourses(id);
	}
	
	@RequestMapping("/topics/{topicId}/courses/{id}")
	public Object getCourse(@PathVariable String topicId, @PathVariable String id) {
		Course course = courseDAO.getCourse(topicId, id);
		
		return course.getId() != null ?
				course
				:
				new Message("Could not find course");
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/topics/{topicId}/courses")
	public Message addCourse(@PathVariable String topicId, @RequestBody Course course) {
		
		return courseDAO.addCourse(topicId, course) ? 
				new Message("Successfully added course")
			:
				new Message("Could not add course");
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/topics/{topicId}/courses/{id}")
	public Message updateCourse(@PathVariable String topicId, @PathVariable String id, @RequestBody Course course) {
		return courseDAO.updateCourse(topicId, course, id) ? 
				new Message("Successfully updated course")
			:
				new Message("Could not update course");
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/topics/{topicId}/courses/{id}")
	public Message deleteCourse(@PathVariable String id) {
		return courseDAO.deleteCourse(id) ? 
				new Message("Successfully deleted course")
			:
				new Message("Could not delete course");
	}

}
