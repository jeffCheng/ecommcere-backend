package com.easybuy.shopping.service.imp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.EntityNotFoundException;
//import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.easybuy.shopping.EcommerceApplication;
import com.easybuy.shopping.dto.CustomerModel;
import com.easybuy.shopping.dto.OrderModel;
import com.easybuy.shopping.dto.ProductModel;
import com.easybuy.shopping.error.BusinessException;
import com.easybuy.shopping.error.EmBusinessError;
import com.easybuy.shopping.model.OrderInfo;
import com.easybuy.shopping.model.SequenceInfo;
import com.easybuy.shopping.repository.OrderRepository;
import com.easybuy.shopping.repository.SequenceRepository;
import com.easybuy.shopping.service.CustomerService;
import com.easybuy.shopping.service.OrderService;
import com.easybuy.shopping.service.ProductService;

@Service
public class OrderServiceImp implements OrderService {

	private static final Logger log = LoggerFactory.getLogger(EcommerceApplication.class);

	@Autowired
	private ProductService productService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private SequenceRepository sequenceRepository;

	@Autowired
	private OrderRepository orderRepository;

	@Override
	@Transactional
	public OrderModel createOrder(OrderModel orderModel) throws BusinessException {
		// product
		ProductModel productModel = productService.getProductById(orderModel.getProductId());
		if (productModel == null)
			throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "Customer is not existed");

		int qty = productModel.getQuantity();
		if (qty <= 0)
			throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,
					"Product quantity is less than zero");
		// customer
		CustomerModel customerModel = customerService.getCustomerInfo(orderModel.getCostomerId());
		if (customerModel == null)
			throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR, "Customer is not existed");

		// start to decrease the stock
		boolean result = productService.decreaseStock(orderModel.getProductId(), orderModel.getQuantity());
		if (!result)
			throw new BusinessException(EmBusinessError.ORDER_QTY_IS_NOT_ENOUGH);

		orderModel.setProductPrice(productModel.getPrice());
		orderModel.setOrderPrice(productModel.getPrice() * orderModel.getQuantity());
		orderModel.setId(generateOrderNo(orderModel));
		OrderInfo orderInfo = convertToOrder(orderModel);
		orderRepository.save(orderInfo);

		productService.increaseSales(orderModel.getProductId(), orderModel.getQuantity());

		return null;
	}

	@Transactional(propagation = Propagation.REQUIRES_NEW)
	private String generateOrderNo(OrderModel orderModel) {
		LocalDateTime now = LocalDateTime.now();
		now.format(DateTimeFormatter.ISO_DATE).replace("-", "");
		StringBuilder sb = new StringBuilder();
		sb.append(now);
		SequenceInfo sequenceInfo = sequenceRepository.findById("order_info")
				.orElseThrow(() -> new EntityNotFoundException("order_info"));
		int val = sequenceInfo.getCurrentValue();
		sequenceInfo.setCurrentValue(val + sequenceInfo.getStep());
		sequenceRepository.save(sequenceInfo);

		String seq = String.valueOf(val);
		for (int i = 0; i < 6 - seq.length(); i++)
			sb.append(0);
		sb.append(seq);

		sb.append(orderModel.getCostomerId() % 100);

		return sb.toString();
	}

	private OrderInfo convertToOrder(OrderModel orderModel) {
		OrderInfo orderInfo = new OrderInfo();
		BeanUtils.copyProperties(orderModel, orderInfo);
		return orderInfo;
	}

	public static <T> T convertTFromPojo(Class<T> clazz, Object pojoBean) {

		if (pojoBean == null) {

			return null;

		}

		try {

			T target = clazz.newInstance();

			BeanUtils.copyProperties(pojoBean, target);

			return target;

		} catch (Exception e) {

			log.error("convertTFromPojo is error-->", e);

		}

		return null;
	}

}
