package pt.iscte.es1.antiSpamFilter.infrastructure;

import pt.iscte.es1.antiSpamFilter.domain.Message;
import pt.iscte.es1.antiSpamFilter.domain.WeightedRule;

import java.util.HashSet;

/**
 * Spam and Ham log Message parser.
 * Assumes Rules are separated by tabs
 */
public class LogParser implements FileReaderParser<Message> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public Message parse(String line) {
		final String[] lineElements = line.split("\\s+");
		final HashSet<WeightedRule> lineResults = new HashSet<>();
		// ignores the first element (message name)
		for (int i = 1; i < lineElements.length; i++) {
			lineResults.add(new WeightedRule(lineElements[i].trim()));
		}
		return new Message(lineResults);
	}
}
