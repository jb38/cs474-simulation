package edu.hood.cs.sim;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import edu.hood.cs.sim.domain.Metric;
import edu.hood.cs.sim.repositories.MetricRepository;

public class Instrumentation {

	public static final String DELIMITER = "|";

	private static final Logger log = LogManager
			.getLogger(Instrumentation.class);
	private static final Instrumentation instance = new Instrumentation();

	public static Instrumentation getInstance() {
		return instance;
	}

	private String runId = null;

	private Instrumentation() {
		this.runId = this.getRunId();
	}

	private String getRunId() {
		return new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
	}

	public void registerDelay(String flightNumber, String tailNumber,
			double delay, int numImpactedFlights) {

		log.info(String.format("Delay: %.0f Impacted: %d", delay,
				numImpactedFlights));

		Metric metric = new Metric(this.runId, flightNumber, tailNumber, (int)delay, numImpactedFlights);
		MetricRepository.getInstance().insert(metric);
	}
}