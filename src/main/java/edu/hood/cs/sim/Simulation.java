package edu.hood.cs.sim;

import java.util.List;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import sim.engine.SimState;
import sim.field.continuous.Continuous2D;
import sim.util.Double2D;
import edu.hood.cs.sim.domain.Aircraft;
import edu.hood.cs.sim.domain.Airport;
import edu.hood.cs.sim.repositories.AircraftRepository;
import edu.hood.cs.sim.repositories.AirportRepository;
import edu.hood.cs.sim.util.HibernateUtil;

@SuppressWarnings("serial")
public class Simulation extends SimState {
	
	private static final Logger log = LogManager.getLogger(Simulation.class);
	
	public Continuous2D field;
	
	public double offsetX = 0;
	public double offsetY = 0;
	public double scale = 1;
	
	public Simulation(long seed) {
		super(seed);
	}
	
	@Override
	public void start() {
		super.start();
		
		log.info("simulation starting");
		
		field = new Continuous2D(0, 100, 100);
		
		createAirports();
		createAircraft();
	}
	
	@Override
	public void finish() {
		super.finish();
		
		log.info("simulation ended");
		
		// clean up database connections
		HibernateUtil.getSessionFactory().close();
	}
	
	/**
	 * Loads all airports from the database
	 */
	public void createAirports() {
		
		// TODO determine if this method is necessary (it only will
		//      be if the visualization becomes a map-like interface
		
		AirportRepository repository = AirportRepository.getInstance();
		
		List<Airport> airports = repository.fetchAll();
		
		double xmin = airports.get(0).getLongitude(), 
			   ymin = airports.get(0).getLatitude(), 
			   xmax = xmin, 
			   ymax = ymin;
		
		for (Airport airport : airports) {
			double x = airport.getLongitude(),
				   y = airport.getLatitude();
			
			xmin = Math.min(xmin, x);
			ymin = Math.min(ymin, y);
			xmax = Math.max(x, xmax);
			ymax = Math.max(ymax, y);
		}
		
		double width = xmax - xmin,
			   height = ymax - ymin;
		
		this.scale = 100.0 / Math.max(width, height);
		
		this.offsetX = xmin + width * 0.05;
		this.offsetY = ymin + height * 0.05;
		
		if (width < height) { // portrait
			// adjust the offsetX
			this.offsetX -= ((height - width)) / 2.0;
			
		} else { // landscape
			// adjust the offsetY
			this.offsetY += ((width - height)) / 2.0;
		}
		
		this.scale *= 0.9;
		
		// only location is registered -- state is maintained
		// by the individual aircraft for the simulation
		for (Airport airport : airports) {
			field.setObjectLocation(
					airport, 
					new Double2D(
							(airport.getLongitude() - this.offsetX) * this.scale, 
							100 - ((airport.getLatitude() - this.offsetY) * this.scale)));
		}
	}
	
	/**
	 * Loads all aircraft from the database
	 */
	public void createAircraft() {
		AircraftRepository repository = AircraftRepository.getInstance();
		
		for (Aircraft aircraft : repository.fetchAll()) {
			aircraft.init(this);
		}
	}

	/**
	 * Main entry point to execute the simulation
	 * @param args
	 */
	public static void main(String[] args) {

		// run the simulation until it completes (blocking)
		doLoop(Simulation.class, args);
		
		// cleanly exit when done
		System.exit(0);
	}
}
