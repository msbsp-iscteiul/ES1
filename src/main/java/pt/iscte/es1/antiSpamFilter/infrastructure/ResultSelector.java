package pt.iscte.es1.antiSpamFilter.infrastructure;

import pt.iscte.es1.antiSpamFilter.AntiSpamFilterConstants;
import pt.iscte.es1.antiSpamFilter.domain.PositiveNegativeSet;
import pt.iscte.es1.antiSpamFilter.domain.Solution;
import pt.iscte.es1.antiSpamFilter.infrastructure.result_selector_strategies.LeisureStrategy;
import pt.iscte.es1.antiSpamFilter.infrastructure.result_selector_strategies.ProfessionalAndLeisureStragety;
import pt.iscte.es1.antiSpamFilter.infrastructure.result_selector_strategies.ProfessionalStrategy;
import pt.iscte.es1.antiSpamFilter.infrastructure.result_selector_strategies.ResultSelectorStrategy;

import java.util.List;
import java.util.Objects;

/**
 * For a given strategy returns the best weights
 */
public class ResultSelector {
	private ResultSelectorStrategy strategy;

	/**
	 * Builds a Result Selector with a profile
	 *
	 * @param profile inbox profile name
	 * @return Selector with the strategy
	 */
	public static ResultSelector createForProfile(String profile) {
		if (AntiSpamFilterConstants.STRATEGY_LEISURE.equals(profile)) {
			return new ResultSelector(new LeisureStrategy());
		} else if (AntiSpamFilterConstants.STRATEGY_PROFESSIONAL.equals(profile)) {
			return new ResultSelector(new ProfessionalStrategy());
		} else if (AntiSpamFilterConstants.STRATEGY_MIXED.equals(profile)) {
			return new ResultSelector(new ProfessionalAndLeisureStragety());
		}
		throw new IllegalArgumentException("Profile doesn't exist");
	}

	/**
	 * @param strategy selector strategy
	 */
	public ResultSelector(ResultSelectorStrategy strategy) {
		this.strategy = strategy;
	}

	/**
	 * Get the weights for the best solution for the given strategy
	 * @param resultQualityList solution quality indicators list
	 * @param resultWeightsList weights for the given solutions
	 * @return weights for the best solution for the given strategy
	 */
	public Solution selectFromResults(
		List<PositiveNegativeSet> resultQualityList,
		List<Solution> resultWeightsList
	) {
		final int bestMatchIndex = strategy.matchFor(resultQualityList);
		return resultWeightsList.get(bestMatchIndex);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		ResultSelector that = (ResultSelector) o;
		return strategy.getClass().equals(that.strategy.getClass());
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return Objects.hash(strategy.getClass());
	}
}
