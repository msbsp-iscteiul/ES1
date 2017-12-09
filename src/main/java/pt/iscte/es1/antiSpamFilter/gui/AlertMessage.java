package pt.iscte.es1.antiSpamFilter.gui;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

import java.util.Optional;

/**
 * Alert Message Dialog
 */
public class AlertMessage {
	private final AlertType type;
	private final String title;
	private final String message;

	/**
	 * Constructor to create a Message Alert
	 * @param type - Type of the Alert
	 * @param title - Title to be shown
	 * @param message - Message Header to be shown
	 */
	public AlertMessage(AlertType type, String title, String message) {
		this.type = type;
		this.title = title;
		this.message = message;
	}

	/**
	 * Presents the Alert Dialog
	 *
	 * @return Optional<ButtonType>
	 */
	public Optional<ButtonType> showAndWait() {
		final Alert alertSave = new Alert(type);
		alertSave.setTitle(title);
		alertSave.setHeaderText(message);
		return alertSave.showAndWait();
	}
}
