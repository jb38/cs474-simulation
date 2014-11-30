package edu.hood.cs.sim;

import java.io.File;

public class Instrumentation {

	private static final Instrumentation instance = new Instrumentation();
	
	public static Instrumentation getInstance() {
		return instance;
	}
	
	private Instrumentation() {
		File file = new File('output.csv');
	}
	
	public void registerDelay(double delay, int numImpactedFlights) {
		// TODO
	}
	
}
