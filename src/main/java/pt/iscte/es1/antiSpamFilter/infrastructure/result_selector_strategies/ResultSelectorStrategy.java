package pt.iscte.es1.antiSpamFilter.infrastructure.result_selector_strategies;

import pt.iscte.es1.antiSpamFilter.domain.PositiveNegativeSet;

import java.util.List;

/**
 * Strategy for selecting the best solution with a given profile
 */
public interface ResultSelectorStrategy {
	/**
	 * Find the index of the best solution for the given solutions list
	 * @param resultQualityList solutions list
	 * @return index of the best solution found
	 */
	int matchFor(List<PositiveNegativeSet> resultQualityList);
}
