package com.easybuy.shopping.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.easybuy.shopping.model.Customer;


public interface CustomerRepository extends CrudRepository<Customer, Long>{

	@Query("SELECT c FROM Customer c WHERE c.email = :email")
	Customer findByEmail(@Param("email") String email);
	
}
