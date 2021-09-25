package com.revature.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Reimbursement;
import com.revature.utils.ConnectionUtil;

public class ReimbursementDao implements ReimbursementInterface {
	
	UserDao uDao = new UserDao();
	ReimbursementStatusDao rsDao = new ReimbursementStatusDao();
	ReimbursementTypeDao rtDao = new ReimbursementTypeDao();

	@Override
	public List<Reimbursement> getAllReimbursements() {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from reimbursements";
			
			Statement s = conn.createStatement();
			
			ResultSet rs = s.executeQuery(sql);
			
			List<Reimbursement> reimbsList = new ArrayList<>();
			
			//populate ArrayList
			while(rs.next()) {
				//make a new Reimbursement object for each row
				Reimbursement r = new Reimbursement(
						rs.getInt("reimb_id"),
						rs.getInt("reimb_amount"),
						rs.getString("reimb_submitted"),
						rs.getString("reimb_resolved"),
						rs.getString("reimb_description"),
						rs.getString("reimb_receipt"),
						null,
						null,
						null,
						null
				);
				
				if(rs.getInt("reimb_author") != 0) {
					r.setReimb_author(uDao.getUserById(rs.getInt("reimb_author")));
				}
				if(rs.getInt("reimb_resolver") != 0) {
					r.setReimb_resolver(uDao.getUserById(rs.getInt("reimb_resolver")));
				}
				if(rs.getInt("reimb_status_fk") != 0) {
					r.setReimb_status_fk(rsDao.getStatusById(rs.getInt("reimb_status_fk")));
				}
				if(rs.getInt("reimb_type_fk") != 0) {
					r.setReimb_type_fk(rtDao.getTypeById(rs.getInt("reimb_type_fk")));
				}
				
				reimbsList.add(r); //add Reimbursement object to ArrayList
				
			}
			
			return reimbsList;
			
		} catch(SQLException e) {
			System.out.println("Get all reimbursements failed");
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Reimbursement> getReimbursementsByEmployeeId(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Reimbursement> getReimbursementsByStatus(String status) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addReimbursement(Reimbursement reimb) {
		// TODO Auto-generated method stub
		return false;
	}

}
