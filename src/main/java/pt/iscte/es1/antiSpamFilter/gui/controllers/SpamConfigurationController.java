package pt.iscte.es1.antiSpamFilter.gui.controllers;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.solution.impl.DefaultDoubleSolution;
import pt.iscte.es1.antiSpamFilter.AntiSpamFilterProblem;
import pt.iscte.es1.antiSpamFilter.domain.ExperimentContext;
import pt.iscte.es1.antiSpamFilter.domain.WeightedRule;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Handles the spam configuration scene
 */
public class SpamConfigurationController implements Initializable {

	@FXML
	public Label falsePositivesQuantity;
	@FXML
	public Label falseNegativesQuantity;
	@FXML
	private TableColumn<WeightedRule, String> rulesColumn;
	@FXML
	private TableColumn<WeightedRule, String> weightsColumn;
	@FXML
	private TableView<WeightedRule> tableView;

	private Scene parentScene;
	private ExperimentContext context;
	private AntiSpamFilterProblem problem;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rulesColumn.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getName()));
		weightsColumn.setOnEditCommit(e -> {
			try {
				final Double weight = Double.valueOf(e.getNewValue());
				e.getRowValue().setWeight(weight);
			} catch (IllegalArgumentException ignore) {
			}
		});
		weightsColumn.setCellValueFactory(
			cell -> new ReadOnlyStringWrapper(String.valueOf(cell.getValue().getWeight())));
		weightsColumn.setCellFactory(TextFieldTableCell.forTableColumn());
	}

	/**
	 * Hidrates this controller with necessary data
	 * @param parentScene reference to the file selector stage
	 * @param context context with spam, ham and rules
	 */
	void initData(Scene parentScene, ExperimentContext context) {
		this.parentScene = parentScene;
		this.context = context;
		this.problem = new AntiSpamFilterProblem(context.getWeightedRules(), context.getHam(), context.getSpam());
		tableView.getItems().addAll(context.getWeightedRules());
	}

	/**
	 * Returns to the file selector stage
	 * @param actionEvent fired event
	 */
	public void cancel(ActionEvent actionEvent) {
		Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
		window.setScene(parentScene);
	}

	/**
	 * Generates weights with a normal distribution
	 */
	public void handleRandom() {
		context.getWeightedRules().forEach(weightedRule -> {
			final double randomWeight = ThreadLocalRandom.current().nextDouble(
				-AntiSpamFilterProblem.THRESHOLD, AntiSpamFilterProblem.THRESHOLD);
			weightedRule.setWeight(randomWeight);
		});
		tableView.refresh();
		handleEvaluation();
	}

	/**
	 * Generates weights with NSGA-II algorithm
	 */
	public void handleNSGAII(ActionEvent actionEvent) {
		// TODO
		handleEvaluation();
	}

	/**
	 * Evaluates the current weights
	 */
	public void handleEvaluation() {
		final DoubleSolution solution = createSolutionFromWeightedRules();
		problem.evaluate(solution);
		falsePositivesQuantity.setText(String.valueOf(
			solution.getObjective(AntiSpamFilterProblem.INDEX_FALSE_POSITIVE)));
		falseNegativesQuantity.setText(String.valueOf(
			solution.getObjective(AntiSpamFilterProblem.INDEX_FALSE_NEGATIVE)));
	}

	private DoubleSolution createSolutionFromWeightedRules() {
		final DoubleSolution solution = new DefaultDoubleSolution(problem);
		for (int i = 0; i < context.getWeightedRules().size(); ++i) {
			solution.setVariableValue(i, context.getWeightedRules().get(i).getWeight());
		}
		return solution;
	}
}
