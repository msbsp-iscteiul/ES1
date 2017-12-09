package pt.iscte.es1.antiSpamFilter.infrastructure;

import org.junit.Test;
import pt.iscte.es1.antiSpamFilter.domain.Message;
import pt.iscte.es1.antiSpamFilter.domain.WeightedRule;

import java.io.File;
import java.util.Collections;
import java.util.HashSet;

import static org.junit.Assert.*;

public class ExperimentContextTest {

	private final ExperimentContext item = new ExperimentContext(
		Collections.singletonList(new Message(new HashSet<>())),
		Collections.singletonList(new Message(new HashSet<>())),
		Collections.singletonList(new WeightedRule("lorem ipsum")),
		new File("lorem ipsum")
	);

	@Test
	public void getHam() throws Exception {
		assertEquals(1, item.getHam().size());
	}

	@Test
	public void getSpam() throws Exception {
		assertEquals(1, item.getSpam().size());
	}

	@Test
	public void getWeightedRules() throws Exception {
		assertEquals(1, item.getWeightedRules().size());
	}

	@Test
	public void getRulesPath() throws Exception {
		assertEquals(new File("lorem ipsum"), item.getRulesPath());
	}
}
