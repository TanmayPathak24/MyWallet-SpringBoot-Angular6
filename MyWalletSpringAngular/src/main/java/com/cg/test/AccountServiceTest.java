package com.cg.test;

import static org.junit.jupiter.api.Assertions.*;

import org.easymock.EasyMock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.cg.bean.Account;
import com.cg.dao.AccountDAO;
import com.cg.service.AccountService;

class AccountServiceTest {
	AccountService service;
	AccountDAO mockdao;
	
	
	@BeforeEach
	public void setup() {
		service = new AccountService();
		mockdao = EasyMock.createMock(AccountDAO.class);
		service.setAccountDAOImpl(mockdao);
	}
	
	
}
