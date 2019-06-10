package com.easybuy.shopping.dto;

public class OrderModel {

	private String id;
	private long costomerId;
	private long productId;
	private int quantity;
	private double productPrice;
	private double orderPrice;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public long getCostomerId() {
		return costomerId;
	}
	public void setCostomerId(long costomerId) {
		this.costomerId = costomerId;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	public double getOrderPrice() {
		return orderPrice;
	}
	public void setOrderPrice(double orderPrice) {
		this.orderPrice = orderPrice;
	}
}
