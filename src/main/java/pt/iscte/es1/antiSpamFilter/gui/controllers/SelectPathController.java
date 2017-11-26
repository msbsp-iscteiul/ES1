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
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

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
	protected void handleContinue(ActionEvent actionEvent) throws IOException {
		openSpamConfiguration(actionEvent);
	}

	private void openSpamConfiguration(ActionEvent actionEvent) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("template/calculator.fxml"));
		Parent parent = loader.load();
		Scene spamConfigurationScene = new Scene(parent);

		CalculatorController calculatorController = loader.getController();
		calculatorController.initData(((Node) actionEvent.getSource()).getScene());

		Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
		window.setScene(spamConfigurationScene);
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
