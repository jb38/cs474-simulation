package edu.hood.cs.sim.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="SCHEDULES")
public class ScheduledFlight {

	private int id = -1;
	private String tailNum = null;
	private String carrier = null;
	private String origin = null;
	private String destination = null;
	private String departureTime = null;
	private String arrivalTime = null;
	
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
	
	@Column(name="DEP_TIME")
	public String getDepartureTime() {
		return departureTime;
	}
	
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}
	
	@Column(name="ARR_TIME")
	public String getArrivalTime() {
		return arrivalTime;
	}
	
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
	
	@Transient
	public long getDepartureSimSteps() {
		return timeToSteps(this.getDepartureTime());
	}

	@Transient
	public long getArrivalSimSteps() {
		return timeToSteps(this.getArrivalTime());
	}
	
	private long timeToSteps(String timeStr) {
		
		long rawSteps = Long.parseLong(timeStr);
		long minutes = rawSteps % 100;
		long hours = rawSteps - minutes;
		
		return (hours * 60) + minutes;
	}
}
