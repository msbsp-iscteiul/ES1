package pt.iscte.es1.antiSpamFilter.domain;

import java.util.Objects;

/**
 * Pareto set containing number of false positives and negatives
 * for a solution
 */
public class PositiveNegativeSet {

	private final Double falsePositives;
	private final Double falseNegatives;

	/**
	 * {@link PositiveNegativeSet} constructor
	 * @param falsePositives number of false positives in a solution
	 * @param falseNegatives number of false negatives in a solution
	 */
	public PositiveNegativeSet(Double falsePositives, Double falseNegatives) {
		validateInput(falsePositives);
		validateInput(falseNegatives);
		this.falsePositives = falsePositives;
		this.falseNegatives = falseNegatives;
	}

	/**
	 * Ensure the value is valid
	 * @param value validation target
	 * @throws IllegalArgumentException when value is negative
	 */
	private static void validateInput(Double value) throws IllegalArgumentException {
		if (value < 0.0) {
			throw new IllegalArgumentException("False positives and negatives must be positive");
		}
	}

	/**
	 * Get the number of false positives
	 * @return number of false positives
	 */
	public Double getFalsePositives() {
		return falsePositives;
	}

	/**
	 * Get the number of false negatives
	 * @return number of false negatives
	 */
	public Double getFalseNegatives() {
		return falseNegatives;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return Objects.hash(falsePositives, falseNegatives);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		PositiveNegativeSet other = (PositiveNegativeSet) obj;
		return Objects.equals(falsePositives, other.falsePositives) && Objects.equals(falseNegatives, other.falseNegatives);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "(" + falsePositives.toString() + ", " + falseNegatives.toString() + ")";
	}

}
