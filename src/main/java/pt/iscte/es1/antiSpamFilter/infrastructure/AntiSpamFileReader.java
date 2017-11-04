package pt.iscte.es1.antiSpamFilter.infrastructure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

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
	 */
	public R readFile(String file) {
		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				parser.parse(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return parser.getResult();
	}
}
