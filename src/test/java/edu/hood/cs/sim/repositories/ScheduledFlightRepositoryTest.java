package edu.hood.cs.sim.repositories;

import edu.hood.cs.sim.domain.Aircraft;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class ScheduledFlightRepositoryTest extends TestCase {

	public static Test suite() { return new TestSuite(ScheduledFlightRepositoryTest.class); }
	
	public ScheduledFlightRepositoryTest(String testName) {
		super(testName);
	}
	
	public void testFetch1() {
		
		ScheduledFlightRepository repository = ScheduledFlightRepository.getInstance();
		
		Aircraft aircraft = new Aircraft();
		aircraft.setTailNum("LOLNOPE");
		
		assertEquals(0, repository.getSchedule(aircraft).size());
	}
	
	public void testFetch2() {
		
		ScheduledFlightRepository repository = ScheduledFlightRepository.getInstance();
		
		Aircraft aircraft = new Aircraft();
		aircraft.setTailNum("N284JB");
		
		assertNotSame(0, repository.getSchedule(aircraft).size());
	}
}
