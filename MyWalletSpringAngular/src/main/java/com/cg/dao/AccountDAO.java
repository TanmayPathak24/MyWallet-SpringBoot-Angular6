package com.cg.dao;

import org.jboss.logging.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cg.bean.Account;

/**
 * @author Tanmay Pathak
 * */
public interface AccountDAO extends JpaRepository<Account, Long>{
	@Query("select a from Account a where a.id = ?1")
	public Account findbyId(Integer id);
}
