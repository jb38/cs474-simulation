package edu.hood.cs.sim.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="METRICS")
public class Metric {

	private int id = -1;
	private String runId = null;
	private String flightNumber = null;
	private String tailNumber = null;
	private int delay = 0;
	private int numFlightsImpacted = 0;
	
	public Metric() { }
	
	public Metric(String runId, String flightNumber, String tailNumber,
			int delay, int numFlightsImpacted) {
		super();
		this.runId = runId;
		this.flightNumber = flightNumber;
		this.tailNumber = tailNumber;
		this.delay = delay;
		this.numFlightsImpacted = numFlightsImpacted;
	}

	@Id
	@GeneratedValue
    public int getId() {
        return this.id;
    }
 
    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "RUN_ID")
	public String getRunId() {
		return runId;
	}

	public void setRunId(String runId) {
		this.runId = runId;
	}

	@Column(name = "FL_NUM")
	public String getFlightNumber() {
		return flightNumber;
	}

	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}

	@Column(name = "TAIL_NUM")
	public String getTailNumber() {
		return tailNumber;
	}

	public void setTailNumber(String tailNumber) {
		this.tailNumber = tailNumber;
	}

	@Column(name = "DELAY")
	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}

	@Column(name = "IMPACT")
	public int getNumFlightsImpacted() {
		return numFlightsImpacted;
	}

	public void setNumFlightsImpacted(int numFlightsImpacted) {
		this.numFlightsImpacted = numFlightsImpacted;
	}
}
