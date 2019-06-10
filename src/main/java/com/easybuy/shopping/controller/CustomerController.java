package com.easybuy.shopping.controller;

import java.security.NoSuchAlgorithmException;
import java.security.Principal;
import java.util.Base64;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.easybuy.shopping.controller.response.CommonReturnType;
import com.easybuy.shopping.controller.vo.CustomerVO;
import com.easybuy.shopping.dto.CustomerModel;
import com.easybuy.shopping.error.BusinessException;
import com.easybuy.shopping.error.EmBusinessError;
import com.easybuy.shopping.service.CustomerService;
import com.easybuy.shopping.validator.ValidationResult;
import com.easybuy.shopping.validator.ValidatorImp;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/customer")
public class CustomerController extends BaseController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private HttpServletRequest httpServeltRequest;

	@Autowired
	private ValidatorImp validator;


	@GetMapping(produces = "application/json")
	@RequestMapping({ "/getotp" })
	public CommonReturnType getOtp(@RequestParam(name = "phone") String phone) {
		Random random = new Random();
		int randomInt = random.nextInt(99999);
		randomInt += 10000;
		String otpCode = String.valueOf(randomInt);
		this.httpServeltRequest.getSession().setAttribute(phone, otpCode);
		return CommonReturnType.create(null);

	}

	/*
	@PostMapping(produces = "application/json")
	@RequestMapping({ "/login" })
	public CommonReturnType login(@RequestParam(name = "email") String email,
			@RequestParam(name = "password") String password) throws BusinessException, NoSuchAlgorithmException {
		CustomerModel customerModel = customerService.validateLogin(email, encodeByMd5(password));
		return CommonReturnType.create(customerModel);
	}*/

	@RequestMapping("/user")
	public Principal user(HttpServletRequest request) {
		String authToken = request.getHeader("Authorization").substring("Basic".length()).trim();
		return () -> new String(Base64.getDecoder().decode(authToken)).split(":")[0];
	}

	@PostMapping(produces = "application/json")
	@RequestMapping({ "/register" })
	public CommonReturnType register(@RequestBody CustomerModel customerModel)
			throws BusinessException, NoSuchAlgorithmException {
		if (customerModel == null)
			throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
		ValidationResult result = validator.validate(customerModel);
		if (result.isHasErrors())
			throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, result.getErrMsg());
		customerModel.setEncrptPassword(encodeByMd5(customerModel.getPassword()));
		customerService.registerCustomer(customerModel);
		return CommonReturnType.create(customerModel);
	}

	private String encodeByMd5(String str) throws NoSuchAlgorithmException {
		System.out.println("password:" + str);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(11);
		// MessageDigest md5 = MessageDigest.getInstance("MD5");
		// byte[] encodedBytes = Base64.getEncoder().encode(md5.digest(str.getBytes()));
		return encoder.encode(str);
	}

	/*
	@GetMapping(produces = "application/json")
	@RequestMapping({ "/{id}" })
	public CommonReturnType getCustomerInfo(@PathVariable(value = "id") long id) throws BusinessException {
		CustomerModel customerModel = customerService.getCustomerInfo(id);
		if (customerModel == null)
			throw new BusinessException(EmBusinessError.USER_NOT_EXIST);

		CustomerVO customerVO = convertCustomerInfo(customerModel);
		return CommonReturnType.create(customerVO);
	}*/

	private CustomerVO convertCustomerInfo(CustomerModel customerModel) {
		CustomerVO customerVO = new CustomerVO();
		BeanUtils.copyProperties(customerModel, customerVO);
		return customerVO;
	}
	

}
