package com.beastytech.springboot.topic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.beastytech.springboot.utilities.Message;



@RestController
public class TopicController {
	
	@Autowired
	private TopicDAOimpl topicService;
	
	@RequestMapping("/topics")
	public Object getAllTopics() {
		
		return topicService.getAllTopics();
	}
	
	@RequestMapping("/topics/{id}")
	public Object getTopic(@PathVariable String id) {		
		Topic topic = topicService.getTopic(id);
		
		return topic.getName() == null ? new Message("Could not find topic!")
				:
				topic;
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/topics")
	public Object addTopic(@RequestBody Topic topic) {
		
		return topicService.addTopic(topic) ? new Message("Successfully added topic!") 
				:
				new Message("Could not add topic!");
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/topics/{id}")
	public Object updateTopic(@PathVariable String id, @RequestBody Topic topic) {
		
		return topicService.updateTopic(id, topic) ? new Message("Successfully added topic!")
				:
				new Message("Could not add topic!");
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/topics/{id}")
	public Object deleteTopic(@PathVariable String id) {
		
		return topicService.deleteTopic(id) ? new Message("Successfully added topic!")
				:
				new Message("Could not add topic!");
	}

}
