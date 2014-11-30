package edu.hood.cs.sim;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Instrumentation {

	// TODO maybe create a domain POJO and store the values in the database
	
	public static final String DELIMITER = "|";
	
	private static final Instrumentation instance = new Instrumentation();
	
	private FileWriter out = null;
	
	public static Instrumentation getInstance() {
		return instance;
	}
	
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
