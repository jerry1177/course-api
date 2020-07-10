package com.beastytech.springboot.course;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.beastytech.springboot.topic.Topic;


@RestController
public class CourseController {
	
	@Autowired
	private CourseService topicService;
	
	@RequestMapping("/topics/{id}/courses")
	public List<Course> getAllTopics(@PathVariable String id) {
		return topicService.getAllCourses(id);
	}
	
	@RequestMapping("/topics/{topicId}/courses/{id}")
	public Course getCourse(@PathVariable String id) {
		return topicService.getCourse(id);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/topics/{topicId}/courses")
	public void addCourse(@PathVariable String topicId, @RequestBody Course course) {
		course.setTopic(new Topic(topicId, "", ""));
		topicService.addCourse(course);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/topics/{topicId}/courses/{id}")
	public void updateCourse(@PathVariable String topicId, @PathVariable String id, @RequestBody Course course) {
		course.setTopic(new Topic(topicId, "", ""));
		topicService.updateCourse(course);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/topics/{courseId}/courses/{id}")
	public void deleteCourse(@PathVariable String id) {
		topicService.deleteCourse(id);
	}

}
