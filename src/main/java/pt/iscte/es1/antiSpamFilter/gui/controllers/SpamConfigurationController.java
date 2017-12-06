package pt.iscte.es1.antiSpamFilter.gui.controllers;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.solution.impl.DefaultDoubleSolution;
import pt.iscte.es1.antiSpamFilter.AntiSpamFilterConstants;
import pt.iscte.es1.antiSpamFilter.AntiSpamFilterProblem;
import pt.iscte.es1.antiSpamFilter.infrastructure.ExperimentContext;
import pt.iscte.es1.antiSpamFilter.domain.WeightedRule;
import pt.iscte.es1.antiSpamFilter.gui.AlertMessage;
import pt.iscte.es1.antiSpamFilter.infrastructure.NsgaRToEpsCompiler;
import pt.iscte.es1.antiSpamFilter.infrastructure.NsgaTexToPdfCompiler;
import pt.iscte.es1.antiSpamFilter.infrastructure.RulesWriter;

import java.io.IOException;
import java.io.FileWriter;
import pt.iscte.es1.antiSpamFilter.infrastructure.RulesWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
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
	 *
	 * @param parentScene reference to the file selector stage
	 * @param context     context with spam, ham and rules
	 */
	void initData(Scene parentScene, ExperimentContext context) {
		this.parentScene = parentScene;
		this.context = context;
		this.problem = new AntiSpamFilterProblem(context.getWeightedRules(), context.getHam(), context.getSpam());
		tableView.getItems().addAll(context.getWeightedRules());
		handleEvaluation();
	}

	/**
	 * Returns to the file selector stage
	 *
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
				-AntiSpamFilterConstants.THRESHOLD, AntiSpamFilterConstants.THRESHOLD);
			weightedRule.setWeight(randomWeight);
		});
		tableView.refresh();
		handleEvaluation();
	}

	/**
	 * Generates weights with NSGA-II algorithm
	 */
	public void handleNSGAII(ActionEvent actionEvent) {
		// Choose profile for User
		chooseProfile();

		// TODO
		handleEvaluation();

		// After calculation its necessary to compile the R file and present it to the user
		new NsgaRToEpsCompiler().compileAndShow();

		// After calculation its necessary to compile Tex file and present it to user
		new NsgaTexToPdfCompiler().compileAndShow();
	}

	/**
	 * Evaluates the current weights
	 */
	public void handleEvaluation() {
		final DoubleSolution solution = createSolutionFromWeightedRules();
		problem.evaluate(solution);
		falsePositivesQuantity.setText(String.valueOf(
			solution.getObjective(AntiSpamFilterConstants.INDEX_FALSE_POSITIVE)));
		falseNegativesQuantity.setText(String.valueOf(
			solution.getObjective(AntiSpamFilterConstants.INDEX_FALSE_NEGATIVE)));
	}

	/**
	 * Generate a solution from the weigthed rules
	 *
	 * @return a solution that contains a weight for each rule
	 */
	private DoubleSolution createSolutionFromWeightedRules() {
		final DoubleSolution solution = new DefaultDoubleSolution(problem);
		for (int i = 0; i < context.getWeightedRules().size(); ++i) {
			solution.setVariableValue(i, context.getWeightedRules().get(i).getWeight());
		}
		return solution;
	}

	/**
	 * Save the current configuration on rules.cf
	 *
	 * @throws IOException if not able to save the configuration
	 */
	public void save() throws IOException {
		final Optional<ButtonType> result = new AlertMessage(Alert.AlertType.CONFIRMATION,"Confirm Save",
			"Are you sure you want to save this configuration??").showAndWait();

		if (!result.isPresent() || result.get() != ButtonType.OK) {
			return;
		}

		FileWriter fw = new FileWriter(context.getRulesPath(), false);
		RulesWriter rw = new RulesWriter(fw);
		rw.write(context.getWeightedRules());

		new AlertMessage(Alert.AlertType.INFORMATION,
			"Result Save", "Configuration saved with success!!").showAndWait();
	}

	/**
	 * Allow user to select the profile to be used
	 *
	 * @return the selected profile
	 */
	private String chooseProfile() {

		List<String> choices = Arrays.asList("Leisure", "Professional", "Intermediate");

		ChoiceDialog<String> profileSelector = new ChoiceDialog<>("Leisure", choices);
		profileSelector.setTitle("Profile");
		profileSelector.setHeaderText("Choose profile");

		Optional<String> result = profileSelector.showAndWait();

		return result.orElse(null);
	}
}
