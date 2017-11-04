package pt.iscte.es1.antiSpamFilter.infrastructure;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pt.iscte.es1.antiSpamFilter.domain.Rule;

public class RuleParserTest {

	private final ArrayList<String> ruleLines = new ArrayList<>();
	
	@Before
	public void setUp() throws Exception {
		ruleLines.add("BAYES_00");
		ruleLines.add("FREEMAIL_FROM");
		ruleLines.add("RDNS_NONE");
		ruleLines.add("FREEMAIL_REPLYTO_END_DIGIT");
		ruleLines.add("MSOE_MID_WRONG_CASE");
	}

	@Test
	public void shouldParseCorrectLines() {
		RuleParser rp = new RuleParser();
		ruleLines.forEach(ruleLine -> {
			rp.parse(ruleLine);
		});
		List<Rule> result = rp.getResult();
		assertEquals(5, result.size());
		assertEquals(new Rule("BAYES_00"), result.get(0));
		assertEquals(new Rule("FREEMAIL_FROM"), result.get(1));
		assertEquals(new Rule("RDNS_NONE"), result.get(2));
		assertEquals(new Rule("FREEMAIL_REPLYTO_END_DIGIT"), result.get(3));
		assertEquals(new Rule("MSOE_MID_WRONG_CASE"), result.get(4));
	}

}
