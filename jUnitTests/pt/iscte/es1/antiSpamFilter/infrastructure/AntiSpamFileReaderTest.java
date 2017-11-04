package pt.iscte.es1.antiSpamFilter.infrastructure;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pt.iscte.es1.antiSpamFilter.domain.Rule;

public class AntiSpamFileReaderTest {

	private static final int TOTAL_RULES = 335;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		AntiSpamFileReader<List<Rule>> fr = new AntiSpamFileReader<>(new RuleParser());
		List<Rule> result = fr.readFile(getClass().getClassLoader().getResource("rules.cf").getFile());
		assertEquals(TOTAL_RULES, result.size());
	}

}
