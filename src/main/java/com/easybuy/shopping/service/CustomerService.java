package com.easybuy.shopping.service;

import org.springframework.validation.annotation.Validated;

import com.easybuy.shopping.dto.CustomerModel;
import com.easybuy.shopping.error.BusinessException;

public @Validated interface CustomerService {
	CustomerModel getCustomerInfo(long id) throws BusinessException ;
	CustomerModel getCustomerInfo(String email);
	CustomerModel registerCustomer(CustomerModel customerModel)throws BusinessException;
	//CustomerModel validateLogin (String email , String password) throws BusinessException ;
}
