package edu.hood.cs.sim.repositories;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AirportRepositoryTest extends TestCase {

	public static Test suite() { return new TestSuite(AirportRepositoryTest.class); }
	
	public AirportRepositoryTest(String testName) {
		super(testName);
	}
	
	public void testFetch1() {
		
		AirportRepository repository = AirportRepository.getInstance();
		
		assertNull(repository.fetch("LOL"));
	}
	
	public void testFetch2() {
		
		AirportRepository repository = AirportRepository.getInstance();
		
		assertNotNull(repository.fetch("IAD"));
	}
	
	public void testFetchAll() {
		
		AirportRepository repository = AirportRepository.getInstance();

		assertNotSame(0, repository.fetchAll().size());
	}
}
