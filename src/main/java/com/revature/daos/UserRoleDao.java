package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.UserRole;
import com.revature.utils.ConnectionUtil;

public class UserRoleDao implements UserRoleInterface {

	@Override
	public UserRole getUserRoleById(int id) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from userroles where user_role_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				UserRole ur = new UserRole();
				
				ur.setUser_role_id(rs.getInt("user_role_id"));
				ur.setUser_role(rs.getString("user_role"));
				
				return ur;
			}
			
		} catch(SQLException e) {
			System.out.println("Get user role by ID failed");
			e.printStackTrace();
		}
		
		return null;
	}

}
