package pt.iscte.es1.antiSpamFilter.gui.controllers;

import helpers.JavaFXThreadingRule;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.Rule;
import org.junit.Test;
import pt.iscte.es1.antiSpamFilter.domain.WeightedRule;
import pt.iscte.es1.antiSpamFilter.gui.Main;

import static org.junit.Assert.*;

/**
 * Tests the {@link SpamConfigurationController}
 */
public class SpamConfigurationControllerTest {
	private static final int NUMBER_OF_RULES_IN_EXAMPLE = 335;

	@Rule
	public JavaFXThreadingRule javafxRule = new JavaFXThreadingRule();

	/**
	 * Launches the {@link SpamConfigurationController} scene
	 *
	 * @return root scene
	 */
	private Parent launchScene() throws Exception {
		final Stage primaryStage = new Stage();
		new Main().start(primaryStage); // Create and
		final Parent root = primaryStage.getScene().getRoot();
		((TextField) root.lookup("#rulesPath"))
			.setText(getClass().getResource("/rules.cf").getPath());
		((TextField) root.lookup("#spamPath"))
			.setText(getClass().getResource("/spam.log").getPath());
		((TextField) root.lookup("#hamPath"))
			.setText(getClass().getResource("/ham.log").getPath());
		((Button) root.lookup("#continueButton")).fire();
		return primaryStage.getScene().getRoot();
	}

	/**
	 * Ensures the table is loaded with the rules
	 */
	@Test
	public void tableViewSizeShouldEqualExampleFile() throws Exception {
		final Parent root = launchScene();
		final int size = ((TableView) root.lookup("#tableView")).getItems().size();
		assertEquals(NUMBER_OF_RULES_IN_EXAMPLE, size);
	}

	/**
	 * Ensures the quality changes when the rules are manually changed
	 */
	@Test
	public void qualityShouldChangeWhenWeightsChangedManually() throws Exception {
		final Parent root = launchScene();
		final TableView<WeightedRule> tableView = (TableView<WeightedRule>) root.lookup("#tableView");
		tableView.getItems().get(0).setWeight(5.0);
		tableView.getItems().get(1).setWeight(5.0);
		tableView.getItems().get(2).setWeight(5.0);
		((Button) root.lookup("#evaluate")).fire();
		final Double falsePositives = Double.valueOf(((Label) root.lookup("#falsePositivesQuantity")).getText());
		final Double falseNegatives = Double.valueOf(((Label) root.lookup("#falseNegativesQuantity")).getText());
		assertEquals(new Double(209), falsePositives);
		assertEquals(new Double(73), falseNegatives);
	}

	/**
	 * Ensures the quality changes when the rules are randomly generated
	 */
	@Test
	public void qualityShouldChangeWhenWeightsChangedRandomly() throws Exception {
		final Parent root = launchScene();
		final Label falsePositivesLabel = (Label) root.lookup("#falsePositivesQuantity");
		final Label falseNegativesLabel = (Label) root.lookup("#falseNegativesQuantity");
		Double falsePositives = Double.valueOf(falsePositivesLabel.getText());
		Double falseNegatives = Double.valueOf(falseNegativesLabel.getText());
		assertEquals(new Double(0), falsePositives);
		assertEquals(new Double(239), falseNegatives);
		((Button) root.lookup("#random")).fire();
		falsePositives = Double.valueOf(falsePositivesLabel.getText());
		falseNegatives = Double.valueOf(falseNegativesLabel.getText());
		assertNotEquals(0d, falsePositives);
		assertNotEquals(239d, falseNegatives);
	}
}
