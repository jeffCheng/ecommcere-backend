package com.easybuy.shopping.repository;

import org.springframework.data.repository.CrudRepository;

import com.easybuy.shopping.model.OrderInfo;

public interface OrderRepository extends CrudRepository<OrderInfo, Long>{
	
}
