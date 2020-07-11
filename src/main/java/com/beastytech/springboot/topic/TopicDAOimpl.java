package com.beastytech.springboot.topic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beastytech.springboot.utilities.DAOUtilities;

@Service
public class TopicDAOimpl implements TopicDAO {
	@Autowired
	DAOUtilities daoUtilities;
	
	Connection connection;
	PreparedStatement stmt;
	
	public List<Topic> getAllTopics() {
		List<Topic> topics = new ArrayList<>();
		//topicRepository.findAll().forEach(topics::add);
		try {
			connection = daoUtilities.getConnection();
			
			String sql = "SELECT * FROM Topic";
			stmt = connection.prepareStatement(sql);
			
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				topics.add(new Topic(rs.getString("id"), rs.getString("name"), rs.getString("description")));
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return topics;
	}
	
	public Topic getTopic(String id) {
		Topic topic = new Topic();
		try {
			connection = daoUtilities.getConnection();
			String sql = "SELECT * FROM Topic WHERE id = ?";
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, id);
			
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) topic = new Topic(rs.getString("id"), rs.getString("name"), rs.getString("description"));
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		
		return topic; 
	}
	
	public boolean addTopic(Topic topic) {
		try {
			connection = daoUtilities.getConnection();
			String sql = "INSERT INTO topic VALUES (?, ?, ?)"; // Were using a lot of ?'s here...
			stmt = connection.prepareStatement(sql);
			
			// But that's okay, we can set them all before we execute
			stmt.setString(1, topic.getId());
			stmt.setString(2, topic.getDescription());
			stmt.setString(3, topic.getName());
			
			return stmt.executeUpdate() != 0 ? true :  false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	
	public boolean updateTopic(String id, Topic topic) {
		try {
			connection = daoUtilities.getConnection();
			String sql = "UPDATE topic SET name=?, description=? WHERE id=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1,  topic.getName());
			stmt.setString(2, topic.getDescription());
			stmt.setString(3, id);
			
			return stmt.executeUpdate() != 0 ? true :  false;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
			
		} finally {
			closeResources();
		}
	}

	public boolean deleteTopic(String id) {
		try {
			connection = daoUtilities.getConnection();
			String sql = "DELETE FROM topic WHERE id=?";
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, id);

			return stmt.executeUpdate() != 0 ? true : false;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	
	private void closeResources() {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException e) {
			System.out.println("Could not close statement!");
			e.printStackTrace();
		}
		
		try {
			if (connection != null)
				connection.close();
		} catch (SQLException e) {
			System.out.println("Could not close connection!");
			e.printStackTrace();
		}
	}
	
}
