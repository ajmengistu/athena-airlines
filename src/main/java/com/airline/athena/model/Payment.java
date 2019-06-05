package com.airline.athena.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "payments")
public class Payment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "payment_id", updatable = false, nullable = false)
	private Long paymentId;

	@Column(nullable = false)
	private Long orderId;

	@Column(nullable = false)
	private String transactionId;

	@Column(nullable = false)
	private Date datetimePayed;

	public Payment() {
	}

	public Payment(Long orderId, String transactionId, Date datetimePayed) {
		this.orderId = orderId;
		this.transactionId = transactionId;
		this.datetimePayed = datetimePayed;
	}

	public Long getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(Long paymentId) {
		this.paymentId = paymentId;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public Date getDatetimePayed() {
		return datetimePayed;
	}

	public void setDatetimePayed(Date datetimePayed) {
		this.datetimePayed = datetimePayed;
	}
}
