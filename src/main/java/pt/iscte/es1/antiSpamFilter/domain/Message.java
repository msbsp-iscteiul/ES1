package pt.iscte.es1.antiSpamFilter.domain;

import java.util.HashSet;

public class Message {
	private final HashSet<Rule> rules;

	public Message(HashSet<Rule> rules) {
		this.rules = rules;
	}
	
	public boolean matchesRule(Rule rule) {
		return rules.contains(rule);
	}
}
