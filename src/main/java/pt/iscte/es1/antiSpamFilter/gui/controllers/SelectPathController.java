package pt.iscte.es1.antiSpamFilter.gui.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.iscte.es1.antiSpamFilter.domain.ExperimentContext;
import pt.iscte.es1.antiSpamFilter.domain.Message;
import pt.iscte.es1.antiSpamFilter.domain.WeightedRule;
import pt.iscte.es1.antiSpamFilter.infrastructure.AntiSpamFileReader;
import pt.iscte.es1.antiSpamFilter.infrastructure.LogParser;
import pt.iscte.es1.antiSpamFilter.infrastructure.RuleParser;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

/**
 * Handles the file chooser scene
 */
public class SelectPathController {

	@FXML
	private TextField rulesPath;

	@FXML
	private TextField spamPath;

	@FXML
	private TextField hamPath;

	private Stage getStage() {
		return (Stage) rulesPath.getScene().getWindow();
	}

	/**
	 * Creates an {@link ExperimentContext} with the results of the selected files
	 * @return the context
	 * @throws IOException
	 */
	private ExperimentContext createContext() throws IOException {
		List<WeightedRule> rules = new AntiSpamFileReader<>(new RuleParser()).readFile(new FileReader(rulesPath.getText()));
		List<Message> spam = new AntiSpamFileReader<>(new LogParser()).readFile(new FileReader(spamPath.getText()));
		List<Message> ham = new AntiSpamFileReader<>(new LogParser()).readFile(new FileReader(hamPath.getText()));
		return new ExperimentContext(ham, spam, rules);
	}

	/**
	 * Opens the spam configuration scene
	 * @param actionEvent the fired event
	 * @param context {@link ExperimentContext} to feed the spam configuration scene
	 * @throws IOException
	 */
	private void openSpamConfiguration(ActionEvent actionEvent, ExperimentContext context) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("template/spam_configuration.fxml"));
		Parent parent = loader.load();
		Scene spamConfigurationScene = new Scene(parent);

		SpamConfigurationController spamConfigurationController = loader.getController();
		spamConfigurationController.initData(((Node) actionEvent.getSource()).getScene(), context);

		Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
		window.setScene(spamConfigurationScene);
	}

	/**
	 * Handles the intent to configure the spam
	 * @param actionEvent the fired event
	 * @throws IOException
	 */
	@FXML
	protected void handleContinue(ActionEvent actionEvent) throws IOException {
		final ExperimentContext context = createContext();
		openSpamConfiguration(actionEvent, context);
	}

	/**
	 * Closes the application
	 */
	@FXML
	protected void handleClose() {
		Platform.exit();
	}

	/**
	 * Handles the rules file chooser
	 */
	@FXML
	protected void handleRulesPath() {
		File file = fileChooser(getStage(), "Rules", "Rules.cf", "*.cf");
		if (file != null) {
			rulesPath.setText(file.getAbsolutePath());
		}
	}

	/**
	 * Handles the spam file chooser
	 */
	@FXML
	protected void handleSpamPath() {
		File file = fileChooser(getStage(), "Spam", "Spam.log", "*.log");
		if (file != null) {
			spamPath.setText(file.getAbsolutePath());
		}
	}

	/**
	 * Handles the ham file chooser
	 */
	@FXML
	protected void handleHamPath() {
		File file = fileChooser(getStage(), "Ham", "Ham.log", "*.log");
		if (file != null) {
			hamPath.setText(file.getAbsolutePath());
		}
	}

	/**
	 * File Chooser which returns a File that the user selects.
	 * @param stage - Primary Stage which is associated with the main Stage, i.e., where the buttons are located
	 * @param title - File chooser title
	 * @param name - File name example
	 * @param extensions - Extensions allowed
	 * @return the selected file or null if no file has been selected
	 */
	private File fileChooser(Stage stage, String title, String name, String extensions) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle(title);
		fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(name, extensions));
		return fileChooser.showOpenDialog(stage);
	}

}
