package pt.iscte.es1.antiSpamFilter.gui.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class CalculatorController {
	private Scene parentScene;

	public void initData(Scene parentScene) {
		this.parentScene = parentScene;
	}

	public void cancel(ActionEvent actionEvent) {
		Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
		window.setScene(parentScene);
	}
}
