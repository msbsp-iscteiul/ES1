package pt.iscte.es1.antiSpamFilter.domain;

import java.util.Arrays;
import java.util.Objects;

public class PositiveNegativeSet {
	
	private Double positive;
	private Double negative;
	
	public PositiveNegativeSet() {
		
	}
	
	public PositiveNegativeSet(Double positive, Double negative) {
		this.positive = positive;
		this.negative = negative;
	}
	
	public void setPositive(Double positive) {
		this.positive = positive;
	}
	
	public Double getPositive() {
		return positive;
	}
	
	public void setNegative(Double negative) {
		this.negative = negative;
	}
	
	public Double getNegative() {
		return negative;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(Arrays.asList(positive, negative));
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (this == obj)
			return true;
		if (getClass() != obj.getClass())
			return false;
		PositiveNegativeSet other = (PositiveNegativeSet) obj;
		return Objects.equals(positive, other.positive) && Objects.equals(negative, other.negative);
	}
	
	@Override
	public String toString() {
		return "(" + positive.toString() + ", " + negative.toString() + ")";
	}
	
}
