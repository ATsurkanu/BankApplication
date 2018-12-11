package com.Anton.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * @author Anton
 */
@Entity
@DiscriminatorValue(value = "SA")
public class SavingsAccount extends Account {

    private double interestRate;

    public SavingsAccount() {
        super();
    }

    public SavingsAccount(Date creationDate, double discount, Customer customer,
                          double interestRate) {
        super(creationDate, discount, customer);
        this.interestRate = interestRate;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

}
