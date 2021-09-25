package com.revature.daos;

import java.util.List;

import com.revature.models.Reimbursement;

public interface ReimbursementInterface {
	
	public List<Reimbursement> getAllReimbursements();
	//Financial Managers need to view all reimbursements
	
	public List<Reimbursement> getReimbursementsByEmployeeId(int id);
	//Employees need to view their own past tickets
	
	public List<Reimbursement> getReimbursementsByStatus(String status);
	//Financial managers must be able to filter tickets by their status
	
	public boolean addReimbursement(Reimbursement reimb);
	//Employees must be able to add a Reimbursement request
	
	public boolean approveReimbursement(int rId, int mId);
	
	public boolean rejectReimbursement(int rId, int mId);
	//Managers must be able to approve/deny reimbursement requests
	
}
