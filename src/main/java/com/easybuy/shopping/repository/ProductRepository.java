package com.easybuy.shopping.repository;

import com.easybuy.shopping.model.Product;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepository extends CrudRepository<Product, Long>{
	
	@Query("SELECT p FROM Product p")
	Iterable<Product> findAllProduct();
	
	@Modifying
	@Query("UPDATE Product p SET p.sales = p.sales + :sales WHERE p.id = :productId ")
	int increaseProductSales(@Param("sales") int sales, @Param("productId") long productId);
}
