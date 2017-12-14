package pt.iscte.es1.antiSpamFilter;

import org.junit.Before;
import org.junit.Test;
import pt.iscte.es1.antiSpamFilter.domain.Message;
import pt.iscte.es1.antiSpamFilter.domain.WeightedRule;
import pt.iscte.es1.antiSpamFilter.infrastructure.AntiSpamFileReader;
import pt.iscte.es1.antiSpamFilter.infrastructure.LogParser;
import pt.iscte.es1.antiSpamFilter.infrastructure.RuleParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class AntiSpamFilterNSGAIIRunnerTest {

	@Before
	public void setUp() throws Exception {
		new File(AntiSpamFilterConstants.REFERENCE_FRONT_DIRECTORY + "/AntiSpamFilterProblem.rf").delete();
	}

	@Test
	public void generateNSGA() throws IOException {
		final AntiSpamFileReader<WeightedRule> weightedRuleAntiSpamFileReader = new AntiSpamFileReader<>(new RuleParser());
		final AntiSpamFileReader<Message> messageAntiSpamFileReader = new AntiSpamFileReader<>(new LogParser());
		final List<Message> ham = messageAntiSpamFileReader.readFile(new FileReader(getClass().getResource("/ham.log").getPath()));
		final List<Message> spam = messageAntiSpamFileReader.readFile(new FileReader(getClass().getResource("/spam.log").getPath()));
		final List<WeightedRule> rules = weightedRuleAntiSpamFileReader.readFile(new FileReader(getClass().getResource("/rules.cf").getPath()));

		assertFalse(new File(AntiSpamFilterConstants.REFERENCE_FRONT_DIRECTORY + "/AntiSpamFilterProblem.rf").exists());
		new AntiSpamFilterNSGAIIRunner(new AntiSpamFilterProblem(rules, ham, spam)).generateNSGA();
		assertTrue(new File(AntiSpamFilterConstants.REFERENCE_FRONT_DIRECTORY + "/AntiSpamFilterProblem.rf").exists());
	}
}
