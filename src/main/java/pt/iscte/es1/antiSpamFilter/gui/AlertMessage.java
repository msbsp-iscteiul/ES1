package pt.iscte.es1.antiSpamFilter.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import java.util.Optional;

public class AlertMessage {
	private final AlertType type;
	private final String title;
	private final String message;

	public AlertMessage(AlertType type, String title, String message) {
		this.type = type;
		this.title = title;
		this.message = message;
	}

	public Optional<ButtonType> showAndWait() {
		final Alert alertSave = new Alert(type);
		alertSave.setTitle(title);
		alertSave.setHeaderText(message);
		return alertSave.showAndWait();
	}
}
