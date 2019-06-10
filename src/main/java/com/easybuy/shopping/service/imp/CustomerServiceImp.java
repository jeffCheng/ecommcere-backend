package com.easybuy.shopping.service.imp;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easybuy.shopping.dto.CustomerModel;
import com.easybuy.shopping.error.BusinessException;
import com.easybuy.shopping.error.EmBusinessError;
import com.easybuy.shopping.model.Customer;
import com.easybuy.shopping.model.CustomerPassword;
import com.easybuy.shopping.repository.CustomerPasswordRepository;
import com.easybuy.shopping.repository.CustomerRepository;
import com.easybuy.shopping.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImp implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerPasswordRepository customerpasswordRepository;
	
	public CustomerServiceImp() {
	}
	
	@Override
	public CustomerModel getCustomerInfo(long id) throws BusinessException {
		Customer customer = customerRepository.findById(id).orElseThrow(()-> new BusinessException(EmBusinessError.USER_NOT_EXIST));
		CustomerPassword customerPassword= customerpasswordRepository.findByCustomerId(customer.getId());
		return convertCustomerInfo(customer, customerPassword);
	}
	
	private CustomerModel convertCustomerInfo(Customer customer, CustomerPassword customerPassword) {
		CustomerModel memberModel = new  CustomerModel();
		BeanUtils.copyProperties(customer, memberModel);
		memberModel.setEncrptPassword(customerPassword.getEncrptPassword());
		return memberModel;
	}

	@Override
	public CustomerModel registerCustomer(CustomerModel customerModel) throws BusinessException {
		if(customerModel == null) throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
		if(StringUtils.isEmpty(customerModel.getFirstname())||StringUtils.isEmpty(customerModel.getLastname()))
				throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
		Customer customer = convertFromModel(customerModel);
		customer = customerRepository.save(customer);
		customerModel.setId(customer.getId());
		CustomerPassword customerPassword = convertToCustomerPassword(customerModel);
		customerpasswordRepository.save(customerPassword);
		return convertCustomerInfo(customer, customerPassword);
	}
	
	private Customer convertFromModel(CustomerModel customerModel) {
		Customer customer = new Customer();
		BeanUtils.copyProperties(customerModel, customer);
		return customer;
	}
	
	private CustomerPassword convertToCustomerPassword(CustomerModel customerModel) {
		CustomerPassword customerPassword = new CustomerPassword();
		customerPassword.setCustomerId(customerModel.getId());
		customerPassword.setEncrptPassword(customerModel.getEncrptPassword());
		return customerPassword;
	}

	/*
	@Override
	public CustomerModel validateLogin(String email, String encrptPassword){
		Customer customer = customerRepository.findByEmail(email);
		//if(customer == null) throw new BusinessException(EmBusinessError.USER_LOGIN_ERROR);
		CustomerPassword customerPassword = customerpasswordRepository.findByCustomerId(customer.getId());
		//if(!StringUtils.equals(encrptPassword, customerPassword.getEncrptPassword())) throw new BusinessException(EmBusinessError.USER_LOGIN_ERROR);
		return convertCustomerInfo(customer, customerPassword);
	}*/

	@Override
	public CustomerModel getCustomerInfo(String email)  {
		Customer customer = customerRepository.findByEmail(email);
		if(customer == null) return null;
		CustomerPassword customerPassword = customerpasswordRepository.findByCustomerId(customer.getId());
		return convertCustomerInfo(customer, customerPassword);
	}
}
