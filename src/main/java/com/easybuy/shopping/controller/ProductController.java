package com.easybuy.shopping.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.easybuy.shopping.controller.response.CommonReturnType;
import com.easybuy.shopping.dto.ProductModel;
import com.easybuy.shopping.error.BusinessException;
import com.easybuy.shopping.error.EmBusinessError;
import com.easybuy.shopping.service.ProductService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/products")
public class ProductController extends BaseController{

	@Autowired
	private ProductService productService;

    protected ProductController() {
	}

    @PreAuthorize("hasRole('ROLE_CUSTOMER') or hasRole('ROLE_ADMIN')")
    @GetMapping(value = { "", "/" },produces = "application/json")
    public CommonReturnType getProducts() throws BusinessException {
        return CommonReturnType.create(productService.showProducts());
    }
    
	@PostMapping(produces = "application/json")
	@RequestMapping({ "/create" })
    public CommonReturnType createProductModel(@RequestBody ProductModel productModel) throws BusinessException {
		if(productModel == null) throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR);
		productService.createProduct(productModel);
		return CommonReturnType.create(productModel);
    }
	
	@PreAuthorize("hasRole('ROLE_CUSTOMER') or hasRole('ROLE_ADMIN')")
	@GetMapping(produces = "application/json")
	@RequestMapping({ "/{id}" })
	public CommonReturnType getProductInfo(@PathVariable(value="id") long id) throws BusinessException{
		ProductModel productModel = productService.getProductById(id);
		if(productModel == null) throw new BusinessException(EmBusinessError.PRODUCT_NOT_EXIST);
		return CommonReturnType.create(productModel);
	}
}

