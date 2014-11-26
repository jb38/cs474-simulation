package edu.hood.cs.sim.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import sim.engine.SimState;
import sim.engine.Steppable;
import edu.hood.cs.sim.Simulation;
import edu.hood.cs.sim.repositories.ScheduledFlightRepository;

@SuppressWarnings("serial")
@Entity
@Table(name="AIRCRAFT")
public class Aircraft implements Steppable  {

	private static final Logger log = LogManager.getLogger(Aircraft.class);
	
	private int id = -1;
	private String carrier = null;
	private String tailNum = null;
	private AircraftState state = AircraftState.ON_GROUND;
	private double delay = -1;
	
	@Id
    public int getId() {
        return this.id;
    }
 
    public void setId(int id) {
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

	@Transient
	public AircraftState getState() {
		return state;
	}

	public void setState(AircraftState state) {
		this.state = state;
	}

	@Transient
	public double getDelay() {
		return Math.max(0, this.delay);
	}

	public void setDelay(double delay) {
		this.delay = delay;
	}
	
	@Transient
	public boolean isDelayed() {
		return (this.delay > 0);
	}
	
	public boolean isDelaySet() {
		return (this.delay >= 0);
	}

	private List<ScheduledFlight> schedule = null;
	
	public void init(SimState state) {
		Simulation sim = (Simulation)state;
		
		ScheduledFlightRepository repository = ScheduledFlightRepository.getInstance();
		this.schedule = repository.getSchedule(this);
		
		log.trace("schedule for " + this.tailNum + " has " + this.schedule.size() + " items");
		
		if (this.schedule.size() == 0) {
			return;
		} else {
			sim.schedule.scheduleOnce(this.schedule.get(0).getDepartureSimTime(), this);
		}
	}
	
	public void step(SimState state) {
		Simulation sim = (Simulation)state;
		
		if (this.schedule.size() == 0) {
			return;
		}
		
		double time = sim.schedule.getTime();
		
		if (!this.isDelaySet()) {
			int delay = sim.random.nextInt(90);
			this.setDelay(delay);
			
			sim.schedule.scheduleOnceIn(delay, this);
		}
		
		ScheduledFlight currentTask = this.schedule.get(0);
		double departureTime = currentTask.getDepartureSimTime() + this.getDelay();
		double arrivalTime = departureTime + currentTask.getElapsedTime();
		
		AircraftState currentState = this.getState();
		
		// should take off
		if (currentState == AircraftState.ON_GROUND && time == departureTime) {
			
			log.debug(String.format("%8.1f %s departing", time, currentTask));
			
			// update the state
			this.setState(AircraftState.IN_AIR);
			
			// schedule the next "step" to be at landing
			sim.schedule.scheduleOnce(arrivalTime, this);
		}
		
		// should land?
		else if (currentState == AircraftState.IN_AIR && time >= arrivalTime) {
			
			log.debug(String.format("%8.1f %s arriving", time, currentTask));
			
			// update the state
			this.setState(AircraftState.ON_GROUND);
			
			// reset the delay
			this.setDelay(-1);
			// TODO clear the delayed flag
			
			this.schedule.remove(0);
			if (this.schedule.size() > 0) {
				ScheduledFlight nextTask = this.schedule.get(0);
				
				if (time < nextTask.getDepartureSimTime()) {
					sim.schedule.scheduleOnce(nextTask.getDepartureSimTime(), this);
				} else {
					log.debug(String.format("%8.1f %s erroneous", time, nextTask));
				}
				// TODO there exists the case where a particular aircraft
				//      will not arrive in time for it's next flight, even
				//      if it is scheduled -- it is destined to be late.
				//      we should have a way to capture this (isolated from
				//      normal lateness?)
				//      this throws an exception currently
			}
		}
		
		// TODO calculate the aircraft's geographic position? doing this depends on 
		//      how we decide to implement the visualization
		
		//		double progress = Math.min(0, (steps - departureSteps) / (double)(arrivalSteps - departureSteps)),
		//		       x = 0, 
		//		       y = 0; // TODO calculate the aircraft's current location
		//		
		//		
		//		sim.field.setObjectLocation(this, new Double2D(x, y));
	}
}
