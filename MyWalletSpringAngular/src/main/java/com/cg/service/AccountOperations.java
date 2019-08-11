package com.cg.service;

/**
 * Author: Tanmay Pathak
 * */
import com.cg.bean.Account;
import java.util.Map;

public interface AccountOperations {
	public boolean addAccount(Account acc);
	public boolean deleteAccount(Account acc);
	public Account findAccount(long mobile);
	public Map<Long, Account> getAllAccount();
	public boolean updateAccount(Account acc);
}
