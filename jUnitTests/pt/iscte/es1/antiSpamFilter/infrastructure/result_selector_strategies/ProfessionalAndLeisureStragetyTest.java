package pt.iscte.es1.antiSpamFilter.infrastructure.result_selector_strategies;

import org.junit.Test;
import pt.iscte.es1.antiSpamFilter.domain.PositiveNegativeSet;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Tests the {@link ProfessionalAndLeisureStragety}
 */
public class ProfessionalAndLeisureStragetyTest {

	/**
	 * Ensures it selects the closes point to the origin
	 */
	@Test
	public void shouldSelectClosestToOrigin() {
		final List<PositiveNegativeSet> list = Arrays.asList(
			new PositiveNegativeSet(100.0, 100.0),
			new PositiveNegativeSet(50.0, 50.0),
			new PositiveNegativeSet(0.0, 0.0),
			new PositiveNegativeSet(10.0, 10.0)
		);
		final int result = new ProfessionalAndLeisureStragety().matchFor(list);
		assertEquals(2, result);
	}
}
