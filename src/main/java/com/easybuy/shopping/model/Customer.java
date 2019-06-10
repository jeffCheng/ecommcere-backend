package com.easybuy.shopping.model;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The persistent class for the customer database table.
 * 
 */
@Entity
@NamedQuery(name="Customer.findAll", query="SELECT c FROM Customer c")
public class Customer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)   
	private long id;

	private String add1;

	private String add2;

	private String email;

	private String firstname;

	private String phone;

	private String postcode;

	private byte role;

	private String lastname;

	//bi-directional one-to-one association to CustomerPassword
	//@OneToOne
	//@JoinColumn(name="id", referencedColumnName="customer_id")
	//private CustomerPassword customerPassword;

	public Customer() {
	}

	public long getId() {
		return this.id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getAdd1() {
		return this.add1;
	}

	public void setAdd1(String add1) {
		this.add1 = add1;
	}

	public String getAdd2() {
		return this.add2;
	}

	public void setAdd2(String add2) {
		this.add2 = add2;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return this.firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPostcode() {
		return this.postcode;
	}

	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}

	public byte getRole() {
		return this.role;
	}

	public void setRole(byte role) {
		this.role = role;
	}

	public String getLastname() {
		return this.lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	//public CustomerPassword getCustomerPassword() {
		//return this.customerPassword;
	//}

	//public void setCustomerPassword(CustomerPassword customerPassword) {
		//this.customerPassword = customerPassword;
	//}

}