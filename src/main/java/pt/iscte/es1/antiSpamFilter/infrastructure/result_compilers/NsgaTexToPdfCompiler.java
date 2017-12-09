package pt.iscte.es1.antiSpamFilter.infrastructure.result_compilers;

import pt.iscte.es1.antiSpamFilter.AntiSpamFilterConstants;

import java.io.File;
import java.io.IOException;

/**
 * Compile Tex to PDF
 */
public class NsgaTexToPdfCompiler {

	private static final String LATEX_DIR = "/latex";
	private static final String NSGAII_TEX_FILE = "AntiSpamStudy.tex";
	private static final String NSGAII_PDF_FILE = "/AntiSpamStudy.pdf";

	/**
	 * Compiles the Tex file, generated from NSGAII Algorithm, creating the PDF file.
	 * @return File
	 * @throws IOException
	 */
	public File compile() throws IOException, InterruptedException {
		final Runtime rt = Runtime.getRuntime();
		rt.exec("pdflatex " + NSGAII_TEX_FILE, null,
			new File(AntiSpamFilterConstants.ANTI_SPAM_STUDY + LATEX_DIR)).waitFor();
		return new File(AntiSpamFilterConstants.ANTI_SPAM_STUDY + LATEX_DIR + NSGAII_PDF_FILE);
	}
}
