package pt.iscte.es1.antiSpamFilter.gui.controllers;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.solution.impl.DefaultDoubleSolution;
import pt.iscte.es1.antiSpamFilter.AntiSpamFilterConstants;
import pt.iscte.es1.antiSpamFilter.AntiSpamFilterNSGAIIRunner;
import pt.iscte.es1.antiSpamFilter.AntiSpamFilterProblem;
import pt.iscte.es1.antiSpamFilter.domain.PositiveNegativeSet;
import pt.iscte.es1.antiSpamFilter.domain.Solution;
import pt.iscte.es1.antiSpamFilter.domain.WeightedRule;
import pt.iscte.es1.antiSpamFilter.gui.AlertMessage;
import pt.iscte.es1.antiSpamFilter.infrastructure.*;
import pt.iscte.es1.antiSpamFilter.infrastructure.result_compilers.NsgaRToEpsCompiler;
import pt.iscte.es1.antiSpamFilter.infrastructure.result_compilers.NsgaTexToPdfCompiler;

import java.awt.Desktop;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Handles the spam configuration scene
 */
public class SpamConfigurationController implements Initializable {

	@FXML
	private Label falsePositivesQuantity;
	@FXML
	private Label falseNegativesQuantity;
	@FXML
	private TableColumn<WeightedRule, String> rulesColumn;
	@FXML
	private TableColumn<WeightedRule, String> weightsColumn;
	@FXML
	private TableView<WeightedRule> tableView;

	private Scene parentScene;
	private ExperimentContext context;
	private AntiSpamFilterProblem problem;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		rulesColumn.setCellValueFactory(cell -> new ReadOnlyStringWrapper(cell.getValue().getName()));
		weightsColumn.setOnEditCommit(cell -> {
			try {
				final Double weight = Double.valueOf(cell.getNewValue());
				cell.getRowValue().setWeight(weight);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
				new AlertMessage(Alert.AlertType.ERROR, "Error",
					e.getMessage()).showAndWait();
				tableView.refresh();
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
	 * Handles the NSGAII algorithm generated weight vector accordingly to the chosen inbox profile
	 *
	 * @throws IOException File Not Found Exception
	 */
	public void handleNSGAII() throws IOException {
		// Choose Profile for User
		final String profile = chooseProfile();
		if (profile == null) {
			return;
		}

		final Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Information");
		alert.setHeaderText(null);
		alert.setContentText("Generating values. Please wait");
		alert.getDialogPane().lookupButton(ButtonType.OK).setVisible(false);
		alert.show();

		final AntiSpamFilterNSGAIIRunner config = new AntiSpamFilterNSGAIIRunner(problem);
		config.generateNSGA();
		final AntiSpamFileReader<PositiveNegativeSet> reader = new AntiSpamFileReader<>(new PositiveNegativeParser());
		final AntiSpamFileReader<Solution> readResultComposite = new AntiSpamFileReader<>(
			new ExperimentResultWeightsParser());

		final List<PositiveNegativeSet> positiveNegativeSets = reader.readFile(new FileReader(
			AntiSpamFilterConstants.REFERENCE_FRONT_DIRECTORY + "/AntiSpamFilterProblem.NSGAII.rf"));
		final List<Solution> resultWeightComposites = readResultComposite.readFile(new FileReader(
			AntiSpamFilterConstants.REFERENCE_FRONT_DIRECTORY + "/AntiSpamFilterProblem.NSGAII.rs"));
		final ResultSelector selector = ResultSelector.createForProfile(profile);
		final Solution doubles = selector.selectFromResults(positiveNegativeSets, resultWeightComposites);
		final Iterator<Double> iterator = doubles.iterator();
		context.getWeightedRules().forEach(weightedRule -> weightedRule.setWeight(iterator.next()));
		alert.hide();
		tableView.refresh();
		handleEvaluation();
		buildREps();
		buildLatexPdf();
	}

	/**
	 * After calculation its necessary to compile Tex to PDF file and present it to the User
	 */
	private void buildLatexPdf() {
		try {
			File pdfFile = new NsgaTexToPdfCompiler().compile();
			Desktop.getDesktop().open(pdfFile);
		} catch (IOException e) {
			new AlertMessage(
				Alert.AlertType.ERROR, "Error Executing Script",
				"There was a problem executing the script.\nPlease configure pdflatex to run the script.")
				.showAndWait();
		} catch (InterruptedException ignore) {
		}
	}

	/**
	 * After calculation its necessary to compile R to EPS file and present it to the User
	 */
	private void buildREps() {
		try {
			File epsFile = new NsgaRToEpsCompiler().compile();
			Desktop.getDesktop().open(epsFile);
		} catch (IOException e) {
			new AlertMessage(
				Alert.AlertType.ERROR, "Error Executing Script",
				"There was a problem executing the script.\nPlease configure Rscript to run the script.")
				.showAndWait();
		} catch (InterruptedException ignore) {
		}
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
		final Optional<ButtonType> result = new AlertMessage(Alert.AlertType.CONFIRMATION,
			"Confirm Save", "Are you sure you want to save this configuration?")
			.showAndWait();

		if (!result.isPresent() || result.get() != ButtonType.OK) {
			return;
		}

//		For the case the User selects the Path
//		FileWriter fw = new FileWriter(directoryChooser().getAbsolutePath() + "/rules.cf", false);
		FileWriter fw = new FileWriter(context.getRulesPath(), false);
		RulesWriter rw = new RulesWriter(fw);
		rw.write(context.getWeightedRules());

		new AlertMessage(Alert.AlertType.INFORMATION, "Result Save", "Configuration saved with success!")
			.showAndWait();
	}

	/**
	 * Allow user to select the profile to be used
	 *
	 * @return the selected profile
	 */
	private String chooseProfile() {
		List<String> choices = Arrays.asList(
			AntiSpamFilterConstants.STRATEGY_LEISURE,
			AntiSpamFilterConstants.STRATEGY_PROFESSIONAL,
			AntiSpamFilterConstants.STRATEGY_MIXED
		);

		ChoiceDialog<String> profileSelector = new ChoiceDialog<>("Leisure", choices);
		profileSelector.setTitle("Profile");
		profileSelector.setHeaderText("Choose profile");

		Optional<String> result = profileSelector.showAndWait();

		return result.orElse(null);
	}

	/**
	 * Directory Chooser for the user to save the rules.cf file
	 *
	 * @return File - Path chosen by the user
	 */
	private File directoryChooser() {
		DirectoryChooser directoryChooser = new DirectoryChooser();
		directoryChooser.setTitle("Directory Chooser");
		directoryChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		return directoryChooser.showDialog(this.parentScene.getWindow());
	}
}
