package pt.iscte.es1.antiSpamFilter.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class WeightedRuleTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldRejectWeightAboveLegalInterval() throws IllegalArgumentException {
		new WeightedRule("whatever", 6.0);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void shouldRejectWeightUnderLegalInterval() throws Exception {
		new WeightedRule("whatever", -5.1);
	}
	
	@Test
	public void shouldDefaultToZeroWeight() throws Exception {
		assertEquals(new Double(0.0), new WeightedRule("name").getWeight());
	}
	
	@Test
	public void shouldAcceptLegalInterval() throws Exception {
		assertEquals(new Double(1.0), new WeightedRule("name", 1.0).getWeight());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void requiresValidName() throws Exception {
		new WeightedRule(null);
	}

	@Test
	public void shouldBeNameable() throws Exception {
		assertEquals("name", new WeightedRule("name").getName());
	}
	
	@Test
	public void shouldEqualByName() throws Exception {
		WeightedRule weightedRule1 = new WeightedRule("name", 1.0);
		WeightedRule weightedRule2 = new WeightedRule("name", 2.0);
		assertTrue(weightedRule1.equals(weightedRule2));
		assertTrue(weightedRule1.hashCode() == weightedRule2.hashCode());
	}

	@Test
	public void shouldEqualSelf() throws Exception {
		WeightedRule weightedRule1 = new WeightedRule("name");
		assertEquals(weightedRule1, weightedRule1);
	}
	
	@Test
	public void shouldNotEqual() throws Exception {
		WeightedRule weightedRule = new WeightedRule("name");
		assertFalse(weightedRule.equals(null));
		assertFalse(weightedRule.equals(new Double(0.0)));
	}
}
