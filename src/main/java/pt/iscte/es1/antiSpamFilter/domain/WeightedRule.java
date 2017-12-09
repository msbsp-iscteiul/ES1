package pt.iscte.es1.antiSpamFilter.domain;

import java.util.Objects;

/**
 * This class represents a rule that may contain an associated weight.
 */
public class WeightedRule {

	private final String name;
	private Double weight;

	/**
	 * Default constructor that accepts a name and an associated weight.
	 *
	 * @param name rule name
	 * @param weight rule weight
	 */
	public WeightedRule(String name, Double weight) {
		setWeight(weight);
		if (name == null) {
			throw new IllegalArgumentException("Name can not be null.");
		}
		this.name = name;
	}

	/**
	 * Constructor that accepts a name and fills in a neutral weight of 0.0.
	 *
	 * @param name rule name
	 */
	public WeightedRule(String name) {
		this(name, 0.0);
	}

	/**
	 * @return rule name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return rule weight
	 */
	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) throws IllegalArgumentException {
		validateWeight(weight);
		this.weight = weight;
	}

	private void validateWeight(Double weight) throws IllegalArgumentException {
		if (weight < -5 || weight > 5) {
			throw new IllegalArgumentException("Weight must be between -5 and 5.");
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		WeightedRule other = (WeightedRule) obj;
		return Objects.equals(name, other.name);
	}
}
