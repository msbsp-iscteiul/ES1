package pt.iscte.es1.antiSpamFilter.infrastructure;

import java.util.ArrayList;
import java.util.List;

import pt.iscte.es1.antiSpamFilter.domain.WeightedRule;

public class RuleParser implements FileReaderParser<List<WeightedRule>> {

	private final ArrayList<WeightedRule> result = new ArrayList<>();
	
	@Override
	public void parse(String line) {
		String[] ruleLine = line.split("\t");
		if (ruleLine.length == 2) {
			result.add(new WeightedRule(ruleLine[0].trim(), Double.valueOf(ruleLine[1])));	
		} else {
			result.add(new WeightedRule(ruleLine[0].trim()));
		}
		
	}

	@Override
	public List<WeightedRule> getResult() {
		return result;
	}

}
