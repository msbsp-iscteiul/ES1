package pt.iscte.es1.antiSpamFilter.domain;

import java.io.File;
import java.util.Collections;
import java.util.List;

public class ExperimentContext {
	private final List<Message> ham;
	private final List<Message> spam;
	private final List<WeightedRule> weightedRules;
	private final File rulesPath;

	public ExperimentContext(List<Message> ham, List<Message> spam, List<WeightedRule> weightedRules, File rulesPath) {
		this.ham = ham;
		this.spam = spam;
		this.weightedRules = weightedRules;
		this.rulesPath = rulesPath;
	}

	public List<Message> getHam() {
		return Collections.unmodifiableList(ham);
	}

	public List<Message> getSpam() {
		return Collections.unmodifiableList(spam);
	}

	public List<WeightedRule> getWeightedRules() {
		return Collections.unmodifiableList(weightedRules);
	}

	public File getRulesPath() {
		return rulesPath;
	}
}
