package com.easybuy.shopping.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.easybuy.shopping.model.CustomerPassword;

public interface CustomerPasswordRepository extends CrudRepository<CustomerPassword, Long>{
	
	@Query("SELECT c FROM CustomerPassword c WHERE c.customerId = :customerId")
	CustomerPassword findByCustomerId(@Param("customerId") long customerId);
	
}
