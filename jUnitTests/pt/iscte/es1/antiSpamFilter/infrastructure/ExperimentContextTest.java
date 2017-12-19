package pt.iscte.es1.antiSpamFilter.infrastructure;

import org.junit.Test;
import pt.iscte.es1.antiSpamFilter.domain.Message;
import pt.iscte.es1.antiSpamFilter.domain.WeightedRule;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.*;

/**
 * Tests the {@link ExperimentContext}
 */
public class ExperimentContextTest {

	private final ExperimentContext item = new ExperimentContext(
		Collections.singletonList(new Message(new HashSet<>())),
		Collections.singletonList(new Message(new HashSet<>())),
		Collections.singletonList(new WeightedRule("lorem ipsum")),
		new File("lorem ipsum")
	);

	/**
	 * Tests the getHam getter
	 */
	@Test
	public void getHam() {
		assertEquals(1, item.getHam().size());
	}

	/**
	 * Tests the getSpam getter
	 */
	@Test
	public void getSpam() {
		assertEquals(1, item.getSpam().size());
	}

	/**
	 * Tests the getWeightedRules getter
	 */
	@Test
	public void getWeightedRules() {
		assertEquals(1, item.getWeightedRules().size());
	}

	/**
	 * Tests the getRulesPath getter
	 */
	@Test
	public void getRulesPath() {
		assertEquals(new File("lorem ipsum"), item.getRulesPath());
	}
}
