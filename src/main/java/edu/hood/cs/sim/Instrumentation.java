package edu.hood.cs.sim;

public class Instrumentation {

	private static final Instrumentation instance = new Instrumentation();
	
	public static Instrumentation getInstance() {
		return instance;
	}
	
	private Instrumentation() {
		
	}
	
	public void registerDelay(double delay, int numImpactedFlights) {
		// TODO
	}
	
}
