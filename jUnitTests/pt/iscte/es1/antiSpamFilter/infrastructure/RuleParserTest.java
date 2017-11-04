package pt.iscte.es1.antiSpamFilter.infrastructure;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pt.iscte.es1.antiSpamFilter.domain.WeightedRule;

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
		weightedRuleLines.forEach(ruleLine -> {
			rp.parse(ruleLine);
		});
		List<WeightedRule> result = rp.getResult();
		assertEquals(5, result.size());
		assertEquals(new WeightedRule("BAYES_00"), result.get(0));
		assertEquals(new WeightedRule("FREEMAIL_FROM"), result.get(1));
		assertEquals(new WeightedRule("RDNS_NONE"), result.get(2));
		assertEquals(new WeightedRule("FREEMAIL_REPLYTO_END_DIGIT"), result.get(3));
		assertEquals(new WeightedRule("MSOE_MID_WRONG_CASE"), result.get(4));
	}
	
	@Test
	public void shouldParseCorrectLines() {
		RuleParser rp = new RuleParser();
		ruleLines.forEach(ruleLine -> {
			rp.parse(ruleLine);
		});
		List<WeightedRule> result = rp.getResult();
		assertEquals(5, result.size());
		assertEquals(new WeightedRule("BAYES_00"), result.get(0));
		assertEquals(new WeightedRule("FREEMAIL_FROM"), result.get(1));
		assertEquals(new WeightedRule("RDNS_NONE"), result.get(2));
		assertEquals(new WeightedRule("FREEMAIL_REPLYTO_END_DIGIT"), result.get(3));
		assertEquals(new WeightedRule("MSOE_MID_WRONG_CASE"), result.get(4));
	}

}
