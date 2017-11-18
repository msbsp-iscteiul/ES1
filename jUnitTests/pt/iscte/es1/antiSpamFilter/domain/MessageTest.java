package pt.iscte.es1.antiSpamFilter.domain;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Before;
import org.junit.Test;

public class MessageTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void itIsInitializable() {
		HashSet<WeightedRule> weightedRules = new HashSet<>();
		assertTrue(new Message(weightedRules).getClass().equals(Message.class));
	}
	
	@Test
	public void itFindsMatchingRule() throws Exception {
		HashSet<WeightedRule> weightedRules = new HashSet<>();
		weightedRules.add(new WeightedRule("name"));
		Message m = new Message(weightedRules);
		assertTrue(m.matchesRule(new WeightedRule("name")));
	}

}
