package pt.iscte.es1.antiSpamFilter.domain;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

/**
 * Tests the {@link Solution}
 */
public class SolutionTest {

	private final List<Double> weights = Arrays.asList(1.0, 2.0);
	private final Solution item = new Solution(weights);

	/**
	 * Ensures exception is thrown on overflow
	 */
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowWhenWeightHigherThanFive() throws IllegalArgumentException {
		new Solution(Collections.singletonList(6.0));
	}

	/**
	 * Ensures exception is thrown on underflow
	 */
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowWhenWeightLowerThanMinusFive() throws IllegalArgumentException {
		new Solution(Collections.singletonList(-6.0));
	}

	/**
	 * Ensures weights are correctly initialized
	 */
	@Test
	public void getWeights() {
		assertEquals(weights, item.getWeights());
	}

	/**
	 * Tests the iterator
	 */
	@Test
	public void iterator() {
		final Iterator<Double> iterator = item.iterator();
		assertTrue(iterator.hasNext());
		assertEquals(new Double(1.0), iterator.next());
		assertEquals(new Double(2.0), iterator.next());
		assertFalse(iterator.hasNext());
	}

	/**
	 * Tests the iteration forEach method
	 */
	@Test
	public void forEach() {
		final Iterator<Double> iterator = weights.iterator();
		item.forEach(weight -> assertEquals(iterator.next(), weight));
	}

	/**
	 * Tests the spliterator
	 */
	@Test
	public void spliterator() {
		assertTrue(item.spliterator() instanceof Spliterator);
	}

	/**
	 * Tests the toString method
	 */
	@Test
	public void toStringTest() {
		assertEquals(weights.toString(), item.toString());
	}

	/**
	 * Tests the equality
	 */
	@Test
	public void equals() {
		assertTrue(new Solution(weights).equals(item));
		assertFalse(item.equals(null));
		assertTrue(item.equals(item));
		assertFalse(item.equals("Lorem Ipsum"));
	}

	/**
	 * Tests the hashCode method
	 */
	@Test
	public void hashCodeTest() {
		assertEquals(weights.hashCode(), item.hashCode());
	}

}
