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
	
}
