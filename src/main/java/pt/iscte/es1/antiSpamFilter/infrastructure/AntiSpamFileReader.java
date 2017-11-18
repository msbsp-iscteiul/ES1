package pt.iscte.es1.antiSpamFilter.infrastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;

/**
 * File reader for the AntiSpamFilter application.
 * Reads each line from a file and passes it to a parser.
 *
 * @param <R> return type
 */
public class AntiSpamFileReader<R> {

	private final FileReaderParser<R> parser;
	
	/**
	 * @param parser to use to interpret the file
	 */
	public AntiSpamFileReader(FileReaderParser<R> parser) {
		this.parser = parser;
	}
		
	/**
	 * Reads a file and returns the result
	 * 
	 * @param file to read
	 * @return
	 * @throws IOException 
	 */
	public R readFile(Reader inReader) throws IOException {
		try (BufferedReader reader = new BufferedReader(inReader)) {
			String line;
			while ((line = reader.readLine()) != null) {
				parser.parse(line);
			}
		}
		return parser.getResult();
	}
}
