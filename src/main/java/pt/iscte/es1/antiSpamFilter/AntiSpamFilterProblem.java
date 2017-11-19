package pt.iscte.es1.antiSpamFilter;

import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

import pt.iscte.es1.antiSpamFilter.domain.Message;
import pt.iscte.es1.antiSpamFilter.domain.WeightedRule;


/**
 * The AntiSpamFilterProblem class is an extension of the JMetal
 * AbstractDoubleProblem class and is used to evaluate a given set of anti-spam
 * rules for given lists of spam and ham messages.
 */
@SuppressWarnings("serial")
public class AntiSpamFilterProblem extends AbstractDoubleProblem {

	private List<Message> spam;
	private List<Message> ham;
	private List<WeightedRule> rules;
	private final static double THRESHOLD = 5.0;
	public final static int INDEX_FALSE_NEGATIVE = 0;
	public final static int INDEX_FALSE_POSITIVE = 1;

	
	/**
	 * Default Constructor that accepts a list of Rules, a list of HAM messages,
	 * a list of SPAM messages and sets the size of the rules list;
	 * 
	 * @param rules
	 *            WeightedRule list that holds all Rules;
	 * @param ham
	 *            Message List that holds all HAM Messages;
	 * @param spam
	 *            Message List that holds all SPAM Messages;
	 */
	public AntiSpamFilterProblem(List<WeightedRule> rules, List<Message> ham, List<Message> spam) {

		this(rules.size());
		this.ham = ham;
		this.spam = spam;
		this.rules = rules;

	}

	/**
	 * Constructor that accepts an Integer NumberOfVariables and sets the lower
	 * and upper bounds for each variable on the LowerLimit and UpperLimit
	 * Lists;
	 * 
	 * @param numberOfVariables
	 *            Integer value that defines the number of variables;
	 */
	public AntiSpamFilterProblem(Integer numberOfVariables) {
		setNumberOfVariables(numberOfVariables);
		setNumberOfObjectives(2);
		setName("AntiSpamFilterProblem");

		List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables());
		List<Double> upperLimit = new ArrayList<>(getNumberOfVariables());

		for (int i = 0; i < getNumberOfVariables(); i++) {
			lowerLimit.add(-5.0);
			upperLimit.add(5.0);
		}

		setLowerLimit(lowerLimit);
		setUpperLimit(upperLimit);
	}


	/**
	 * Method used to evaluate a given solution for the current Anti-SPAM
	 * problem.
	 * 
	 * It accepts a DoubleSolution interface that extends the JMETAL Solution
	 * class and sets the counters for FalsePositives and FalseNegatives to
	 * zero.
	 * 
	 * Then for each message on the spam and ham lists, its analyzed if the
	 * current message has the same words as the Rules list and if so, the
	 * double aux variable is incremented with the weight of that rule.
	 * 
	 * Finally, the total weight on the double aux variable is compared with the
	 * Threshold and the correspondent FalseNegative or FalsePositive counter is
	 * incremented;
	 * 
	 * 
	 * @param solution
	 *            DoubleSolution interface that extends JMetals class Solution;
	 * @see org.uma.jmetal.problem.Problem#evaluate(java.lang.Object)
	 */
	@Override
	public void evaluate(DoubleSolution solution) {
		int falseNegative = 0;
		int falsePositive = 0;

		for (Message message: spam ) {
			double aux = 0;
			for (int index = 0; index != rules.size(); index++) {
				if(message.matchesRule(rules.get(index))) {
					aux += solution.getVariableValue(index);
				}
			}

			if(aux < THRESHOLD) {
				falseNegative++;
			}
		}

		for (Message message: ham) {
			double aux = 0;
			for (int index = 0; index != rules.size(); index++) {
				if(message.matchesRule(rules.get(index))) {
					aux += solution.getVariableValue(index);
				}
			}

			if(aux > THRESHOLD) {
				falsePositive++;
			}
		}
		
		solution.setObjective(INDEX_FALSE_NEGATIVE, falseNegative);
		solution.setObjective(INDEX_FALSE_POSITIVE, falsePositive);
	}
}
