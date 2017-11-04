package pt.iscte.es1.antiSpamFilter.domain;

import java.util.Objects;

public class WeightedRule {
	private final String name;
	private final Double weight;

	public WeightedRule(String name, Double weight) {
		this.name = name;
		this.weight = weight;
	}
	
	public WeightedRule(String name) {
		this(name, 0.0);
	}
	
	public String getName() {
		return name;
	}
	
	public Double getWeight() {
		return weight;
	}

	@Override
	public int hashCode() {
		return Objects.hash(name);
	}

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
