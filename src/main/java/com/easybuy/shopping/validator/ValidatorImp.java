package com.easybuy.shopping.validator;


import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

@Component
public class ValidatorImp implements InitializingBean{

	private Validator validator;
	
	public ValidationResult validate(Object bean) {
		ValidationResult validationResult = new ValidationResult();
		Set<ConstraintViolation<Object>> constraintViolationSet = validator.validate(bean);
		if(constraintViolationSet.size()> 0) {
			validationResult.setHasErrors(true);
			constraintViolationSet.forEach(constraintViolation -> {
				String errMsg = constraintViolation.getMessage();
				String propertyName = constraintViolation.getPropertyPath().toString();
				validationResult.getErrorMsgMap().put(propertyName, errMsg);
			});
			
		}
		return validationResult;
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
		this.validator = Validation.buildDefaultValidatorFactory().getValidator();
	}

	
	
}
