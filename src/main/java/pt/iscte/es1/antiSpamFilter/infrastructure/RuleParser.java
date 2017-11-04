package pt.iscte.es1.antiSpamFilter.infrastructure;

import java.util.ArrayList;
import java.util.List;

import pt.iscte.es1.antiSpamFilter.domain.Rule;

public class RuleParser implements FileReaderParser<List<Rule>> {

	private final ArrayList<Rule> result = new ArrayList<>();
	
	@Override
	public void parse(String line) {
		result.add(new Rule(line.trim()));
	}

	@Override
	public List<Rule> getResult() {
		return result;
	}

}
