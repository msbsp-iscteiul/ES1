package pt.iscte.es1.antiSpamFilter.gui.controllers;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class SelectPathController {

	@FXML
	private Button continueButton;

	@FXML
	private TextField rulesPath;

	@FXML
	private TextField spamPath;

	@FXML
	private TextField hamPath;

	@FXML
	protected void handleClose() {
		Platform.exit();
	}

        private Stage getStage() {
                return (Stage) rulesPath.getScene().getWindow();
        }

	@FXML
	protected void handleContinue() {
		// TODO
	}

	@FXML
	protected void handleRulesPath() {
                File file = fileChooser(getStage(), "Rules", "Rules.cf", "*.cf");
		if (file != null) {
			rulesPath.setText(file.getAbsolutePath());
		}
	}

	@FXML
	protected void handleSpamPath() {
                File file = fileChooser(getStage(), "Spam", "Spam.log", "*.log");
		if (file != null) {
			spamPath.setText(file.getAbsolutePath());
		}
	}

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
