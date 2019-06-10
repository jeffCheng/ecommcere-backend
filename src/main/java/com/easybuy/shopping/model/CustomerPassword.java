package com.easybuy.shopping.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the customer_password database table.
 * 
 */
@Entity
@Table(name="customer_password")
@NamedQuery(name="CustomerPassword.findAll", query="SELECT c FROM CustomerPassword c")
public class CustomerPassword implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)   
	private long id;

	@Column(name="encrpt_password")
	private String encrptPassword;

	//bi-directional one-to-one association to Customer
	//@OneToOne(mappedBy="customerPassword")
	//private Customer customer;
	@Column(name="customer_id")
	private long customerId;

	public CustomerPassword() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEncrptPassword() {
		return this.encrptPassword;
	}

	public void setEncrptPassword(String encrptPassword) {
		this.encrptPassword = encrptPassword;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	//public Customer getCustomer() {
		//return this.customer;
	//}

	//public void setCustomer(Customer customer) {
		//this.customer = customer;
	//}

}