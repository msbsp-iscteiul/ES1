package pt.iscte.es1.antiSpamFilter.domain;

import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

/**
 * Tests the {@link PositiveNegativeSet}
 */
public class PositiveNegativeSetTest {

	private final PositiveNegativeSet item = new PositiveNegativeSet(1.0, 2.0);

	/**
	 * Ensures only a positive count of false positives are accepted
	 */
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowOnNegativeFalsePositives() throws IllegalArgumentException {
		new PositiveNegativeSet(-1.0, 0.0);
	}

	/**
	 * Ensures only a positive count of false negatives are accepted
	 */
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowOnNegativeFalseNegatives() throws IllegalArgumentException {
		new PositiveNegativeSet(0.0, -1.0);
	}

	/**
	 * Ensures the false positives are correctly initialized
	 */
	@Test
	public void getFalsePositives() {
		assertEquals(new Double(1.0), item.getFalsePositives());
	}

	/**
	 * Ensures the false negatives are correctly initialized
	 */
	@Test
	public void getFalseNegatives() {
		assertEquals(new Double(2.0), item.getFalseNegatives());
	}

	/**
	 * Tests the hashCode method
	 */
	@Test
	public void hashCodeTest() {
		assertEquals(Objects.hash(1.0, 2.0), item.hashCode());
	}

	/**
	 * Tests the equality
	 */
	@Test
	public void equals() {
		assertTrue(item.equals(item));
		assertTrue(item.equals(new PositiveNegativeSet(1.0, 2.0)));
		assertFalse(item.equals(null));
		assertFalse(item.equals(new Double(1.0)));
	}

	/**
	 * Tests the toString method
	 */
	@Test
	public void toStringTest() {
		assertEquals("(1.0, 2.0)", item.toString());
	}

}
