package pt.iscte.es1.antiSpamFilter.infrastructure.result_selector_strategies;

import org.junit.Test;
import pt.iscte.es1.antiSpamFilter.domain.PositiveNegativeSet;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Tests the {@link LeisureStrategy}
 */
public class LeisureStrategyTest {

	/**
	 * Ensures it selects the solution with the lowest false negatives count
	 */
	@Test
	public void shouldSelectLowestFalseNegativeSet() {
		final List<PositiveNegativeSet> list = Arrays.asList(
			new PositiveNegativeSet(0.0, 100.0),
			new PositiveNegativeSet(0.0, 50.0),
			new PositiveNegativeSet(0.0, 10.0),
			new PositiveNegativeSet(0.0, 0.0)
		);
		final int selectedIndex = new LeisureStrategy().matchFor(list);
		assertEquals(3, selectedIndex);
	}

	/**
	 * Ensures it solves a tie case, returning the lowest false positive solution
	 */
	@Test
	public void whenTiedShouldSelectLowestFalsePositive() {
		final List<PositiveNegativeSet> list = Arrays.asList(
			new PositiveNegativeSet(10.0, 0.0),
			new PositiveNegativeSet(5.0, 0.0),
			new PositiveNegativeSet(2.0, 0.0),
			new PositiveNegativeSet(1.0, 100.0)
		);
		final int selectedIndex = new LeisureStrategy().matchFor(list);
		assertEquals(2, selectedIndex);
	}
}
