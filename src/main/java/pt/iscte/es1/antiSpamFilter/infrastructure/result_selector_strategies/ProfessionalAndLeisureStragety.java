package pt.iscte.es1.antiSpamFilter.infrastructure.result_selector_strategies;

import pt.iscte.es1.antiSpamFilter.domain.PositiveNegativeSet;

import java.util.List;

/**
 * Professional and Leisure strategy for selecting the best solution
 */
public class ProfessionalAndLeisureStragety implements ResultSelectorStrategy {
	/**
	 * {@inheritDoc}
	 */
	@Override
	public int matchFor(List<PositiveNegativeSet> resultQualityList) {
		final PositiveNegativeSet optimalPoint = resultQualityList.stream()
			.sorted((p1, p2) -> {
				return Double.compare(
					distanceFromOrigin(p1.getFalseNegatives(), p1.getFalsePositives()),
					distanceFromOrigin(p2.getFalseNegatives(), p2.getFalsePositives())
				);
			})
			.findFirst().get();
		return resultQualityList.indexOf(optimalPoint);
	}

	/**
	 * Get distance from the origin given a point
	 * @param x position in x axis
	 * @param y position in y axis
	 * @return distance from origin
	 */
	private static double distanceFromOrigin(double x, double y) {
		return Math.sqrt(x * x + y * y);
	}
}
