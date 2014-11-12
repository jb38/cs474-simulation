package edu.hood.cs.sim.repositories;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class AircraftRepositoryTest extends TestCase {

	public static Test suite() { return new TestSuite(AircraftRepositoryTest.class); }
	
	public AircraftRepositoryTest(String testName) {
		super(testName);
	}
	
	public void testFetchAll() {
		
		AircraftRepository repository = AircraftRepository.getInstance();

		assertNotSame(0, repository.fetchAll().size());
	}
}
