package pt.iscte.es1.antiSpamFilter.infrastructure.result_selector_strategies;

import pt.iscte.es1.antiSpamFilter.domain.PositiveNegativeSet;

import java.util.Comparator;
import java.util.List;

/**
 * Leisure strategy for selecting the best solution
 */
public class LeisureStrategy implements ResultSelectorStrategy {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int matchFor(List<PositiveNegativeSet> resultQualityList) {
		final Double minimumNegatives = resultQualityList.stream()
			.map(PositiveNegativeSet::getFalseNegatives)
			.sorted(Double::compare)
			.findFirst().get();
		final PositiveNegativeSet optimalPoint = resultQualityList.stream()
			// get all that have the minimum value
			.filter(positiveNegativeSet -> positiveNegativeSet.getFalseNegatives().equals(minimumNegatives))
			// break the tie by selecting lowest false positives
			.sorted(Comparator.comparingDouble(PositiveNegativeSet::getFalsePositives))
			.findFirst().get();
		return resultQualityList.indexOf(optimalPoint);
	}
}
