package com.cg.service;

import com.cg.bean.Account;
import com.cg.bean.Response;
import com.cg.exception.ApplicationException;

import java.util.List;
import java.util.Map;

public interface Transaction {
	public Response withdraw(Account a, double amount);
	public Response deposit(Account a, double amount);
	public Response transferMoney(Account from, Account to, double amount);
	public Response addAccount(Account a);
	public Response deleteAccount(Account a);
	public List<Account> getAllAccount();
	public boolean updateAccount(Account a);
}
