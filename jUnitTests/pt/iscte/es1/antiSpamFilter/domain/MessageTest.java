package pt.iscte.es1.antiSpamFilter.domain;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the {@link Message}
 */
public class MessageTest {

	/**
	 * Tests it is correctly initialized
	 */
	@Test
	public void itIsInitializable() {
		HashSet<WeightedRule> weightedRules = new HashSet<>();
		assertTrue(new Message(weightedRules).getClass().equals(Message.class));
	}

	/**
	 * Ensures it finds a matching rule
	 */
	@Test
	public void itFindsMatchingRule() {
		HashSet<WeightedRule> weightedRules = new HashSet<>();
		weightedRules.add(new WeightedRule("name"));
		Message m = new Message(weightedRules);
		assertTrue(m.matchesRule(new WeightedRule("name")));
	}

}
