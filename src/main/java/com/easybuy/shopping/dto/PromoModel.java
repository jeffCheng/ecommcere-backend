package com.easybuy.shopping.dto;

import java.util.Date;

public class PromoModel {
	
	private int id;
	private Date endDate;
	private Date startDate;
	private String promoName;
	private int productId;
	private double promoItemPrice;
	
	private int status; // 1:no start 2:start 3:expired
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getEndDate() {
		return endDate;
	}
	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public String getPromoName() {
		return promoName;
	}
	public void setPromoName(String promoName) {
		this.promoName = promoName;
	}
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public double getPromoItemPrice() {
		return promoItemPrice;
	}
	public void setPromoItemPrice(double promoItemPrice) {
		this.promoItemPrice = promoItemPrice;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
}
