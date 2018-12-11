package com.Anton.service;

import java.util.Date;

import com.Anton.dao.CustomerRepository;
import com.Anton.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Anton.dao.AccountRepository;
import com.Anton.dao.OperationRepository;

@Service
@Transactional
public class BankServiceImp implements IBankService {
    private static long accountId = 0;

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private OperationRepository operationRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Account getAccountById(String accountId) {
        Account account = (Account) accountRepository.findOne(accountId);
        if (account == null)
            throw new RuntimeException("unfound account");

        return account;
    }

    @Override
    public void payToAccount(String accountId, double amount) {
        Account currentAcc = getAccountById(accountId);
        PaymentOperation paymentOp = new PaymentOperation(new Date(), amount,
                currentAcc);
        operationRepository.save(paymentOp);
        currentAcc.setDiscount(currentAcc.getDiscount() + amount);
        accountRepository.save(currentAcc);

    }

    @Override
    public void removeFromAccount(String accountId, double amount) {
        Account currentAcc = getAccountById(accountId);
        double solde = 0;
        if (currentAcc instanceof CurrentAccount)
            solde = ((CurrentAccount) currentAcc).getOverdraft();
        if (currentAcc.getDiscount() + solde < amount)
            throw new RuntimeException("Insufficient discount ");
        WithdrawalOperation withdrawalOp = new WithdrawalOperation(new Date(),
                amount, currentAcc);
        operationRepository.save(withdrawalOp);
        currentAcc.setDiscount(currentAcc.getDiscount() - amount);
        accountRepository.save(currentAcc);

    }

    @Override
    public void transfer(String accountOriginId, String accountDestId,
                         double amount) {
        if (accountOriginId.equals(accountDestId))
            throw new RuntimeException(
                    "Impossible operation: account id must be different");
        removeFromAccount(accountOriginId, amount);
        payToAccount(accountDestId, amount);

    }

    @Override
    public Page<Operation> getAccountOperationByPage(String accountId,
                                                     int page, int size) {
        return operationRepository.getAccountOperationsByPage(accountId,
                new PageRequest(page, size));
    }

    @Override
    public void createNewCustomer(String name, String email) {
        Customer customer = new Customer(name, email);
        customerRepository.save(customer);
    }

    @Override
    public Customer getCustomerByNameAndEmail(String name, String email) {
        return customerRepository.getCustomerByNameAndEmail(name, email);
    }

    @Override
    public boolean checkIfCustomerWithCurrentParamsExists(String name, String email) {
        Customer customerFromDb = getCustomerByNameAndEmail(name, email);

        if (customerFromDb != null) {
            if ((name.equalsIgnoreCase(customerFromDb.getName())) && email.equalsIgnoreCase(customerFromDb.getEmail())) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void insertNewAccountForExitingCustomer(long customerId) {
        accountRepository.insertNewAccountForExistingCustomer(String.valueOf(accountId++), customerId);
    }
}
