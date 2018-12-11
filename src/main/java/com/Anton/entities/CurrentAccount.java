package com.Anton.entities;

import java.util.Date;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Anton
 */
@Entity
@DiscriminatorValue(value = "CA")
public class CurrentAccount extends Account {
    private double overdraft;

    public CurrentAccount() {
        super();
    }

    public CurrentAccount(Date creationDate, double discount, Customer customer, double overdraft) {
        super(creationDate, discount, customer);
        this.overdraft = overdraft;
    }

    public double getOverdraft() {
        return overdraft;
    }

    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
    }

}
