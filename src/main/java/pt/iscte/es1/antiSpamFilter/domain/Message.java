package pt.iscte.es1.antiSpamFilter.domain;

import java.util.HashSet;

/**
 * A Message represents a Ham or Spam log.
 * A Message can have multiple matching rules.
 */
public class Message {

	private final HashSet<WeightedRule> rules;

	/**
	 * @param rules that match this message
	 */
	public Message(HashSet<WeightedRule> rules) {
		this.rules = rules;
	}

	/**
	 * Finds if given {@link WeightedRule} matches this {@link Message}
	 *
	 * @param rule rule to match
	 * @return true if rule is contained within the message, false otherwise
	 */
	public boolean matchesRule(WeightedRule rule) {
		return rules.contains(rule);
	}
}
