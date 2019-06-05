package com.airline.athena.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity(name = "addresses")
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "address_id", updatable = false, nullable = false)
	private Long addressId;

	@Column(length = 100, nullable = false)
	private String firstName;

	@Column(length = 100, nullable = true)
	private String middleName;

	@Column(length = 100, nullable = false)
	private String lastName;

	@Column(length = 200, nullable = false)
	private String address1;

	@Column(length = 200, nullable = true)
	private String address2;

	@Column(length = 50, nullable = false)
	private String city;

	@Column(length = 50, nullable = false)
	private String state;

	@Column(length = 50, nullable = false)
	private String zip;

	@Column(length = 50, nullable = false)
	private String email;

	@Column(nullable = false)
	private Date datetimeAdded;
	
	public Address() {
		
	}

	public Address(String firstName, String lastName, String middleName, String address1, String address2, String city,
			String state, String zip, String email, Date datetimeAdded) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.middleName = middleName;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.email = email;
		this.datetimeAdded = datetimeAdded;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getAddressId() {
		return addressId;
	}

	public void setAddressId(Long id) {
		this.addressId = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	public Date getDatetimeAdded() {
		return datetimeAdded;
	}

	public void setDatetimeAdded(Date date) {
		this.datetimeAdded = date;
	}
}
