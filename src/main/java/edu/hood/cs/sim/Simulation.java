package edu.hood.cs.sim;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import sim.engine.SimState;
import sim.field.network.Network;
import util.CsvParser;
import edu.hood.cs.sim.domain.Airport;

@SuppressWarnings("serial")
public class Simulation extends SimState {
	
	private Network network = new Network(true);
	
	private static final String AIRPORTS_FILE = "airports.csv";
	
	public Simulation(long seed) {
		super(seed);
	}
	
	@Override
	public void start() {
		super.start();
		
		try {
			Properties p = new Properties();
			p.load(getClass().getResourceAsStream(AIRPORTS_FILE));
			
			
		} catch (IOException ioe) {
			
		}
		//network.addNode(node); // TODO add a node for each airport
	}
	
	@Override
	public void finish() {
		super.finish();
	}

	public static void main(String[] args) {
		doLoop(Simulation.class, args);
		System.exit(0);
	}
}
