package pt.iscte.es1.antiSpamFilter.infrastructure;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import pt.iscte.es1.antiSpamFilter.domain.WeightedRule;

public class RulesWriter {

        private final List<WeightedRule> weightedRules;

        public RulesWriter(List<WeightedRule> weightedRules) {
                this.weightedRules = weightedRules;
        }

        public void write(String file) throws IOException {
                try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
                        for (WeightedRule weightedRule : weightedRules) {
                                bw.write(weightedRule.getName() + "\t" + weightedRule.getWeight() + "\n");
                        }
                }
        }
}
