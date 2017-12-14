package pt.iscte.es1.antiSpamFilter;

public final class AntiSpamFilterConstants {

	public static final int INDEX_FALSE_NEGATIVE = 0;
	public static final int INDEX_FALSE_POSITIVE = 1;
	public static final double THRESHOLD = 5.0;
	public static final String EXPERIMENT_BASE_DIRECTORY = "experimentBaseDirectory";
	public static final String ANTI_SPAM_STUDY = EXPERIMENT_BASE_DIRECTORY + "/AntiSpamStudy";
	public static final String REFERENCE_FRONT_DIRECTORY = EXPERIMENT_BASE_DIRECTORY + "/referenceFronts";
	public static final int INDEPENDENT_RUNS = 5;
	public static final String STRATEGY_LEISURE = "Leisure";
	public static final String STRATEGY_PROFESSIONAL = "Professional";
	public static final String STRATEGY_MIXED = "Mixed";

	private AntiSpamFilterConstants() {
		// restrict instatiation
	}
}
