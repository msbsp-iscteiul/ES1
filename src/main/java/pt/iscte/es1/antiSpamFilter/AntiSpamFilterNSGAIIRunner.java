package pt.iscte.es1.antiSpamFilter;

import org.uma.jmetal.algorithm.Algorithm;
import org.uma.jmetal.algorithm.multiobjective.nsgaii.NSGAIIBuilder;
import org.uma.jmetal.operator.impl.crossover.SBXCrossover;
import org.uma.jmetal.operator.impl.mutation.PolynomialMutation;
import org.uma.jmetal.problem.DoubleProblem;
import org.uma.jmetal.qualityindicator.impl.hypervolume.PISAHypervolume;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.experiment.Experiment;
import org.uma.jmetal.util.experiment.ExperimentBuilder;
import org.uma.jmetal.util.experiment.component.*;
import org.uma.jmetal.util.experiment.util.ExperimentAlgorithm;
import org.uma.jmetal.util.experiment.util.ExperimentProblem;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * NSGAII algorithm for the {@link AntiSpamFilterProblem}
 */
public class AntiSpamFilterNSGAIIRunner {
	private final DoubleProblem problem;

	/**
	 * Generic Constructor
	 *
	 * @param problem AntiSpamFilterProblem
	 */
	public AntiSpamFilterNSGAIIRunner(AntiSpamFilterProblem problem) {
		this.problem = problem;
	}

	/**
	 * Generate the solutions for the {@link AntiSpamFilterProblem}
	 *
	 * @throws IOException File Not Found Exception
	 */
	public void generateNSGA() throws IOException {
		ExperimentProblem<DoubleSolution> experimentProblem = new ExperimentProblem<>(problem);
		Algorithm<List<DoubleSolution>> algorithm = new NSGAIIBuilder<>(
			problem,
			new SBXCrossover(1.0, 5),
			new PolynomialMutation(1.0 / problem.getNumberOfVariables(), 10.0))
			.setMaxEvaluations(100).setPopulationSize(100).build();
		List<ExperimentAlgorithm<DoubleSolution, List<DoubleSolution>>> algorithms = new ArrayList<>(
			Collections.singletonList(new ExperimentAlgorithm<>(algorithm, "NSGAII", experimentProblem.getTag())));
		List<ExperimentProblem<DoubleSolution>> problemList = new ArrayList<>(
			Collections.singletonList(experimentProblem));
		Experiment<DoubleSolution, List<DoubleSolution>> experiment =
			new ExperimentBuilder<DoubleSolution, List<DoubleSolution>>("AntiSpamStudy")
				.setAlgorithmList(algorithms)
				.setProblemList(problemList)
				.setExperimentBaseDirectory(AntiSpamFilterConstants.EXPERIMENT_BASE_DIRECTORY)
				.setOutputParetoFrontFileName("FUN")
				.setOutputParetoSetFileName("VAR")
				.setReferenceFrontDirectory(AntiSpamFilterConstants.REFERENCE_FRONT_DIRECTORY)
				.setIndicatorList(Collections.singletonList(new PISAHypervolume<>()))
				.setIndependentRuns(AntiSpamFilterConstants.INDEPENDENT_RUNS)
				.setNumberOfCores(8)
				.build();
		new ExecuteAlgorithms<>(experiment).run();
		new GenerateReferenceParetoSetAndFrontFromDoubleSolutions(experiment).run();
		new ComputeQualityIndicators<>(experiment).run();
		new GenerateLatexTablesWithStatistics(experiment).run();
		new GenerateBoxplotsWithR<>(experiment).setRows(1).setColumns(1).run();
	}
}
