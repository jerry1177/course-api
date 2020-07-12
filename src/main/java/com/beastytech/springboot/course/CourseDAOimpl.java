package com.beastytech.springboot.course;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.beastytech.springboot.utilities.DAOUtilities;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Service
public class CourseDAOimpl implements CourseDAO {
	
	@Autowired
	DAOUtilities daoUtilities;
	
	Connection connection;
	PreparedStatement stmt;
	
	@Override
	public List<Course> getAllCourses(String topicId) {
		List<Course> courses = new ArrayList<>();
		
		try {
			connection = daoUtilities.getConnection();
			
			String sql = "SELECT id, description, name FROM public.course where topic_id=?";
			
			stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, topicId);
			
			ResultSet rs = stmt.executeQuery();
			
			while (rs.next()) 
				courses.add(new Course(rs.getString("id"), rs.getString("name"), rs.getString("description")));
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			closeResources();
		}
		
		return courses;
	}
	@Override
	public Course getCourse(String topicId, String id) {
		Course course = new Course();
		try {
			connection = daoUtilities.getConnection();
			
			String sql = "SELECT id, description, name FROM public.course WHERE topic_id=? AND id=?";
			
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, topicId);
			stmt.setString(2, id);
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) 
				course = new Course(rs.getString("id"), rs.getString("name"), rs.getString("description"));
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			closeResources();
		}
		return course;
	}
	@Override
	public boolean addCourse(String topicId, Course course) {
		try {
			connection = daoUtilities.getConnection();
			String sql = "INSERT INTO public.course(id, description, name, topic_id) VALUES (?, ?, ?, ?)";
			
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, course.getId());
			stmt.setString(2, course.getDescription());
			stmt.setString(3, course.getName());
			stmt.setString(4, topicId);
			
			return stmt.executeUpdate() != 0 ? true : false;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
	}
	@Override
	public boolean updateCourse(String topicId, Course course, String id) {
		System.out.println("updated");
		try {
			connection = daoUtilities.getConnection();
			String sql = "UPDATE public.course SET description=?, name=? WHERE id=? AND topic_id=?";
			
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, course.getDescription());
			stmt.setString(2, course.getName());
			stmt.setString(3, id);
			stmt.setString(4, topicId);
			
			return stmt.executeUpdate() != 0 ? true : false;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} finally {
			closeResources();
		}
		
	}
	@Override
	public boolean deleteCourse(String id) {
		try {
			connection = daoUtilities.getConnection();
			String sql = "DELETE FROM public.course WHERE id=?";
			
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, id);
			
			return stmt.executeUpdate() != 0 ? true : false;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
