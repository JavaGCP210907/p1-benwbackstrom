package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class UserDao implements UserInterface {
	
	UserRoleDao urDao = new UserRoleDao();

	@Override
	public User getUserById(int id) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from users where user_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				User u = new User();
				
				u.setUser_id(rs.getInt("user_id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setFirst_name(rs.getString("user_first_name"));
				u.setLast_name(rs.getString("user_last_name"));
				u.setEmail(rs.getString("user_email"));
				
				u.setUser_role_fk(urDao.getUserRoleById(rs.getInt("user_role_fk")));
				
				return u; //return the User
			}
			
		} catch(SQLException e) {
			System.out.println("Get user by ID failed");
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public User getUserByCredentials(String username, String password) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from users where (username, password) = (?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				User u = new User();
				
				u.setUser_id(rs.getInt("user_id"));
				u.setUsername(rs.getString("username"));
				u.setPassword(rs.getString("password"));
				u.setFirst_name(rs.getString("user_first_name"));
				u.setLast_name(rs.getString("user_last_name"));
				u.setEmail(rs.getString("user_email"));
				
				u.setUser_role_fk(urDao.getUserRoleById(rs.getInt("user_role_fk")));
				
				return u; //return the User
			}
			
		} catch(SQLException e) {
			System.out.println("Get user by credentials failed");
			e.printStackTrace();
		}
		
		return null;
	}

}
