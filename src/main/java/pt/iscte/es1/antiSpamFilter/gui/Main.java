package pt.iscte.es1.antiSpamFilter.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Anti Spam Filter");
		URL url = getClass().getClassLoader().getResource("template/select_paths.fxml");
		Parent root = FXMLLoader.load(url);
		Scene scene = new Scene(root, 600, 250);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
