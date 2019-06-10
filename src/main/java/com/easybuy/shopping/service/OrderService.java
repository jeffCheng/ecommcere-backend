package com.easybuy.shopping.service;

import org.springframework.validation.annotation.Validated;
import com.easybuy.shopping.dto.OrderModel;
import com.easybuy.shopping.error.BusinessException;


public @Validated interface OrderService {
	OrderModel createOrder(OrderModel orderModel) throws BusinessException;
	
}
