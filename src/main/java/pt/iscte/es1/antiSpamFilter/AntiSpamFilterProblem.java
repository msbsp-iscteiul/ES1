package pt.iscte.es1.antiSpamFilter;

import java.util.ArrayList;
import java.util.List;

import org.uma.jmetal.problem.impl.AbstractDoubleProblem;
import org.uma.jmetal.solution.DoubleSolution;

import pt.iscte.es1.antiSpamFilter.domain.Message;
import pt.iscte.es1.antiSpamFilter.domain.WeightedRule;

/**
 * 
 *
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
	 * @param rules
	 * @param ham
	 * @param spam
	 */
	public AntiSpamFilterProblem(List<WeightedRule> rules, List<Message> ham, List<Message> spam) {

		this(rules.size());
		this.ham = ham;
		this.spam = spam;
		this.rules = rules;

	}

	/**
	 * @param numberOfVariables
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

	/* (non-Javadoc)
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
