package edu.hood.cs.sim.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import sim.engine.SimState;
import sim.engine.Steppable;
import sim.util.Double2D;
import edu.hood.cs.sim.Simulation;
import edu.hood.cs.sim.repositories.ScheduledFlightRepository;

@SuppressWarnings("serial")
@Entity
@Table(name="AIRCRAFT")
public class Aircraft implements Steppable  {

	private int id = -1;
	private String carrier = null;
	private String tailNum = null;
	private AircraftState state = AircraftState.ON_GROUND;
	private long delay = 0;
	
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
	public long getDelay() {
		return delay;
	}

	public void setDelay(long delay) {
		this.delay = delay;
	}
	
	@Transient
	public boolean isDelayed() {
		return this.getDelay() > 0;
	}

	private List<ScheduledFlight> schedule = null;
	
	public void init() {
		
		ScheduledFlightRepository repository = ScheduledFlightRepository.getInstance();
		
		this.schedule = repository.getSchedule(this);
	}
	
	public void step(SimState state) {
		Simulation sim = (Simulation)state;
		
		if (this.schedule.size() == 0) {
			return;
		}
		
		ScheduledFlight currentTask = this.schedule.get(0);
		long departureSteps = currentTask.getDepartureSimSteps() + this.getDelay();
		long arrivalSteps = currentTask.getArrivalSimSteps() + this.getDelay();
		
		long steps = sim.schedule.getSteps();
		
		AircraftState currentState = this.getState();
		
		if (currentState == AircraftState.ON_GROUND && steps == departureSteps - 1) {
			double random = sim.random.nextDouble();
			if (random < 0.25) {
				this.setDelay(sim.random.nextLong(60));
			}
		} else if (currentState == AircraftState.ON_GROUND && steps == departureSteps) {
			this.setState(AircraftState.IN_AIR);
		} else if (currentState == AircraftState.IN_AIR && steps == arrivalSteps) {
			this.setState(AircraftState.ON_GROUND);
			this.setDelay(0);
		}
		
		double progress = Math.min(0, (steps - departureSteps) / (double)(arrivalSteps - departureSteps)),
		       x = 0, 
		       y = 0; // TODO calculate the aircraft's current location
		
		
		sim.field.setObjectLocation(this, new Double2D(x, y));
		
		// TODO register the next event with the scheduler  (include random delay here)
		//double time = 0; // TODO
		//sim.schedule.scheduleOnce(time, this);
	}
}
