package com.easybuy.shopping.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.easybuy.shopping.model.ProductStock;

public interface ProductStockRepository extends CrudRepository<ProductStock, Long>{
	
	@Query("SELECT ps FROM ProductStock ps WHERE ps.productId = :productId")
	ProductStock findByProductId(@Param("productId") long productId);
	
	@Modifying
	@Query("UPDATE ProductStock ps SET ps.quantity = ps.quantity - :quantity "
			+ "WHERE ps.productId = :productId and ps.quantity >= :quantity ")
	int decreaseProductStock(@Param("quantity") int quantity, @Param("productId") long productId);
	
}
