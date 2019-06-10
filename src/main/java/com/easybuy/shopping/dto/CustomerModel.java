package com.easybuy.shopping.dto;

import javax.validation.constraints.NotBlank;


public class CustomerModel {
	private long id;
	private String add1 ="";
	private String add2 ="";
	
	@NotBlank(message = "have to fill the email")
	private String email;
	
	@NotBlank(message = "have to fill the first name")
	private String firstname;
	
	@NotBlank(message = "have to fill the phone number")
	private String phone;
	private String postcode ="";
	private byte role = 2;
	
	@NotBlank(message = "have to fill the lastname")
	private String lastname;
	private String encrptPassword;
	private String password;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getAdd1() {
		return add1;
	}
	public void setAdd1(String add1) {
		this.add1 = add1;
	}
	public String getAdd2() {
		return add2;
	}
	public void setAdd2(String add2) {
		this.add2 = add2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPostcode() {
		return postcode;
	}
	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}
	public byte getRole() {
		return role;
	}
	public void setRole(byte role) {
		this.role = role;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEncrptPassword() {
		return encrptPassword;
	}
	public void setEncrptPassword(String encrptPassword) {
		this.encrptPassword = encrptPassword;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
