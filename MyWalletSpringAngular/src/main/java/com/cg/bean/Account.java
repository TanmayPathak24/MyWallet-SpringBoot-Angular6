package com.cg.bean;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * @author Tanmay Pathak
 * */

@Entity
@Table(name="Account")
public class Account {
	private Integer id;
	@Id
	private Long mobileno;
	private String holdername;
	private Double balance;
	
	public Account() { 
	}

	public Account(Integer id, Long mobileno, String holdername, Double balance) {
		super();
		this.id = id;
		this.mobileno = mobileno;
		this.holdername = holdername;
		this.balance = balance;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Long getMobileno() {
		return mobileno;
	}

	public void setMobileno(Long mobileno) {
		this.mobileno = mobileno;
	}

	public String getHoldername() {
		return holdername;
	}

	public void setHoldername(String holdername) {
		this.holdername = holdername;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [id=" + id + ", mobileno=" + mobileno + ", holdername=" + holdername + ", balance=" + balance
				+ "]";
	}
	
	
	
}