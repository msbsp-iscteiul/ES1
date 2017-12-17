package pt.iscte.es1.antiSpamFilter.infrastructure;

import pt.iscte.es1.antiSpamFilter.domain.PositiveNegativeSet;

/**
 * Solution quality parser
 * Parses the number of false negatives and positives for a solution.
 */
public class PositiveNegativeParser implements FileReaderParser<PositiveNegativeSet> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PositiveNegativeSet parse(String line) {
		final String[] positiveNegative = line.split("\\s+");
		return new PositiveNegativeSet(
			Double.valueOf(positiveNegative[0]),
			Double.valueOf(positiveNegative[1])
		);
	}
}
