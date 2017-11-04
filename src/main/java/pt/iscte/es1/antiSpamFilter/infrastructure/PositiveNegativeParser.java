package pt.iscte.es1.antiSpamFilter.infrastructure;

import java.util.ArrayList;
import java.util.List;

import pt.iscte.es1.antiSpamFilter.domain.PositiveNegativeSet;

public class PositiveNegativeParser implements FileReaderParser<List<PositiveNegativeSet>> {
	
	private final ArrayList<PositiveNegativeSet> result = new ArrayList<>();

	@Override
	public void parse(String line) {
		String[] positiveNegative = line.split(" ");
		if (positiveNegative != null) {
			if (positiveNegative.length == 2) {
				PositiveNegativeSet pn = new PositiveNegativeSet(Double.valueOf(positiveNegative[0]), Double.valueOf(positiveNegative[1]));
				result.add(pn);
			}
		}
	}

	@Override
	public List<PositiveNegativeSet> getResult() {
		return result;
	}

}
