package com.easybuy.shopping.controller;

import javax.validation.constraints.NotNull;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import com.easybuy.shopping.controller.response.CommonReturnType;
import com.easybuy.shopping.dto.OrderModel;
import com.easybuy.shopping.error.BusinessException;
import com.easybuy.shopping.model.OrderInfo;
import com.easybuy.shopping.service.OrderService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/orders")
public class OrderController extends BaseController{
	
	@Autowired
	OrderService orderService;
    
    protected OrderController() {
    	
	}
	
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public @NotNull Iterable<OrderInfo> list() {
        return null; //this.orderService.getAllOrders();
    }

    @PostMapping
    public CommonReturnType create(@RequestBody OrderModel orderModel) throws BusinessException {
    	orderService.createOrder(orderModel);
        return CommonReturnType.create(orderModel);
    }
}
