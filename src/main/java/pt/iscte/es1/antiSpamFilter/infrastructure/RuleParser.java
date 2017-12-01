package pt.iscte.es1.antiSpamFilter.infrastructure;

import pt.iscte.es1.antiSpamFilter.domain.WeightedRule;

/**
 * Rules file parser
 * It parses rules and, optionally, weights from a rules file.
 * Assumes rules and weights are separated by a tab.
 */
public class RuleParser implements FileReaderParser<WeightedRule> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public WeightedRule parse(String line) {
		final String[] ruleLine = line.split("\\s+");
		if (ruleLine.length == 2) {
			return new WeightedRule(ruleLine[0].trim(), Double.valueOf(ruleLine[1]));
		} else {
			return new WeightedRule(ruleLine[0].trim());
		}
	}
}
