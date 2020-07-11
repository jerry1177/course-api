package com.beastytech.springboot.topic;

import java.util.List;
import java.util.Optional;

public interface TopicDAO {
	
	public List<Topic> getAllTopics();
	
	public Topic getTopic(String id);
	
	public boolean addTopic(Topic topic);
	
	public boolean updateTopic(String id, Topic topic);

	public boolean deleteTopic(String id);
	

}
