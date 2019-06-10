package com.easybuy.shopping.service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import com.easybuy.shopping.dto.ProductModel;
import com.easybuy.shopping.error.BusinessException;

public @Validated interface ProductService {
	
	@NotNull Iterable<ProductModel> showProducts() throws BusinessException;
	ProductModel getProductById(@Min(value = 1L, message = "Invalid product ID.") long id) throws BusinessException;
	ProductModel createProduct(ProductModel productModel) throws BusinessException;
	boolean decreaseStock(long productId, int quantity) throws BusinessException;
	void increaseSales(long productId, int sales) throws BusinessException;
}
