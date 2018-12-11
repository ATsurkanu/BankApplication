package com.Anton;

import com.Anton.dao.AccountRepository;
import com.Anton.dao.CustomerRepository;
import com.Anton.dao.OperationRepository;
import com.Anton.service.IBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication 
public class BankApplication  {

	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private AccountRepository accountRepository;
	@Autowired
	private OperationRepository operationRepository;

	@Autowired
	private IBankService iBankService;

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(BankApplication.class, args);

	}
}