package com.Anton.controller;

import com.Anton.entities.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.Anton.entities.Operation;
import com.Anton.service.IBankService;

/**
 * @author Anton
 */
@Controller
public class BankController {

    @Autowired
    private IBankService bankService;

    @RequestMapping("/home")
    public String toHome() {
        return "accounts";
    }

    @RequestMapping("/getAccount")
    public String getAccountById(@RequestParam(name = "accountId") String accountId, Model model,
                                 @RequestParam(name = "page", defaultValue = "0") int page) {
        model.addAttribute("accountIdModel", accountId);
        try {
            Account account = bankService.getAccountById(accountId);
            model.addAttribute("accountModel", account);
            Page<Operation> pageOperation = bankService.getAccountOperationByPage(accountId, page, 4);
            int pageNumber = pageOperation.getTotalPages();
            Integer[] pages = new Integer[pageNumber];
            for (int i = 0; i < pageNumber; i++) {
                pages[i] = i;
            }
            model.addAttribute("pageOperationsModel", pageOperation);
            model.addAttribute("pagesModel", pages);

        } catch (Exception e) {
            model.addAttribute("exceptionModel", e);
        }

        return "accounts";
    }

    @RequestMapping(value = "/saveAccountOperation", method = RequestMethod.POST)
    public String saveAccountOperation(Model model, String accountId, String operationType, String accountIdDest,
                                       @RequestParam(defaultValue = "0") double operationAmount) {
        try {
            if (operationType.equals("PAYMENT")) {
                bankService.payToAccount(accountId, operationAmount);
            } else if (operationType.equals("WITHDRAWAL")) {
                bankService.removeFromAccount(accountId, operationAmount);
            } else {
                bankService.transfer(accountId, accountIdDest, operationAmount);
            }
        } catch (Exception e) {
            model.addAttribute("errorModel", e);
            return "redirect:/getAccount?accountId=" + accountId + "&errorModel=" + e.getMessage();
        }

        return "redirect:/getAccount?accountId=" + accountId;
    }

    @RequestMapping(value = "/createAccount", method = RequestMethod.POST)
    public String createNewAccountForExistingCustomer(@RequestParam(name = "customerName") String customerName,
                                                      @RequestParam(name = "customerEmail") String customerEmail,
                                                      Model model,
                                                      @RequestParam(name = "page", defaultValue = "0") int page) {
        try {
            boolean isCustomerWithCurrentParamsExists =
                    bankService.checkIfCustomerWithCurrentParamsExists(customerName, customerEmail);

            if (isCustomerWithCurrentParamsExists) {
                long customerId = bankService.getCustomerByNameAndEmail(customerName, customerEmail).getCode();

                bankService.insertNewAccountForExitingCustomer(customerId);
            } else {
                throw new Exception("The customer with this name and email does not exist.");
            }
        } catch (Exception e) {
            model.addAttribute("exceptionModel", e);
        }

        return "accounts";
    }

    @RequestMapping(value = "/createCustomer", method = RequestMethod.POST)
    public String createNewCustomer(@RequestParam(name = "name") String name, @RequestParam(name = "email") String email,
                                    Model model, @RequestParam(name = "page", defaultValue = "0") int page) {
        try {

            boolean isCustomerWithCurrentParamsExists = bankService.checkIfCustomerWithCurrentParamsExists(name, email);

            if (isCustomerWithCurrentParamsExists) {
                throw new Exception("Can't create new customer with the same name and email");
            }

            bankService.createNewCustomer(name, email);

        } catch (Exception e) {
            model.addAttribute("exceptionModel", e);
        }

        return "accounts";
    }

}
