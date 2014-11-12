package edu.hood.cs.sim.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;

import edu.hood.cs.sim.Simulation;
import sim.engine.*;

@Entity
@Table(name="AIRCRAFT")
public class Aircraft implements Steppable {

	private int id;
	private String carrier;
	private String tailNum;
	
	public Aircraft() {
		
	}
	
	public Aircraft(int id, String carrier, String tailNum) {
		this.id = id;
		this.carrier = carrier;
		this.tailNum = tailNum;
	}
	
	@Id
    public Integer getId() {
        return this.id;
    }
 
    public void setId(Integer id) {
        this.id = id;
    }
	
    @Column(name="CARRIER")
	public String getCarrier() {
		return carrier;
	}

	public void setCarrier(String carrier) {
		this.carrier = carrier;
	}

	@Column(name="TAIL_NUM")
	public String getTailNum() {
		return tailNum;
	}

	public void setTailNum(String tailNum) {
		this.tailNum = tailNum;
	}

	public void step(SimState state) {
		Simulation sim = (Simulation)state;
		
		
	}

}
