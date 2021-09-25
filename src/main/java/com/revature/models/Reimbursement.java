package com.revature.models;

import java.sql.Blob;

public class Reimbursement {

	private int reimb_id;
	private int reimb_amount;
	private String reimb_submitted;
	private String reimb_resolved;
	private String reimb_description;
	private Blob reimb_receipt;
	private User reimb_author;
	private User reimb_resolver;
	private ReimbursementStatus reimb_status_fk;
	private ReimbursementType reimb_type_fk;
	
	
	//boilerplate code --------------------------------------------
	public Reimbursement() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Reimbursement(int reimb_id, int reimb_amount, String reimb_submitted, String reimb_resolved,
			String reimb_description, Blob reimb_receipt, User reimb_author, User reimb_resolver,
			ReimbursementStatus reimb_status_fk, ReimbursementType reimb_type_fk) {
		super();
		this.reimb_id = reimb_id;
		this.reimb_amount = reimb_amount;
		this.reimb_submitted = reimb_submitted;
		this.reimb_resolved = reimb_resolved;
		this.reimb_description = reimb_description;
		this.reimb_receipt = reimb_receipt;
		this.reimb_author = reimb_author;
		this.reimb_resolver = reimb_resolver;
		this.reimb_status_fk = reimb_status_fk;
		this.reimb_type_fk = reimb_type_fk;
	}


	public Reimbursement(int reimb_amount, String reimb_submitted, String reimb_resolved, String reimb_description,
			Blob reimb_receipt, User reimb_author, User reimb_resolver, ReimbursementStatus reimb_status_fk,
			ReimbursementType reimb_type_fk) {
		super();
		this.reimb_amount = reimb_amount;
		this.reimb_submitted = reimb_submitted;
		this.reimb_resolved = reimb_resolved;
		this.reimb_description = reimb_description;
		this.reimb_receipt = reimb_receipt;
		this.reimb_author = reimb_author;
		this.reimb_resolver = reimb_resolver;
		this.reimb_status_fk = reimb_status_fk;
		this.reimb_type_fk = reimb_type_fk;
	}


	@Override
	public String toString() {
		return "Reimbursement [reimb_id=" + reimb_id + ", reimb_amount=" + reimb_amount + ", reimb_submitted="
				+ reimb_submitted + ", reimb_resolved=" + reimb_resolved + ", reimb_description=" + reimb_description
				+ ", reimb_receipt=" + reimb_receipt + ", reimb_author=" + reimb_author + ", reimb_resolver="
				+ reimb_resolver + ", reimb_status_fk=" + reimb_status_fk + ", reimb_type_fk=" + reimb_type_fk + "]";
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + reimb_amount;
		result = prime * result + ((reimb_author == null) ? 0 : reimb_author.hashCode());
		result = prime * result + ((reimb_description == null) ? 0 : reimb_description.hashCode());
		result = prime * result + reimb_id;
		result = prime * result + ((reimb_receipt == null) ? 0 : reimb_receipt.hashCode());
		result = prime * result + ((reimb_resolved == null) ? 0 : reimb_resolved.hashCode());
		result = prime * result + ((reimb_resolver == null) ? 0 : reimb_resolver.hashCode());
		result = prime * result + ((reimb_status_fk == null) ? 0 : reimb_status_fk.hashCode());
		result = prime * result + ((reimb_submitted == null) ? 0 : reimb_submitted.hashCode());
		result = prime * result + ((reimb_type_fk == null) ? 0 : reimb_type_fk.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reimbursement other = (Reimbursement) obj;
		if (reimb_amount != other.reimb_amount)
			return false;
		if (reimb_author == null) {
			if (other.reimb_author != null)
				return false;
		} else if (!reimb_author.equals(other.reimb_author))
			return false;
		if (reimb_description == null) {
			if (other.reimb_description != null)
				return false;
		} else if (!reimb_description.equals(other.reimb_description))
			return false;
		if (reimb_id != other.reimb_id)
			return false;
		if (reimb_receipt == null) {
			if (other.reimb_receipt != null)
				return false;
		} else if (!reimb_receipt.equals(other.reimb_receipt))
			return false;
		if (reimb_resolved == null) {
			if (other.reimb_resolved != null)
				return false;
		} else if (!reimb_resolved.equals(other.reimb_resolved))
			return false;
		if (reimb_resolver == null) {
			if (other.reimb_resolver != null)
				return false;
		} else if (!reimb_resolver.equals(other.reimb_resolver))
			return false;
		if (reimb_status_fk == null) {
			if (other.reimb_status_fk != null)
				return false;
		} else if (!reimb_status_fk.equals(other.reimb_status_fk))
			return false;
		if (reimb_submitted == null) {
			if (other.reimb_submitted != null)
				return false;
		} else if (!reimb_submitted.equals(other.reimb_submitted))
			return false;
		if (reimb_type_fk == null) {
			if (other.reimb_type_fk != null)
				return false;
		} else if (!reimb_type_fk.equals(other.reimb_type_fk))
			return false;
		return true;
	}


	public int getReimb_id() {
		return reimb_id;
	}


	public void setReimb_id(int reimb_id) {
		this.reimb_id = reimb_id;
	}


	public int getReimb_amount() {
		return reimb_amount;
	}


	public void setReimb_amount(int reimb_amount) {
		this.reimb_amount = reimb_amount;
	}


	public String getReimb_submitted() {
		return reimb_submitted;
	}


	public void setReimb_submitted(String reimb_submitted) {
		this.reimb_submitted = reimb_submitted;
	}


	public String getReimb_resolved() {
		return reimb_resolved;
	}


	public void setReimb_resolved(String reimb_resolved) {
		this.reimb_resolved = reimb_resolved;
	}


	public String getReimb_description() {
		return reimb_description;
	}


	public void setReimb_description(String reimb_description) {
		this.reimb_description = reimb_description;
	}


	public Blob getReimb_receipt() {
		return reimb_receipt;
	}


	public void setReimb_receipt(Blob reimb_receipt) {
		this.reimb_receipt = reimb_receipt;
	}


	public User getReimb_author() {
		return reimb_author;
	}


	public void setReimb_author(User reimb_author) {
		this.reimb_author = reimb_author;
	}


	public User getReimb_resolver() {
		return reimb_resolver;
	}


	public void setReimb_resolver(User reimb_resolver) {
		this.reimb_resolver = reimb_resolver;
	}


	public ReimbursementStatus getReimb_status_fk() {
		return reimb_status_fk;
	}


	public void setReimb_status_fk(ReimbursementStatus reimb_status_fk) {
		this.reimb_status_fk = reimb_status_fk;
	}


	public ReimbursementType getReimb_type_fk() {
		return reimb_type_fk;
	}


	public void setReimb_type_fk(ReimbursementType reimb_type_fk) {
		this.reimb_type_fk = reimb_type_fk;
	}
	
	
	
	
}
