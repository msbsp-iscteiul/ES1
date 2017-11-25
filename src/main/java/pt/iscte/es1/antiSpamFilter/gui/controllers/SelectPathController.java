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

	@FXML
	protected void handleContinue() {
		// TODO
	}

	@FXML
	protected void handleRulesPath() {
		File file = fileChooser((Stage) rulesPath.getScene().getWindow(), "Rules");
		if (file != null) {
			rulesPath.setText(file.getAbsolutePath());
		}
	}

	@FXML
	protected void handleSpamPath() {
		File file = fileChooser((Stage) spamPath.getScene().getWindow(), "Spam.log");
		if (file != null) {
			spamPath.setText(file.getAbsolutePath());
		}
	}

	@FXML
	protected void handleHamPath() {
		File file = fileChooser((Stage) hamPath.getScene().getWindow(), "Ham.log");
		if (file != null) {
			hamPath.setText(file.getAbsolutePath());
		}
	}



	/**
	 * File Chooser which returns a File that the user selects. The only files that are allowed are: .log and .cf.
	 * @param stage - Primary Stage which is associated with the main Stage, i.e., where the buttons are located
	 * @return {File} - File that the user as selected
	 */
	private File fileChooser(Stage stage, String text) {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Log Files");
		fileChooser.setInitialDirectory(
				new File(System.getProperty("user.home"))
		);
		if (text.contains("Rules")) {
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Rules", "*.cf"));
		} else {
			fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Log", "*.log"));
		}
		return fileChooser.showOpenDialog(stage);
	}

}
