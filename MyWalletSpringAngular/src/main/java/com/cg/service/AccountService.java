package com.cg.service;

import java.util.List;
/**
 * Author: Tanmay Pathak
 * */
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.cg.bean.Account;
import com.cg.bean.Response;
import com.cg.dao.AccountDAO;
import com.cg.exception.ApplicationException;

@Service
@Transactional
public class AccountService implements Gst, Transaction {

	// To link the service and dao layer
	@Autowired
	private AccountDAO dao;
	
	// Used for testing purpose
	public void setAccountDAOImpl(AccountDAO dao) {
		this.dao = dao;
	}
	
	
	
	@Transactional(propagation = Propagation.REQUIRED) 
	public Response withdraw(Account account, double amount) throws ApplicationException{
		if(amount <0) throw new ApplicationException("Can not enter negative amount");
		if(amount == 0) throw new ApplicationException("Amount can not be zero");
		if((account.getBalance() - amount)<1000) {
			throw new ApplicationException("Balance goes below 1000, Transaction can not be processed");
		}
		account.setBalance(account.getBalance()-amount);
		dao.saveAndFlush(account);
		return new Response(200, "Transaction Completed "+amount+" withdrawl from your account",null);
	}

	
	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Response deposit(Account account, double amount) throws ApplicationException{
		if(amount <0) throw new ApplicationException("Can not enter negative amount");
		if(amount == 0) throw new ApplicationException("Amount can not be zero");
		if(amount < 0.0) 
			throw new ApplicationException("Amount less than 0, Transaction can not be processed");
		account.setBalance(account.getBalance()+amount);
		dao.saveAndFlush(account);
		return new Response(200, "Transaction Completed "+amount+" deposited to your account", null);
	}

	
	@Transactional(propagation = Propagation.REQUIRED)
	public Response transferMoney(Account from, Account to, double amount) throws ApplicationException{
		if(amount <0) throw new ApplicationException("Can not enter negative amount");
		if(amount<0.0)
			throw new ApplicationException("Amount is less than 0, Transaction can not be processed");
		else if((from.getBalance()-amount)<1000)
			throw new ApplicationException("Balance goes below 1000, Transaction can not be processed");
		from.setBalance(from.getBalance()-amount);
		to.setBalance(to.getBalance()+amount);
		dao.saveAndFlush(from);
		dao.saveAndFlush(to);
		return new Response(200,"Transaction Completed "+amount+" transfered successfully", null); 
	}

	
	
	public double calculateTax(double PCT, double amount) {
		return amount * Gst.PCT_5;
	}

	
	
	@Transactional(propagation = Propagation.REQUIRED)
	public Response addAccount(Account account) throws ApplicationException{
		if(!validateID(account.getId()))
			throw new ApplicationException("Account ID is invalid");
		else if(!validateName(account.getHoldername()))
			throw new ApplicationException("Account Holder Name is invalid");
		else if(!validateMobileNumber(account.getMobileno()))
			throw new ApplicationException("Account Mobile Number is invalid");
		else if(!validateBalance(account.getBalance()))
			throw new ApplicationException("Given Balance is invalid");
		else if(account.getBalance()<1000.0)
			throw new ApplicationException("Minimm balance should be 1000");
		else if(account.getBalance() <0) 
			throw new ApplicationException("Can not enter negative amount");
		else if(dao.findById(account.getMobileno()).isPresent())
			throw new ApplicationException("Account Already Present with mobile number "+account.getMobileno());
		else if(dao.findbyId(account.getId())!=null)
			throw new ApplicationException("Account Already Present with Account ID "+account.getId());
		else
		dao.saveAndFlush(account);
		return new Response(200,"Account with ID : "+account.getId()+" added successfully.", null);
	}

	
	@Transactional(propagation = Propagation.REQUIRED)
	public Response deleteAccount(Account account) throws ApplicationException{
		if(dao.findById(account.getMobileno()).isPresent()) {
			dao.delete(account);
			return new Response(200,"Account associated to "+account.getMobileno()+" deleted successfully.", null);
		}
		else
			throw new ApplicationException("Account with Mobile number "+account.getMobileno()+" not present");
	}

	
	@Transactional(readOnly = true)
	public Response findAccount(Long mobile) throws ApplicationException{ // check for invalid mobile number
		if(!validateMobileNumber(mobile))
			throw new ApplicationException("Enter a valid mobile number");
		if(mobile == null) 
			return new Response(400,"Mobile number is empty", null);
		Optional<Account> box = dao.findById(mobile);
		if(box.isPresent())
			return new Response(200, "Account Found", box.get());
		else
			throw new ApplicationException("Account with Mobile number "+mobile+" not present");
	}

	
	@Transactional(readOnly = true)
	public List<Account> getAllAccount() {
		return dao.findAll();
	}

	@Transactional(propagation = Propagation.REQUIRED)
	public boolean updateAccount(Account account) {
		dao.saveAndFlush(account);
		return true;
	}
	
	// for validating the given account holder name
	private boolean validateName(String name) {
		return Validator.validateData(name, Validator.namePattern);
	}
	
	private boolean validateBalance(Double balance) {
		return Validator.validateData(String.valueOf(balance), Validator.balancePattern);
	}
	
	private boolean validateID(Integer id) {
		return Validator.validateData(String.valueOf(id), Validator.aIdPattern);
	}
	
	private boolean validateMobileNumber(Long mobile) {
		return Validator.validateData(String.valueOf(mobile), Validator.mobilePattern);
	}
	
}