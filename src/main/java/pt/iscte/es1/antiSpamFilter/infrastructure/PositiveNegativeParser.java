package pt.iscte.es1.antiSpamFilter.infrastructure;

import pt.iscte.es1.antiSpamFilter.domain.PositiveNegativeSet;

public class PositiveNegativeParser implements FileReaderParser<PositiveNegativeSet> {

	@Override
	public PositiveNegativeSet parse(String line) {
		final String[] positiveNegative = line.split("\\s+");
		return new PositiveNegativeSet(
			Double.valueOf(positiveNegative[0]),
			Double.valueOf(positiveNegative[1])
		);
	}
}
