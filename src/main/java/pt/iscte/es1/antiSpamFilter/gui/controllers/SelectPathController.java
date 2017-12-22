package pt.iscte.es1.antiSpamFilter.gui.controllers;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pt.iscte.es1.antiSpamFilter.domain.Message;
import pt.iscte.es1.antiSpamFilter.domain.WeightedRule;
import pt.iscte.es1.antiSpamFilter.gui.AlertMessage;
import pt.iscte.es1.antiSpamFilter.infrastructure.AntiSpamFileReader;
import pt.iscte.es1.antiSpamFilter.infrastructure.ExperimentContext;
import pt.iscte.es1.antiSpamFilter.infrastructure.LogParser;
import pt.iscte.es1.antiSpamFilter.infrastructure.RuleParser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.prefs.Preferences;

/**
 * Handles the file chooser scene
 */
public class SelectPathController {

	private static final AntiSpamFileReader<WeightedRule> RULES_READER =
		new AntiSpamFileReader<>(new RuleParser());
	private static final AntiSpamFileReader<Message> MESSAGE_READER =
		new AntiSpamFileReader<>(new LogParser());
	private static final Preferences USER_PREFERENCES = Preferences.userRoot()
			.node(SelectPathController.class.getName());
	private static final String LAST_USED_FOLDER = "LAST_USED_FOLDER";

	@FXML
	private TextField rulesPath;

	@FXML
	private TextField spamPath;

	@FXML
	private TextField hamPath;

	/**
	 * Creates an {@link ExperimentContext} with the results of the selected files
	 * @return the context
	 * @throws IOException when IO error
	 * @throws IllegalArgumentException when invalid inputs in files being read
	 */
	private ExperimentContext createContext() throws IOException {
		final List<Message> ham = MESSAGE_READER.readFile(new FileReader(hamPath.getText()));
		final List<Message> spam = MESSAGE_READER.readFile(new FileReader(spamPath.getText()));
		final List<WeightedRule> rules = RULES_READER.readFile(new FileReader(rulesPath.getText()));
		return new ExperimentContext(ham, spam, rules, new File(rulesPath.getText()));
	}

	/**
	 * @return the current stage being used
	 */
	private Stage getStage() {
		return (Stage) rulesPath.getScene().getWindow();
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
	 */
	@FXML
	protected void handleContinue(ActionEvent actionEvent) {
		try {
			final ExperimentContext context = createContext();
			openSpamConfiguration(actionEvent, context);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			new AlertMessage(Alert.AlertType.ERROR, "Errors detected in the selected files",
				"File not defined or invalid").showAndWait();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			new AlertMessage(Alert.AlertType.ERROR, "Some files have invalid data",
				"Invalid data in some or all files: " + e.getMessage()).showAndWait();
		} catch (Exception e) {
			e.printStackTrace();
			new AlertMessage(Alert.AlertType.ERROR, "Errors detected in the selected files",
				e.getMessage()).showAndWait();
		}
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
			updateLastFolderPreference(file.getParent());
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
			updateLastFolderPreference(file.getParent());
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
			updateLastFolderPreference(file.getParent());
		}
	}

	/**
	 * Update last used folder when opening a file
	 * @param lastUsedFolder last used folder for opening a file
	 */
	private static void updateLastFolderPreference(String lastUsedFolder) {
		USER_PREFERENCES.put(LAST_USED_FOLDER, lastUsedFolder);
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
		fileChooser.setInitialDirectory(new File(
			USER_PREFERENCES.get(LAST_USED_FOLDER, System.getProperty("user.home"))
		));
		fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(name, extensions));
		return fileChooser.showOpenDialog(stage);
	}

}
