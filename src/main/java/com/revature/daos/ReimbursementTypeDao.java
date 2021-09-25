package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.ReimbursementType;
import com.revature.utils.ConnectionUtil;

public class ReimbursementTypeDao implements ReimbursementTypeInterface {

	@Override
	public ReimbursementType getTypeById(int id) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from reimbursementtypes where reimb_type_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				ReimbursementType rType = new ReimbursementType(
						rs.getInt("reimb_type_id"),
						rs.getString("reimb_type")
						);
				
				return rType;
			}
			
		} catch(SQLException e) {
			System.out.println("Get type by ID failed");
			e.printStackTrace();
		}
		
		return null;
	}

}
