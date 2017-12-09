package pt.iscte.es1.antiSpamFilter.infrastructure;

import pt.iscte.es1.antiSpamFilter.domain.Message;
import pt.iscte.es1.antiSpamFilter.domain.WeightedRule;

import java.io.File;
import java.util.Collections;
import java.util.List;

/**
 * Context of AntiSpam configuration which includes needed values
 * for running NSGA-II and saving the rules file
 */
public class ExperimentContext {
	private final List<Message> ham;
	private final List<Message> spam;
	private final List<WeightedRule> weightedRules;
	private final File rulesPath;

	/**
	 * {@link ExperimentContext} constructor
	 * @param ham ham messages
	 * @param spam spam messages
	 * @param weightedRules rules and weights to populate the table
	 * @param rulesPath rules file used for saving
	 */
	public ExperimentContext(List<Message> ham, List<Message> spam, List<WeightedRule> weightedRules, File rulesPath) {
		this.ham = ham;
		this.spam = spam;
		this.weightedRules = weightedRules;
		this.rulesPath = rulesPath;
	}

	/**
	 * Get the ham messages
	 * @return ham messages
	 */
	public List<Message> getHam() {
		return Collections.unmodifiableList(ham);
	}

	/**
	 * Get the spam messages
	 * @return spam messages
	 */
	public List<Message> getSpam() {
		return Collections.unmodifiableList(spam);
	}

	/**
	 * Get the weights and rules
	 * @return weights and rules
	 */
	public List<WeightedRule> getWeightedRules() {
		return Collections.unmodifiableList(weightedRules);
	}

	/**
	 * Get the rules file
	 * @return rules file for saving
	 */
	public File getRulesPath() {
		return rulesPath;
	}
}
