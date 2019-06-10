package com.easybuy.shopping.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.easybuy.shopping.model.Promo;


public interface PromoRepository extends CrudRepository<Promo, Long>{
	
	@Query("SELECT pr FROM Promo pr WHERE pr.productId = :productId")
	Promo findByProductId(@Param("productId") long productId);

}
