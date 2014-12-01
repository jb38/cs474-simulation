package edu.hood.cs.sim;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

//TODO maybe create a domain POJO and store the values in the database

public class Instrumentation {

	public static final String DELIMITER = "|";
	
	private static final Logger log = LogManager.getLogger(Instrumentation.class);
	private static final Instrumentation instance = new Instrumentation();
	
	public static Instrumentation getInstance() {
		return instance;
	}
	
	private FileWriter out = null;
	
	private Instrumentation() {
		try {
			this.out = new FileWriter(this.buildFileName());
			this.writeHeaderRow();
		} catch (IOException e) {
			
		}
	}
	
	private String buildFileName() {
		return new SimpleDateFormat("'output-'yyyyMMddHHmmss'.csv'").format(new Date());
	}
	
	private void writeHeaderRow() throws IOException {
		String[] columns = { "DELAY", "NUM_FLIGHTS_IMPACTED" };
		writeRow(String.join(DELIMITER, columns));
	}
	
	private void writeRow(String row) throws IOException {
		out.write(row);
		out.write('\n');
	}

	public void registerDelay(double delay, int numImpactedFlights) {
		
		log.info(String.format("Delay: %.0f Impacted: %d", delay, numImpactedFlights));
		
		try {
			String[] values = {
				Double.toString(delay),
				Integer.toString(numImpactedFlights, 10)
			};
			writeRow(String.join(DELIMITER, values));
		} catch (IOException ioe) {
			
		}
	}
}
