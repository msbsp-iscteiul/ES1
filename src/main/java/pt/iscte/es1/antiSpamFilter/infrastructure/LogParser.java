package pt.iscte.es1.antiSpamFilter.infrastructure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Spam and Ham log parser
 *
 */
public class LogParser implements FileReaderParser<List<HashSet<String>>> {

	private final List<HashSet<String>> result = new ArrayList<>();

	/**
	 * Parses a log line
	 */
	@Override
	public void parse(String line) {
		String[] lineElements = line.split("\t");
		HashSet<String> lineResults = new HashSet<>();
		// ignores the first element (message name)
		for (int i = 1; i < lineElements.length; i++) {
			lineResults.add(lineElements[i]);
		}
		result.add(lineResults);
	}

	/**
	 * Returns the parsing results
	 */
	@Override
	public List<HashSet<String>> getResult() {
		return result;
	}

}
