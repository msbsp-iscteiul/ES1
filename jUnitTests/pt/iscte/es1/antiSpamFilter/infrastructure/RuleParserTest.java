package pt.iscte.es1.antiSpamFilter.infrastructure;

import org.junit.Before;
import org.junit.Test;
import pt.iscte.es1.antiSpamFilter.domain.WeightedRule;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class RuleParserTest {

	private final ArrayList<String> ruleLines = new ArrayList<>();
	private final ArrayList<String> weightedRuleLines = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		ruleLines.add("BAYES_00");
		ruleLines.add("FREEMAIL_FROM");
		ruleLines.add("RDNS_NONE");
		ruleLines.add("FREEMAIL_REPLYTO_END_DIGIT");
		ruleLines.add("MSOE_MID_WRONG_CASE");

		weightedRuleLines.add("BAYES_00	1.0");
		weightedRuleLines.add("FREEMAIL_FROM	2.0");
		weightedRuleLines.add("RDNS_NONE	3.0");
		weightedRuleLines.add("FREEMAIL_REPLYTO_END_DIGIT	4.0");
		weightedRuleLines.add("MSOE_MID_WRONG_CASE	5.0");
	}

	@Test
	public void shouldParseCorrectWeightedLines() {
		RuleParser rp = new RuleParser();
		assertEquals(new WeightedRule("BAYES_00", 1.0).getWeight(), rp.parse(weightedRuleLines.get(0)).getWeight());
		assertEquals(new WeightedRule("FREEMAIL_FROM", 2.0).getWeight(), rp.parse(weightedRuleLines.get(1)).getWeight());
		assertEquals(new WeightedRule("RDNS_NONE", 3.0).getWeight(), rp.parse(weightedRuleLines.get(2)).getWeight());
		assertEquals(new WeightedRule("FREEMAIL_REPLYTO_END_DIGIT", 4.0).getWeight(), rp.parse(weightedRuleLines.get(3)).getWeight());
		assertEquals(new WeightedRule("MSOE_MID_WRONG_CASE", 5.0).getWeight(), rp.parse(weightedRuleLines.get(4)).getWeight());
	}

	@Test
	public void shouldParseCorrectLines() {
		RuleParser rp = new RuleParser();
		assertEquals(new WeightedRule("BAYES_00"), rp.parse(ruleLines.get(0)));
		assertEquals(new WeightedRule("FREEMAIL_FROM"), rp.parse(ruleLines.get(1)));
		assertEquals(new WeightedRule("RDNS_NONE"), rp.parse(ruleLines.get(2)));
		assertEquals(new WeightedRule("FREEMAIL_REPLYTO_END_DIGIT"), rp.parse(ruleLines.get(3)));
		assertEquals(new WeightedRule("MSOE_MID_WRONG_CASE"), rp.parse(ruleLines.get(4)));
	}

}
