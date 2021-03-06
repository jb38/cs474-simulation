package edu.hood.cs.sim.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="SCHEDULES")
public class ScheduledFlight implements Comparable<ScheduledFlight> {

	private int id = -1;
	private String tailNum = null;
	private String carrier = null;
	private String flightNum = null;
	private String origin = null;
	private String destination = null;
	private String departureTime = null;
	private String arrivalTime = null;
	private float elapsedTime = 0;
	
	@Id
    public int getId() {
        return this.id;
    }
 
    public void setId(int id) {
        this.id = id;
    }
	
	@Column(name="TAIL_NUM")
	public String getTailNum() {
		return tailNum;
	}
	
	public void setTailNum(String tailNum) {
		this.tailNum = tailNum;
	}
	
	@Column(name="CARRIER")
	public String getCarrier() {
		return carrier;
	}
	
	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}
	
	@Column(name="FL_NUM")
	public String getFlightNum() {
		return flightNum;
	}

	public void setFlightNum(String flightNum) {
		this.flightNum = flightNum;
	}

	@Column(name="ORIGIN")
	public String getOrigin() {
		return origin;
	}
	
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	@Column(name="DEST")
	public String getDestination() {
		return destination;
	}
	
	public void setDestination(String destination) {
		this.destination = destination;
	}
	
	@Column(name="CRS_DEP_TIME")
	public String getDepartureTime() {
		return departureTime;
	}
	
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	
	@Column(name="CRS_ARR_TIME")
	public String getArrivalTime() {
		return arrivalTime;
	}
	
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	@Column(name="CRS_ELAPSED_TIME")
	public float getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(float elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	@Transient
	public double getAirTime() {
		return this.getArrivalSimTime() - this.getDepartureSimTime();
	}

	@Transient
	public double getDepartureSimTime() {
		return timeToSimTime(this.getDepartureTime());
	}

	@Transient
	public double getArrivalSimTime() {
		return timeToSimTime(this.getArrivalTime());
	}
	
	private double timeToSimTime(String timeStr) {
		
		long rawSteps = Long.parseLong(timeStr);
		long minutes = rawSteps % 100;
		long hours = rawSteps - minutes;
		
		return (hours * 60) + minutes;
	}
	
	@Override
	public String toString() {
		return String.format("%2s%-4s (%6s) %3s %4s-%4s %3s", 
				this.carrier, 
				this.flightNum, 
				this.tailNum, 
				this.origin, 
				this.departureTime, 
				this.arrivalTime, 
				this.destination);
	}

	public int compareTo(ScheduledFlight o) {
		double lhs = this.getDepartureSimTime(),
			   rhs = o.getDepartureSimTime();
		
		return Double.compare(lhs, rhs);
	}
}
