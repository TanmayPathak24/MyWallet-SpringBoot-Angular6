package com.cg.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties.Producer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bean.Account;
import com.cg.bean.Response;
import com.cg.exception.ApplicationException;
import com.cg.service.AccountService;
import com.sun.swing.internal.plaf.metal.resources.metal;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class AppController {

	@Autowired
	private AccountService service;

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping("/addaccount")
	public Response addAccount(@RequestBody Account account) {
		try {
			return service.addAccount(account);
		} catch (ApplicationException ex) {
			return new Response(400,ex.getMessage(),null);
		}
	}

	@PostMapping(value = "/withdraw", consumes = { "application/json" }, produces = { "application/json" })
	@CrossOrigin(origins = "http://localhost:4200")
	public Response withdraw(@RequestBody Account account, @RequestParam Double amount) {
		try {
			return service.withdraw(account, amount);
		} catch (ApplicationException ex) {
			return new Response(400,ex.getMessage(), null);
		}
	}

	@PostMapping(value = "/deposit", consumes = { "application/json" }, produces = { "application/json" })
	@CrossOrigin(origins = "http://localhost:4200")
	public Response deposit(@RequestBody Account account, @RequestParam Double amount) {
		try {
			return service.deposit(account, amount);
		} catch (ApplicationException ex) {
			return new Response(400,ex.getMessage(), null);
		}
	}

	@PostMapping(value = "/transfer", consumes = { "application/json" }, produces = { "application/json" })
	@CrossOrigin(origins = "http://localhost:4200")
	public Response transferMoney(@RequestBody List<Account> list, @RequestParam Double amount) {
		Account from = list.get(0);
		Account to = list.get(1);
		try {
			return service.transferMoney(from, to, amount);
		} catch (ApplicationException ex) {
			return new Response(400,ex.getMessage(), null);
		}
	}

	@PostMapping(value = "/delete", consumes = { "application/json" }, produces = { "application/json" })
	@CrossOrigin(origins = "http://localhost:4200")
	public Response deleteAccount(@RequestBody Account account) {
		try {
			return service.deleteAccount(account);
		} catch (ApplicationException ex) {
			return new Response(400, ex.getMessage(), null);
		}
	}

	@PostMapping(value = "/find", consumes = { "application/json" }, produces = { "application/json" })
	@CrossOrigin(origins = "http://localhost:4200")
	public Response findAccount(@RequestParam Long mobileNo) {
		 try {
			 return service.findAccount(mobileNo);
		 }catch(ApplicationException exception) {
			 return new Response(400, exception.getMessage(), null);
		 }
	}

	@GetMapping(value = "/displayAll", consumes = { "application/json" }, produces = { "application/json" })
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Account> displayAccounts() {
		List<Account> list = new ArrayList<Account>(service.getAllAccount());
		System.out.println(list);
		return list;
	}
}
