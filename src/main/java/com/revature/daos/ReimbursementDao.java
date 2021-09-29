package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
						//rs.getBlob("reimb_receipt"),
						null,
						null,
						null,
						null
				);
				
				if(rs.getInt("reimb_author") != 0) { //if the SQL field is not NULL
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
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from reimbursements where reimb_author = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
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
						//rs.getBlob("reimb_receipt"),
						null,
						null,
						null,
						null
				);
				
				if(rs.getInt("reimb_author") != 0) { //if the SQL field is not NULL
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
			
			return reimbsList; //return employee's past tickets
			
		} catch(SQLException e) {
			System.out.println("Get reimbursement by id failed");
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public List<Reimbursement> getReimbursementsByStatus(String status) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			String sql = "select * from reimbursements inner join reimbursementsstatuses "
						+ "on reimb_status_fk = reimb_status_id where reimb_status = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, status);
			
			ResultSet rs = ps.executeQuery();
			
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
						//rs.getBlob("reimb_receipt"),
						null,
						null,
						null,
						null
				);
				
				if(rs.getInt("reimb_author") != 0) { //if the SQL field is not NULL
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
			System.out.println("Get reimbursements by status failed");
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public boolean addReimbursement(Reimbursement reimb) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String currentTimeStamp = dateFormat.format(date);
			
			String sql = "insert into reimbursements (reimb_amount, reimb_submitted, reimb_description, reimb_author, reimb_status_fk, reimb_type_fk)"
						+ " values (?, ?, ?, ?, ?, ?)";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, reimb.getReimb_amount());
			ps.setTimestamp(2, java.sql.Timestamp.valueOf(currentTimeStamp));
			ps.setString(3, reimb.getReimb_description());
			//ps.setBlob(4, reimb.getReimb_receipt());
			ps.setInt(4, reimb.getReimb_author().getUser_id());
			ps.setInt(5, 1); //Requests should start off as Pending
			ps.setInt(6, reimb.getReimb_type_fk().getReimb_type_id());
			
			ps.executeUpdate();
			
			System.out.println("Ticket successfully added");
			return true;
			
		} catch(SQLException e) {
			System.out.println("Ticket could not be added");
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean approveReimbursement(int rId, int mId) {
		//rId = reimb_id and mId is user_id of the financial manager
		try(Connection conn = ConnectionUtil.getConnection()){
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String currentTimeStamp = dateFormat.format(date);
			
			String sql = "update reimbursements set (reimb_resolved, reimb_resolver, reimb_status_fk) = (?, ?, ?) where reimb_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setTimestamp(1, java.sql.Timestamp.valueOf(currentTimeStamp));
			ps.setInt(2, mId);
			ps.setInt(3, 2);
			ps.setInt(4, rId);
			
			ps.executeUpdate();
			
			return true;
			
		} catch(SQLException e) {
			System.out.println("Could not resolve this ticket");
			e.printStackTrace();
		}
		
		return false;
	}

	@Override
	public boolean rejectReimbursement(int rId, int mId) {
		
		try(Connection conn = ConnectionUtil.getConnection()){
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date date = new Date();
			String currentTimeStamp = dateFormat.format(date);
			
			String sql = "update reimbursements set (reimb_resolved, reimb_resolver, reimb_status_fk) = (?, ?, ?) where reimb_id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setTimestamp(1, java.sql.Timestamp.valueOf(currentTimeStamp));
			ps.setInt(2, mId);
			ps.setInt(3, 3);
			ps.setInt(4, rId);
			
			ps.executeUpdate();
			
			return true;
			
		} catch(SQLException e) {
			System.out.println("Could not resolve this ticket");
			e.printStackTrace();
		}
		
		return false;
	}

}
