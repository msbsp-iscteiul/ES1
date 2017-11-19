package pt.iscte.es1.antiSpamFilter.gui;

import java.io.File;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Main extends Application {
	
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * Starts the User Interface associated with the Selection of the Paths
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Anti Spam Filter");
		
		// Create grid
		GridPane grid = initGrid(primaryStage);
		
		// Create scene and include grid into it
		Scene scene = new Scene(grid, 600, 250);
		primaryStage.setScene(scene);
		primaryStage.show();
	}
	
	/**
	 * Initiates a new Grid with a few Rows where the first row is associated with the Rules Path,
	 * the second row associated with the Spam.log Path and the third row associated with the
	 * Ham.log Path
	 * 
	 * @return GridPane
	 */
	private GridPane initGrid(Stage stage) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(20);
        grid.setVgap(20);
        grid.setPadding(new Insets(25, 25, 25, 25));
        
        Text sceneTitle = new Text("Select Paths");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0);
        
        createRow(stage, grid, "Rules Path", "Rules File", 1);
        createRow(stage, grid, "Spam.log Path", "Spam.log File", 2);
        createRow(stage, grid, "Ham.log Path", "Ham.log File", 3);
        
        createButtons(grid);
               
        return grid;
	}
	
	/**
	 * Creates the Continue and Close buttons. The Continue button will navigate the user
	 * to the next Panel while the Close button will close the application.
	 * @param grid - Grid on which the buttons are added.
	 */
	private void createButtons(GridPane grid) {
		Button closeButton = new Button("Close");
		closeButton.setPrefWidth(75);
		closeButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Platform.exit();
			}
		});
		Button continueButton = new Button("Continue");
		continueButton.setPrefWidth(100);
		HBox hbBtn1 = new HBox(10);
		hbBtn1.setAlignment(Pos.BOTTOM_RIGHT);
		hbBtn1.getChildren().add(closeButton);
		HBox hbBtn2 = new HBox(10);
		hbBtn2.setAlignment(Pos.BOTTOM_LEFT);
		hbBtn2.getChildren().add(continueButton);
		grid.add(hbBtn2, 2, 4);
		grid.add(hbBtn1, 1, 4);
	}
	
	/**
	 * Creates a row which consists on a Label, TextField and a Button to upload the path.
	 * @param grid - items to be inserted in the grid
	 * @param textLabel - name for the Label
	 * @param textButton - name for the Button
	 * @param row - number of the row
	 */
	private void createRow(Stage stage, GridPane grid, String textLabel, String textButton, int row) {
		int col = 0;
        Label label = new Label(textLabel + ": ");
        label.setPrefWidth(125);
        grid.add(label, col++, row);
        
        TextField textField = new TextField();
        textField.setPrefWidth(275);
        textField.setEditable(false);
        grid.add(textField, col++, row);
        
        Button button = new Button(textButton);
        button.setPrefWidth(100);
        button.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				File file = fileChooser(stage, textLabel);
				if (file != null) {
					textField.setText(file.getAbsolutePath());
				}
			}
			
		});
        grid.add(button, col, row);
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
