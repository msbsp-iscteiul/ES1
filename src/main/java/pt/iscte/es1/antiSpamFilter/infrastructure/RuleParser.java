package pt.iscte.es1.antiSpamFilter.infrastructure;

import java.util.ArrayList;
import java.util.List;

import pt.iscte.es1.antiSpamFilter.domain.WeightedRule;

/**
 * Rules file parser
 * It parses rules and, optionally, weights from a rules file.
 * Assumes rules and weights are separated by a tab.
 */
public class RuleParser implements FileReaderParser<List<WeightedRule>> {

	private final ArrayList<WeightedRule> result = new ArrayList<>();

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void parse(String line) {
		String[] ruleLine = line.split("\t");
		if (ruleLine.length == 2) {
			result.add(new WeightedRule(ruleLine[0].trim(), Double.valueOf(ruleLine[1])));	
		} else {
			result.add(new WeightedRule(ruleLine[0].trim()));
		}
		
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<WeightedRule> getResult() {
		return result;
	}

}
