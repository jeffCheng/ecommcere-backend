package com.easybuy.shopping.model;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the promo database table.
 * 
 */
@Entity
@NamedQuery(name="Promo.findAll", query="SELECT p FROM Promo p")
public class Promo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="end_date")
	private Date endDate;

	@Column(name="product_id")
	private int productId;

	@Column(name="promo_item_price")
	private double promoItemPrice;

	@Column(name="promo_name")
	private String promoName;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="start_date")
	private Date startDate;

	public Promo() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getEndDate() {
		return this.endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public int getProductId() {
		return this.productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public double getPromoItemPrice() {
		return this.promoItemPrice;
	}

	public void setPromoItemPrice(double promoItemPrice) {
		this.promoItemPrice = promoItemPrice;
	}

	public String getPromoName() {
		return this.promoName;
	}

	public void setPromoName(String promoName) {
		this.promoName = promoName;
	}

	public Date getStartDate() {
		return this.startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

}