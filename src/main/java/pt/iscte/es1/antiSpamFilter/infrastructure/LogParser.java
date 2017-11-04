package pt.iscte.es1.antiSpamFilter.infrastructure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import pt.iscte.es1.antiSpamFilter.domain.Message;
import pt.iscte.es1.antiSpamFilter.domain.Rule;

/**
 * Spam and Ham log parser
 *
 */
public class LogParser implements FileReaderParser<List<Message>> {

	private final List<Message> result = new ArrayList<>();

	/**
	 * Parses a log line
	 */
	@Override
	public void parse(String line) {
		String[] lineElements = line.split("\t");
		HashSet<Rule> lineResults = new HashSet<>();
		// ignores the first element (message name)
		for (int i = 1; i < lineElements.length; i++) {
			lineResults.add(new Rule(lineElements[i].trim()));
		}
		result.add(new Message(lineResults));
	}

	/**
	 * Returns the parsing results
	 */
	@Override
	public List<Message> getResult() {
		return result;
	}

}
