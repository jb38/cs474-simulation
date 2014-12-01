// This class writes pipe-delimited output to a text file.
// The content written comes from the Aircraft class, which
// is used to generate delays at random for the flights, and 
// then will give us a number for total impacted flights.

package edu.hood.cs.sim;

import edu.hood.cs.sim.domain.Aircraft;
import java.io.FileWriter;
import java.io.IOException;
<<<<<<< HEAD
import java.text.SimpleDateFormat;
import java.util.Date;

public class Instrumentation {

	// TODO maybe create a domain POJO and store the values in the database
	
	public static final String DELIMITER = "|";
	
	private static final Instrumentation instance = new Instrumentation();
	
	private FileWriter out = null;
=======
import java.io.Writer;

public class Instrumentation {

	private static final Instrumentation instance = new Instrumentation();
	
	private int numImpactedFlights = 0;
	private double delay = 0;
	
	Aircraft aircraft = new Aircraft();
	
	
>>>>>>> 8d9b07c93594e6d8d2954f3444cd740f2655e683
	
	public static Instrumentation getInstance() {
		return instance;
	}
	
	private Instrumentation() {
		
	}
	
	// TODO
			// will spit out into text file (pipes, tabs, comma...whatever delimited)
			// the delays of each flight. should have year/month/day/hour/second of when the 
			// class is first opened/created (singleton...only one instance globally for this class)
			// static method getInstance...
			// delay and the number of flights impacted are what will get pushed to text file (one entry per run)
			// 
	
	public void registerDelay(double delay, int numImpactedFlights) {
<<<<<<< HEAD
		try {
			String[] values = {
				Double.toString(delay),
				Integer.toString(numImpactedFlights, 10)
			};
			writeRow(String.join(DELIMITER, values));
		} catch (IOException ioe) {
			
		}
=======
		delay = aircraft.getDelay(); 
		this.numImpactedFlights = numImpactedFlights;
>>>>>>> 8d9b07c93594e6d8d2954f3444cd740f2655e683
	}
	
	
	
}
