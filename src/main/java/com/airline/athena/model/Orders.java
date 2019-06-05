package com.airline.athena.model;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "orders")
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id", updatable = false, nullable = false)
	private Long orderId;

	@Column(length = 255, nullable = false)
	private String hash;

	@Column(length = 100, nullable = false)
	private BigDecimal total;

	@Column(length = 100, nullable = false)
	private Date datetimeOrdered;

	@Column(nullable = false)
	private Long addressId;

	public Orders() {
		
	}
	
	public Orders(BigDecimal total, Date datetimeOrdered, Long addressId) {
		this.hash = UUID.randomUUID().toString();
		this.total = total;
		this.datetimeOrdered = datetimeOrdered;
		this.addressId = addressId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public Date getDatetimeOrdered() {
		return datetimeOrdered;
	}

	public void setDatetimeOrdered(Date datetimeOrdered) {
		this.datetimeOrdered = datetimeOrdered;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long addressId) {
		this.addressId = addressId;
	}

	public static String generateHash() {
		return "";
	}
}
