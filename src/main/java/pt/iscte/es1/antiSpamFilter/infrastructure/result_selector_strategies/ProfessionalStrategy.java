package pt.iscte.es1.antiSpamFilter.infrastructure.result_selector_strategies;

import pt.iscte.es1.antiSpamFilter.domain.PositiveNegativeSet;

import java.util.Comparator;
import java.util.List;

/**
 * Professional strategy for selecting the best solution
 */
public class ProfessionalStrategy implements ResultSelectorStrategy {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int matchFor(List<PositiveNegativeSet> resultQualityList) {
		final Double minimumPositives = resultQualityList.stream()
			.map(PositiveNegativeSet::getFalsePositives)
			.sorted(Double::compare)
			.findFirst().get();
		final PositiveNegativeSet optimalPoint = resultQualityList.stream()
			// get all that have the minimum value
			.filter(positiveNegativeSet -> positiveNegativeSet.getFalsePositives().equals(minimumPositives))
			// break the tie by selecting lowest false negatives
			.sorted(Comparator.comparingDouble(PositiveNegativeSet::getFalseNegatives))
			.findFirst().get();
		return resultQualityList.indexOf(optimalPoint);
	}
}
