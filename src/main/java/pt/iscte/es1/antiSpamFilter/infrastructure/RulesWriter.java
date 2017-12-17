package pt.iscte.es1.antiSpamFilter.infrastructure;

import pt.iscte.es1.antiSpamFilter.domain.WeightedRule;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.List;

/**
 * Writes rules and its weights to a file
 */
public class RulesWriter {

	private final Writer writer;

	/**
	 * RulesWriter constructor
	 *
	 * @param writer writer to use
	 */
	public RulesWriter(Writer writer) {
		this.writer = writer;
	}

	/**
	 * Writes rules and weights to a {@link Writer}.
	 *
	 * @param weightedRules rules and weights being written
	 * @throws IOException when writing or closing the writer
	 */
	public void write(List<WeightedRule> weightedRules) throws IOException {
		final BufferedWriter bw = new BufferedWriter(writer);
		try {
			for (WeightedRule weightedRule : weightedRules) {
				bw.write(weightedRule.getName() + "\t" + weightedRule.getWeight() + "\n");
			}
		} finally {
			bw.close();
		}
	}
}
