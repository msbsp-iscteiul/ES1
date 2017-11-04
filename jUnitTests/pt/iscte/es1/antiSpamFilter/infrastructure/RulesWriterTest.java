package pt.iscte.es1.antiSpamFilter.infrastructure;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import pt.iscte.es1.antiSpamFilter.domain.WeightedRule;

public class RulesWriterTest {

        private static final String FILE_NAME = "d:\\d\\teste.cf";

        @Before
        public void setUp() throws Exception {
        }

        @Test
        public void shouldWriteFile() throws IOException {
                List<WeightedRule> weightedRules = new ArrayList<>();
                weightedRules.add(new WeightedRule("regra", 1.0));
                weightedRules.add(new WeightedRule("regra_2", 2.0));
                weightedRules.add(new WeightedRule("regra_3", 3.0));
                RulesWriter rw = new RulesWriter(weightedRules);
                rw.write(FILE_NAME);

                AntiSpamFileReader<List<WeightedRule>> reader = new AntiSpamFileReader<>(new RuleParser());
                List<WeightedRule> result = reader.readFile(FILE_NAME);

                assertEquals(new WeightedRule("regra"), result.get(0));
                assertEquals(new WeightedRule("regra_2"), result.get(1));
                assertEquals(new WeightedRule("regra_3"), result.get(2));
        }

}
