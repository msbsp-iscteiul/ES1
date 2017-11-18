package pt.iscte.es1.antiSpamFilter;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.junit.Test;
import org.uma.jmetal.solution.impl.DefaultDoubleSolution;

import pt.iscte.es1.antiSpamFilter.domain.Message;
import pt.iscte.es1.antiSpamFilter.domain.WeightedRule;

public class AntiSpamFilterProblemTest {
	
	private List<WeightedRule> rules = new ArrayList<>(Arrays.asList(new WeightedRule("coco"), new WeightedRule("ranheta"),new WeightedRule("facada")));
	private List<Message> spam = new ArrayList<>(Arrays.asList(new Message(new HashSet<>(Arrays.asList(new WeightedRule("coco"), new WeightedRule("ranheta"))))));
	private List<Message> ham = new ArrayList<>(Arrays.asList(new Message(new HashSet<>(Arrays.asList(new WeightedRule("facada"), new WeightedRule("ranheta"))))));
	
	private AntiSpamFilterProblem teste = new AntiSpamFilterProblem(rules, ham, spam);
	private DefaultDoubleSolution doubleSolution = new DefaultDoubleSolution(teste);
	

	@Test
	public void noFalseOrPositiveNegatives() {
		
		doubleSolution.setVariableValue(0, 4.0);
		doubleSolution.setVariableValue(1, 3.0);
		doubleSolution.setVariableValue(2, -3.0);
		
		teste.evaluate(doubleSolution);
		
		
		
		assertTrue(doubleSolution.getObjective(AntiSpamFilterProblem.INDEX_FALSE_NEGATIVE) == 0.0);
		assertTrue(doubleSolution.getObjective(AntiSpamFilterProblem.INDEX_FALSE_POSITIVE) == 0.0);
		
	}
	
	@Test
	public void oneFalseAndOnePositiveNegatives() {
		
		doubleSolution.setVariableValue(0, 2.0);
		doubleSolution.setVariableValue(1, 2.3);
		doubleSolution.setVariableValue(2, 4.0);
		
		teste.evaluate(doubleSolution);
		
		assertTrue(doubleSolution.getObjective(AntiSpamFilterProblem.INDEX_FALSE_NEGATIVE) == 1.0);
		assertTrue(doubleSolution.getObjective(AntiSpamFilterProblem.INDEX_FALSE_POSITIVE) == 1.0);
		
	}

}
