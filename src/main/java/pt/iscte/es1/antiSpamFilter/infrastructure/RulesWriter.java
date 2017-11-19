package pt.iscte.es1.antiSpamFilter.infrastructure;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

import pt.iscte.es1.antiSpamFilter.domain.WeightedRule;

/**
 * Writes rules and its weights to a file 
 */
public class RulesWriter {

	private final Writer writer;

	/**
	 * RulesWriter constructor
	 */
	public RulesWriter(Writer writer) {
		this.writer = writer;
	}

	/**
	 * Writes to {@link Writer} support
	 * @param writer
	 * @throws IOException
	 */
	public void write(List<WeightedRule> weightedRules) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(writer)) {
			for (WeightedRule weightedRule : weightedRules) {
				bw.write(weightedRule.getName() + "\t" + weightedRule.getWeight() + "\n");
			}
		}
	}
}
