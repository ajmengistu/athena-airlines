package com.airline.athena.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity(name = "passengers")
public class Passenger {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "passengers_id", updatable = true, nullable = false)
	private Long id;

	@Column(length = 100, nullable = false)
	private String firstName;

	@Column(length = 100, nullable = true)
	private String middleName;

	@Column(length = 100, nullable = false)
	private String lastName;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date birthday;

	@Column(length = 20, nullable = false)
	private String gender;

	@Column(nullable = false)
	private Boolean payed;

	@Column(length = 8, nullable = true)
	private String confirmationCode;

	@Column(length = 8, nullable = true)
	private String seatNumber;

	@Column(nullable = false)
	private Boolean checkedIn;

	@Column(nullable = false)
	private Date checkedInDatetime;

	public Passenger() {
	}

	public Passenger(String firstName, String middleName, String lastName, Date birthday, String gender) {
		this.payed = false;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.birthday = birthday;
		this.gender = gender;
		this.seatNumber = null;
		this.checkedIn = false;
	}

	public Long getId() {
		return id;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Boolean getPayed() {
		return payed;
	}

	public void setPayed(Boolean payed) {
		this.payed = payed;
	}

	public String getConfirmationCode() {
		return confirmationCode;
	}

	public void setConfirmationCode(String confirmationCode) {
		this.confirmationCode = confirmationCode;
	}

	public String getSeatNumber() {
		return seatNumber;
	}

	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}

	public Boolean getCheckedIn() {
		return checkedIn;
	}

	public void setCheckedIn(Boolean checkedIn) {
		this.checkedIn = checkedIn;
	}

	public Date getCheckedInDatetime() {
		return checkedInDatetime;
	}

	public void setCheckedInDatetime(Date datetime) {
		this.checkedInDatetime = datetime;
	}
}
