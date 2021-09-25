package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.ReimbursementStatus;
import com.revature.utils.ConnectionUtil;

public class ReimbursementStatusDao implements ReimbursementStatusInterface {

	@Override
	public ReimbursementStatus getStatusById(int id) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from reimbursementsstatuses where reimb_status_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				ReimbursementStatus rStatus = new ReimbursementStatus(
						rs.getInt("reimb_status_id"),
						rs.getString("reimb_status")
						);
				
				return rStatus;
			}
			
		} catch(SQLException e) {
			System.out.println("Get status by ID failed");
			e.printStackTrace();
		}
		
		return null;
	}

}
