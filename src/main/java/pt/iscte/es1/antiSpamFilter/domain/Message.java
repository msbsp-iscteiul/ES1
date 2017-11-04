package pt.iscte.es1.antiSpamFilter.domain;

import java.util.HashSet;

public class Message {
	private final HashSet<WeightedRule> rules;

	public Message(HashSet<WeightedRule> rules) {
		this.rules = rules;
	}
	
	public boolean matchesRule(WeightedRule rule) {
		return rules.contains(rule);
	}
}
