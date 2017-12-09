package pt.iscte.es1.antiSpamFilter.infrastructure.result_selector_strategies;

import org.junit.Test;
import pt.iscte.es1.antiSpamFilter.domain.PositiveNegativeSet;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ProfessionalStrategyTest {
	@Test
	public void shouldSelectLowestFalsePositiveSet() {
		final List<PositiveNegativeSet> list = Arrays.asList(
			new PositiveNegativeSet(100.0, 0.0),
			new PositiveNegativeSet(50.0, 0.0),
			new PositiveNegativeSet(0.0, 0.0),
			new PositiveNegativeSet(10.0, 0.0)
		);
		final int selectedIndex = new ProfessionalStrategy().matchFor(list);
		assertEquals(2, selectedIndex);
	}

	@Test
	public void whenTiedShouldSelectLowestFalseNegative() {
		final List<PositiveNegativeSet> list = Arrays.asList(
			new PositiveNegativeSet(0.0, 10.0),
			new PositiveNegativeSet(0.0, 2.0),
			new PositiveNegativeSet(9.0, 0.0),
			new PositiveNegativeSet(0.0, 100.0)
		);
		final int selectedIndex = new ProfessionalStrategy().matchFor(list);
		assertEquals(1, selectedIndex);
	}
}
