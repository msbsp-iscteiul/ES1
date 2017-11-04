package pt.iscte.es1.antiSpamFilter.infrastructure;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AntiSpamFileReader<R> {

	private final FileReaderParser<R> parser;
	
	public AntiSpamFileReader(FileReaderParser<R> parser) {
		this.parser = parser;
	}
		
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
