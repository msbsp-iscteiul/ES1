package pt.iscte.es1.antiSpamFilter.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the {@link WeightedRule}
 */
public class WeightedRuleTest {

	/**
	 * Ensures an exception is thrown when overflow occurs
	 */
	@Test(expected = IllegalArgumentException.class)
	public void shouldRejectWeightAboveLegalInterval() throws IllegalArgumentException {
		new WeightedRule("whatever", 6.0);
	}

	/**
	 * Ensures an exception is thrown when underflow occurs
	 */
	@Test(expected = IllegalArgumentException.class)
	public void shouldRejectWeightUnderLegalInterval() throws IllegalArgumentException {
		new WeightedRule("whatever", -5.1);
	}

	/**
	 * Ensures the default weight is 0
	 */
	@Test
	public void shouldDefaultToZeroWeight() {
		assertEquals(new Double(0.0), new WeightedRule("name").getWeight());
	}

	/**
	 * Ensures the weight is correctly initialized
	 */
	@Test
	public void shouldAcceptLegalInterval() {
		assertEquals(new Double(1.0), new WeightedRule("name", 1.0).getWeight());
	}

	/**
	 * Ensures an exception is thrown when no name is given
	 */
	@Test(expected = IllegalArgumentException.class)
	public void requiresValidName() throws IllegalArgumentException {
		new WeightedRule(null);
	}

	/**
	 * Ensures the name is correctly initialized
	 */
	@Test
	public void shouldBeNameable() {
		assertEquals("name", new WeightedRule("name").getName());
	}

	/**
	 * Ensures the weights are comparable only by name
	 */
	@Test
	public void shouldEqualByName() {
		WeightedRule weightedRule1 = new WeightedRule("name", 1.0);
		WeightedRule weightedRule2 = new WeightedRule("name", 2.0);
		assertTrue(weightedRule1.equals(weightedRule2));
		assertTrue(weightedRule1.hashCode() == weightedRule2.hashCode());
	}

	/**
	 * Ensures it equals itself
	 */
	@Test
	public void shouldEqualSelf() {
		WeightedRule weightedRule1 = new WeightedRule("name");
		assertEquals(weightedRule1, weightedRule1);
	}

	/**
	 * Tests inequalities
	 */
	@Test
	public void shouldNotEqual() {
		WeightedRule weightedRule = new WeightedRule("name");
		assertFalse(weightedRule.equals(null));
		assertFalse(weightedRule.equals(new Double(0.0)));
	}
}
