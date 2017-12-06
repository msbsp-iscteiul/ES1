package pt.iscte.es1.antiSpamFilter.infrastructure;

import javafx.scene.control.Alert;
import pt.iscte.es1.antiSpamFilter.AntiSpamFilterConstants;
import pt.iscte.es1.antiSpamFilter.gui.AlertMessage;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;

/**
 * Compile Tex to PDF
 */
public class NsgaTexToPdfCompiler {

	private static final String LATEX_DIR = "/latex";
	private static final String NSGAII_TEX_FILE = "/AntiSpamStudy.tex";
	private static final String NSGAII_PDF_FILE = "/AntiSpamStudy.pdf";

	/**
	 * Converts the TEX file into PDF file and presents it to the User through the Default Application
	 * that is specified from the user to execute/open PDF files.
	 */
	public void compileAndShow() {
		Runtime rt = Runtime.getRuntime();
		Process process = null;
		try {
			process = rt.exec("pdflatex", null,
				new File(AntiSpamFilterConstants.ANTI_SPAM_STUDY + LATEX_DIR + NSGAII_TEX_FILE));
			if (process != null) {
				Desktop.getDesktop().open(
					new File(AntiSpamFilterConstants.ANTI_SPAM_STUDY + LATEX_DIR + NSGAII_PDF_FILE));
			} else {
				new AlertMessage(
					Alert.AlertType.WARNING,"Error Opening File",
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