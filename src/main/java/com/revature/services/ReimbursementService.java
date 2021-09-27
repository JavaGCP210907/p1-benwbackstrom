package com.revature.services;

import java.util.List;

import com.revature.daos.ReimbursementDao;
import com.revature.models.Reimbursement;

public class ReimbursementService {

	ReimbursementDao rDao = new ReimbursementDao();
	
	//methods that get data from the DAO and send to the Controller
	public List<Reimbursement> getAllReimbursements(){
		return rDao.getAllReimbursements();
	}

	public List<Reimbursement> getReimbursementsByEmployeeId(int id) {
		return rDao.getReimbursementsByEmployeeId(id);
	}

	public List<Reimbursement> getReimbursementsByStatus(String status) {
		return rDao.getReimbursementsByStatus(status);
	}

	public boolean addReimbursement(Reimbursement reimb) {
		return rDao.addReimbursement(reimb);
	}
	
}
