package com.airline.athena.model;

import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "scheduled_flights")
@SecondaryTable(name = "scheduled_flights_details")
public class ScheduledFlight {

	@Id
	@Column(length = 6, nullable = false, unique = true, updatable = true)
	private String flightId;

	@Column(length = 4, nullable = false)
	private String source;

	@Column(length = 4, nullable = false)
	private String dest;

	@Column(length = 3, nullable = false)
	private String airlineId;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date localDepartingDate;

	@Column(nullable = false)
	private Date localDepartingDateTime;

	@Column(nullable = false)
	private Date localArrivalDateTime;

	@Column(nullable = false, table = "scheduled_flights_details")
	private Integer economySeats;

	@Column(nullable = false, table = "scheduled_flights_details")
	private Integer businessSeats;

	@Column(nullable = false, table = "scheduled_flights_details")
	private Integer firstclassSeats;

	public Integer getEconomySeats() {
		return this.economySeats;
	}

	public Integer getBusinessSeats() {
		return this.businessSeats;
	}

	public Integer getFirstClassSeats() {
		return this.firstclassSeats;
	}

	public void setEconomySeats(Integer seats) {
		this.economySeats = seats;
	}

	public void setBusinessSeats(Integer seats) {
		this.businessSeats = seats;
	}

	public void setFirstClassSeats(Integer seats) {
		this.firstclassSeats = seats;
	}

	public ScheduledFlight() {

	}

	public ScheduledFlight(String flightId, String source, String dest, String airlineId, Date localDepartingDateTime,
			Date localArrivalDateTime) {
		this.flightId = flightId;
		this.source = source;
		this.dest = dest;
		this.airlineId = airlineId;
		this.localDepartingDateTime = localDepartingDate;
		this.localArrivalDateTime = localArrivalDateTime;
	}

	public String getFlightId() {
		return flightId;
	}

	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public String getDest() {
		return dest;
	}

	public void setDest(String dest) {
		this.dest = dest;
	}

	public String getAirlineId() {
		return airlineId;
	}

	public void setAirlineId(String airlineId) {
		this.airlineId = airlineId;
	}

	public Date getLocalDepartingDate() {
		return localDepartingDate;
	}

	public void setLocalDepartingDate(Date date) {
		this.localDepartingDate = date;
	}

	public Date getLocalDepartingDateTime() {
		return localDepartingDateTime;
	}

	public void setLocalDepartingDateTime(Date datetime) {
		this.localDepartingDateTime = datetime;
	}

	public Date getLocalArrivalDateTime() {
		return localArrivalDateTime;
	}

	public void setLocalArrivalDateTime(Date datetime) {
		this.localArrivalDateTime = datetime;
	}

	public String convertDateTimeToTime(Date datetime) {
		SimpleDateFormat localDateFormat = new SimpleDateFormat("HH:mm:ss");
		String time = localDateFormat.format(datetime);

		LocalTime localTime = LocalTime.parse(time);
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("hh:mm a");

		if (localTime.format(dateTimeFormatter).charAt(0) == '0') {
			return localTime.format(dateTimeFormatter).substring(1);
		}

		return localTime.format(dateTimeFormatter);

	}

	public String arrivalTime() {
		return convertDateTimeToTime(this.localArrivalDateTime);
	}

	public String departingTime() {
		return convertDateTimeToTime(this.localDepartingDateTime);
	}

	public String flightDuration() {
		Date startDate = this.localDepartingDateTime;
		Date endDate = this.localArrivalDateTime;

		long duration = endDate.getTime() - startDate.getTime();

//		long diffInSeconds = TimeUnit.MILLISECONDS.toSeconds(duration);
		long diffInMinutes = TimeUnit.MILLISECONDS.toMinutes(duration);
		long diffInHours = TimeUnit.MILLISECONDS.toHours(duration);
//		long diffInDays = TimeUnit.MILLISECONDS.toDays(duration);

		return diffInHours + "h " + diffInMinutes % 60 + "m";

	}
}
