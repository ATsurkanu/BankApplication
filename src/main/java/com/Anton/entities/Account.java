package com.Anton.entities;

import java.io.Serializable;

import java.util.Collection;
import java.util.Date;

import javax.persistence.*;

/**
 *
 * @author Anton
 *
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "ACCOUNT_TYPE", discriminatorType = DiscriminatorType.STRING, length = 2)
public abstract class Account implements Serializable {

	@Id
	private String accountId;
	private Date creationDate;
	private double discount;
	@ManyToOne
	@JoinColumn(name = "CUSTOMER_CODE")
	private Customer customer;
	@OneToMany(mappedBy = "account", fetch = FetchType.LAZY)
	private Collection<Operation> operations;

	public Account() {
		super();
	}

	public Account(Date creationDate, double discount, Customer customer) {
		super();

		this.creationDate = creationDate;
		this.discount = discount;
		this.customer = customer;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Collection<Operation> getOperations() {
		return operations;
	}

	public void setOperations(Collection<Operation> operations) {
		this.operations = operations;
	}

}
