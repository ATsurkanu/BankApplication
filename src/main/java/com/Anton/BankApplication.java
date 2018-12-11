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

	/*@Override
	public void run(String... arg0) throws Exception {

		Customer cust1 = customerRepository.save(new Customer("Anton", "Anton@gmail.com"));
		Customer cust2 = customerRepository.save(new Customer("asma", "asma@gmail.com"));

		Account currAcc = accountRepository.save(new CurrentAccount("currAcc", new Date(), 9000, cust1, 6000));
		Account savAcc = accountRepository.save(new SavingsAccount("savAcc", new Date(), 6000, cust2, 5.5));

		Operation paymentOpcurrAcc = operationRepository.save(new PaymentOperation(new Date(), 2000, currAcc));
		Operation widhdrawelOpcurrAcc = operationRepository.save(new WithdrawalOperation(new Date(), 1000, currAcc));
		Operation paymentOp1currAcc = operationRepository.save(new PaymentOperation(new Date(), 2000, currAcc));
		Operation widhdrawelOp1currAcc = operationRepository.save(new WithdrawalOperation(new Date(), 1000, currAcc));
		Operation paymentOp2currAcc = operationRepository.save(new PaymentOperation(new Date(), 5000, currAcc));
		Operation widhdrawelOp2currAcc = operationRepository.save(new WithdrawalOperation(new Date(), 1000, currAcc));
		Operation paymentOp3currAcc = operationRepository.save(new PaymentOperation(new Date(), 8741, currAcc));
		Operation widhdrawelOp3currAcc = operationRepository.save(new WithdrawalOperation(new Date(), 2000, currAcc));
		Operation paymentOp4currAcc= operationRepository.save(new PaymentOperation(new Date(), 6587, currAcc));
		Operation widhdrawelOp4currAcc = operationRepository.save(new WithdrawalOperation(new Date(), 2547, currAcc));

		Operation paymentOpsavAcc = operationRepository.save(new PaymentOperation(new Date(), 1000, savAcc));
		Operation widhdrawelOpsavAcc = operationRepository.save(new WithdrawalOperation(new Date(), 2000, savAcc));

		iBankService.payToAccount(currAcc.getAccountId(), 99999999);
	} */
}