package pt.iscte.es1.antiSpamFilter.infrastructure;

import pt.iscte.es1.antiSpamFilter.domain.Solution;

import java.util.ArrayList;
import java.util.List;

/**
 * Parses the result weights from jMetal's experiments
 */
public class ExperimentResultWeightsParser implements FileReaderParser<Solution> {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public Solution parse(String line) {
		final String[] items = line.split("\\s+");
		final List<Double> weights = new ArrayList<>(items.length);
		for (String item : items) {
			weights.add(Double.valueOf(item));
		}
		return new Solution(weights);
	}
}
