package pt.iscte.es1.antiSpamFilter.infrastructure;

import javafx.scene.control.Alert;
import pt.iscte.es1.antiSpamFilter.AntiSpamFilterConstants;
import pt.iscte.es1.antiSpamFilter.gui.AlertMessage;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 * Compile R to EPS
 */
public class NsgaRToEpsCompiler {

	private static final String R = "/R";
	private static final String NSGAII_R_FILE = "/HV.Boxplot.R";
	private static final String NSGAII_EPS_FILE = "/HV.Boxplot.eps";

	/**
	 * Converts the R file into EPS file and presents it to the User through the Default Application
	 * that is specified from the user to execute/open EPS files.
	 */
	public void compileAndShow() {
		Runtime rt = Runtime.getRuntime();
		Process process = null;
		try {
			process = rt.exec("Rscript " + NSGAII_R_FILE, null,
				new File(AntiSpamFilterConstants.ANTI_SPAM_STUDY + R + NSGAII_R_FILE));
			if (process != null) {
				Desktop.getDesktop().open(
					new File(AntiSpamFilterConstants.ANTI_SPAM_STUDY + R + NSGAII_EPS_FILE));
			} else {
				new AlertMessage(Alert.AlertType.WARNING,"Error Opening File",
					"There was a problem trying to open the file.\nPlease verify if the file exists.")
					.showAndWait();
			}
		} catch (IOException e) {
			new AlertMessage(
				Alert.AlertType.ERROR,"Error Executing Script",
				"There was a problem executing the script.\nPlease configure Rscript to run the script.")
				.showAndWait();
		}
	}
}
