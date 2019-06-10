package com.easybuy.shopping.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the order_info database table.
 * 
 */
@Entity
@Table(name="order_info")
@NamedQuery(name="OrderInfo.findAll", query="SELECT o FROM OrderInfo o")
public class OrderInfo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String id;

	private int amount;

	@Column(name="customer_id")
	private int customerId;

	@Column(name="order_price")
	private Double orderPrice;

	@Column(name="product_id")
	private int productId;

	@Column(name="product_price")
	private Double productPrice;

	@Column(name="promo_id")
	private int promoId;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="order_date")
	private Date orderDate;

	public OrderInfo() {
	}

	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getAmount() {
		return this.amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getCustomerId() {
		return this.customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public Double getOrderPrice() {
		return this.orderPrice;
	}

	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public Double getProductPrice() {
		return this.productPrice;
	}

	public void setProductPrice(Double productPrice) {
		this.productPrice = productPrice;
	}

	public int getPromoId() {
		return this.promoId;
	}

	public void setPromoId(int promoId) {
		this.promoId = promoId;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
}