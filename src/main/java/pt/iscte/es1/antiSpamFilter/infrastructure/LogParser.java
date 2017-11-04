package pt.iscte.es1.antiSpamFilter.infrastructure;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import pt.iscte.es1.antiSpamFilter.domain.Message;
import pt.iscte.es1.antiSpamFilter.domain.WeightedRule;

/**
 * Spam and Ham log Message parser.
 * Assumes Rules are separated by tabs 
 */
public class LogParser implements FileReaderParser<List<Message>> {

	private final List<Message> result = new ArrayList<>();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void parse(String line) {
		String[] lineElements = line.split("\t");
		HashSet<WeightedRule> lineResults = new HashSet<>();
		// ignores the first element (message name)
		for (int i = 1; i < lineElements.length; i++) {
			lineResults.add(new WeightedRule(lineElements[i].trim()));
		}
		result.add(new Message(lineResults));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Message> getResult() {
		return result;
	}

}
