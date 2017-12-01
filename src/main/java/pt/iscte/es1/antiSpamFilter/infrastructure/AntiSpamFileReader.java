package pt.iscte.es1.antiSpamFilter.infrastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * File reader for the AntiSpamFilter application.
 * Reads each line from a file and passes it to a parser.
 *
 * @param <R> return type
 */
public class AntiSpamFileReader<R> {

	private final FileReaderParser<R> parser;
	private List<R> results = new ArrayList<>();

	/**
	 * @param parser to use to interpret the file
	 */
	public AntiSpamFileReader(FileReaderParser<R> parser) {
		this.parser = parser;
	}

	/**
	 * Reads a file and returns the result
	 *
	 * @param inReader to read
	 * @return list or results of R type
	 * @throws IOException input or output expection
	 */
	public List<R> readFile(Reader inReader) throws IOException {
		try (BufferedReader reader = new BufferedReader(inReader)) {
			String line;
			while ((line = reader.readLine()) != null) {
				results.add(parser.parse(line));
			}
		}
		final List<R> aux = results;
		results = new ArrayList<>();
		return aux;
	}
}
