package com.Anton.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * @author Anton
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "OPERATION_TYPE", discriminatorType = DiscriminatorType.STRING, length = 2)
public abstract class Operation implements Serializable {

    @Id
    @GeneratedValue
    private Long number;
    private Date operationDate;
    private double amount;
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

    public Operation() {
        super();
    }

    public Operation(Date operationDate, double amount, Account account) {
        super();
        this.operationDate = operationDate;
        this.amount = amount;
        this.account = account;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Date getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(Date operationDate) {
        this.operationDate = operationDate;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

}
