package pt.iscte.es1.antiSpamFilter.domain;

import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

public class PositiveNegativeSetTest {

	private final PositiveNegativeSet item = new PositiveNegativeSet(1.0, 2.0);

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowOnNegativeFalsePositives() throws Exception {
		new PositiveNegativeSet(-1.0, 0.0);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowOnNegativeFalseNegatives() throws Exception {
		new PositiveNegativeSet(0.0, -1.0);
	}

	@Test
	public void getFalsePositives() throws Exception {
		assertEquals(new Double(1.0), item.getFalsePositives());
	}

	@Test
	public void getFalseNegatives() throws Exception {
		assertEquals(new Double(2.0), item.getFalseNegatives());
	}

	@Test
	public void hashCodeTest() throws Exception {
		assertEquals(Objects.hash(1.0, 2.0), item.hashCode());
	}

	@Test
	public void equals() throws Exception {
		assertTrue(item.equals(item));
		assertTrue(item.equals(new PositiveNegativeSet(1.0, 2.0)));
		assertFalse(item.equals(null));
		assertFalse(item.equals(new Double(1.0)));
	}

	@Test
	public void toStringTest() throws Exception {
		assertEquals("(1.0, 2.0)", item.toString());
	}

}
