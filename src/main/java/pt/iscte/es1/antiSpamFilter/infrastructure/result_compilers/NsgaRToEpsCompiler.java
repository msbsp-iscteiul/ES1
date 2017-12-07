package pt.iscte.es1.antiSpamFilter.infrastructure.result_compilers;

import pt.iscte.es1.antiSpamFilter.AntiSpamFilterConstants;

import java.io.File;
import java.io.IOException;

/**
 * Compile R to EPS
 */
public class NsgaRToEpsCompiler {

	private static final String R = "/R";
	private static final String NSGAII_R_FILE = "HV.Boxplot.R";
	private static final String NSGAII_EPS_FILE = "/HV.Boxplot.eps";

	/**
	 * Compiles the R file, generated from NSGAII Algorithm, creating the EPS file.
	 * @return File
	 * @throws IOException
	 */
	public File compile() throws IOException, InterruptedException {
		final Runtime rt = Runtime.getRuntime();
		rt.exec("Rscript " + NSGAII_R_FILE, null,
			new File(AntiSpamFilterConstants.ANTI_SPAM_STUDY + R)).waitFor();
		return new File(AntiSpamFilterConstants.ANTI_SPAM_STUDY + R + NSGAII_EPS_FILE);
	}
}
