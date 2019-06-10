package com.easybuy.shopping.service.imp;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.transaction.Transactional;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.easybuy.shopping.dto.ProductModel;
import com.easybuy.shopping.error.BusinessException;
import com.easybuy.shopping.error.EmBusinessError;
import com.easybuy.shopping.model.Product;
import com.easybuy.shopping.model.ProductStock;
import com.easybuy.shopping.repository.ProductRepository;
import com.easybuy.shopping.repository.ProductStockRepository;
import com.easybuy.shopping.service.ProductService;
import com.easybuy.shopping.validator.ValidationResult;
import com.easybuy.shopping.validator.ValidatorImp;


@Service
@Transactional
class ProductServiceImp implements ProductService {

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductStockRepository  productStockRepository;
	
	@Autowired
	private ValidatorImp validator;
	
    protected ProductServiceImp() {
	}

	@Override
	public @NotNull Iterable<ProductModel> showProducts() throws BusinessException {
		
		Iterable<ProductModel> productModelList =  StreamSupport.stream(productRepository.findAllProduct().spliterator(), false).map(
		   product -> {
			   ProductModel productModel = new ProductModel();
			   ProductStock productStock = productStockRepository.findByProductId(product.getId());
			   BeanUtils.copyProperties(product, productModel);
			   productModel.setQuantity(productStock.getQuantity());
			   return productModel;
		   }
		).collect(Collectors.toList());
		return productModelList;
	}

	@Override
	public ProductModel getProductById(@Min(value = 1, message = "Invalid product ID.") long id) throws BusinessException {
		Product product =  productRepository.findById(id).orElseThrow(()->new BusinessException(null,"Product is not found"));
		ProductStock productStock = productStockRepository.findByProductId(product.getId());
		return convertToProductModel(product,productStock);
	}

	@Override
	public ProductModel createProduct(ProductModel productModel) throws BusinessException {
		
		ValidationResult validationResult = validator.validate(productModel);
		if(validationResult.isHasErrors()) throw new  BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, validationResult.getErrMsg());
		Product product = convertFromProductModel(productModel);
		product = productRepository.save(product);
		productModel.setId(product.getId());
		ProductStock productStock = convertStockFromProductModel(productModel);
		productRepository.save(product);
		productStockRepository.save(productStock);
		return productModel;
	}
	
	private ProductModel convertToProductModel(Product product, ProductStock productStock) {
		ProductModel productModel = new ProductModel();
		BeanUtils.copyProperties(product, productModel);
		productModel.setQuantity(productStock.getQuantity());
		return productModel;
	}
	
	private ProductStock convertStockFromProductModel(ProductModel productModel) {
		ProductStock productStock = new ProductStock();
		productStock.setProductId(productModel.getId());
		productStock.setQuantity(productStock.getQuantity());
		return productStock;
	}
	
	
	private Product convertFromProductModel(ProductModel productModel) {
		Product product = new  Product();
		BeanUtils.copyProperties(productModel, product);
		return product;
	}

	@Override
	public boolean decreaseStock(long productId, int quantity) throws BusinessException {
		int affectRow = productStockRepository.decreaseProductStock(quantity, productId);
		if(affectRow > 0) return true;
		else return false;
	}

	@Override
	public void increaseSales(long productId, int sales) throws BusinessException {
		productRepository.increaseProductSales(sales, productId);
	}

}
