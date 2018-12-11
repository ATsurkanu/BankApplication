package com.Anton.service;

import com.Anton.entities.Customer;
import org.springframework.data.domain.Page;

import com.Anton.entities.Account;
import com.Anton.entities.Operation;

/**
 * @author Anton
 */
public interface IBankService {

    public Account getAccountById(String accountId);

    /**
     * @param accountId
     * @param amount
     */
    public void payToAccount(String accountId, double amount);

    /**
     * @param accountId
     * @param amount
     */
    public void removeFromAccount(String accountId, double amount);

    /**
     * @param accountOriginId
     * @param accountDestId
     * @param amount
     */

    public void transfer(String accountOriginId, String accountDestId, double amount);

    /**
     * @param accountId
     * @param page
     * @param size
     * @return
     */

    public Page<Operation> getAccountOperationByPage(String accountId, int page, int size);

    public void createNewCustomer(String name, String email);

    public Customer getCustomerByNameAndEmail(String name, String email);

    boolean checkIfCustomerWithCurrentParamsExists(String name, String email);

    void insertNewAccountForExitingCustomer(long customerId);
}
