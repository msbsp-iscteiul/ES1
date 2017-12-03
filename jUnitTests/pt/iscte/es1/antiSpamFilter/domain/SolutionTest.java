package pt.iscte.es1.antiSpamFilter.domain;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.*;

public class SolutionTest {

	private final List<Double> weights = Arrays.asList(1.0, 2.0);
	private final Solution item = new Solution(weights);

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowWhenWeightHigherThanFive() throws Exception {
		new Solution(Collections.singletonList(6.0));
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowWhenWeightLowerThanMinusFive() throws Exception {
		new Solution(Collections.singletonList(-6.0));
	}

	@Test
	public void getWeights() throws Exception {
		assertEquals(weights, item.getWeights());
	}

	@Test
	public void iterator() throws Exception {
		final Iterator<Double> iterator = item.iterator();
		assertTrue(iterator.hasNext());
		assertEquals(new Double(1.0), iterator.next());
		assertEquals(new Double(2.0), iterator.next());
		assertFalse(iterator.hasNext());
	}

	@Test
	public void forEach() throws Exception {
		final Iterator<Double> iterator = weights.iterator();
		item.forEach(weight -> assertEquals(iterator.next(), weight));
	}

	@Test
	public void spliterator() throws Exception {
		assertTrue(item.spliterator() instanceof Spliterator);
	}

	@Test
	public void toStringTest() throws Exception {
		assertEquals(weights.toString(), item.toString());
	}

	@Test
	public void equals() throws Exception {
		assertEquals(new Solution(weights), item);
		assertFalse(item.equals(null));
		assertTrue(item.equals(item));
	}

	@Test
	public void hashCodeTest() throws Exception {
		assertEquals(weights.hashCode(), item.hashCode());
	}

}
