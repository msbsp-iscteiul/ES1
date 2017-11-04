package pt.iscte.es1.antiSpamFilter.infrastructure;

/**
 * Parser interface for AntiSpamFilter files
 *
 * @param <R> return type
 */
public interface FileReaderParser<R> {
	
	/**
	 * Parses a line from a file
	 * 
	 * @param line to be parsed
	 */
	public void parse(String line);
	
	/**
	 * Returns the result from the parsed lines
	 * 
	 * @return the result from the parsed lines
	 */
	public R getResult();
}
