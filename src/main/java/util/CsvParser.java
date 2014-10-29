package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CsvParser {

	private CsvParser() { }

	public static List<Map<String, String>> parse(InputStream stream) throws IOException {

		List<Map<String, String>> retVal = new ArrayList<Map<String, String>>();

		Scanner scanner = new Scanner(stream);
		String line = null;
		String[] fields = null;
		String[] tokens = null;

		line = scanner.nextLine();
		fields = line.split(",");

		while ((line = scanner.nextLine()) != null) {
			tokens = line.split(",");

			Map<String, String> map = new HashMap<String, String>();

			for (int i = 0; i < tokens.length; i++) {
				map.put(fields[i], tokens[i]);
			}

			retVal.add(map);
		}

		scanner.close();

		return retVal;
	}

}
