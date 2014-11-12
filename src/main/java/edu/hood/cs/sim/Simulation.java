package edu.hood.cs.sim;

import sim.engine.SimState;
import edu.hood.cs.sim.repositories.AircraftRepository;
import edu.hood.cs.sim.util.HibernateUtil;

@SuppressWarnings("serial")
public class Simulation extends SimState {
		
	public Simulation(long seed) {
		super(seed);
	}
	
	@Override
	public void start() {
		super.start();
		
		System.out.println(AircraftRepository.getInstance().fetchAll().size());
	}
	
	@Override
	public void finish() {
		super.finish();
		
		HibernateUtil.getSessionFactory().close();
	}

	public static void main(String[] args) {
		doLoop(Simulation.class, args);
		
		System.exit(0);
	}
}
