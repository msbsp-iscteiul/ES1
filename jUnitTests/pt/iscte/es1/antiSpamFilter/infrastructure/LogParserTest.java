package pt.iscte.es1.antiSpamFilter.infrastructure;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class LogParserTest {

	private final List<String> lines = new ArrayList<>();
	
	@Before
	public void setUp() throws Exception {
		lines.add("xval_initial/9/_ham_/00035.a0e0e8cdca0b8352a9e9c2c81e5d5cd7	BAYES_00	HTML_FONT_SIZE_LARGE	HTML_MESSAGE	MIME_HTML_ONLY	SPF_FAIL");
		lines.add("xval_initial/9/_ham_/00353.ec01b62420323fffe253643fe0439c86	BAYES_00	RDNS_NONE");
		lines.add("xval_initial/9/_ham_/00286.74f122eeb4cd901867d74f5676c85809	BAYES_00	SPF_HELO_FAIL");
	}
	
	@Test
	public void ShouldParseCorrectLines() {
		LogParser parser = new LogParser();
		lines.forEach(line -> {
			parser.parse(line);
		});
		List<HashSet<String>> result = parser.getResult();
		assertEquals(3, result.size());
		assertTrue(result.get(0).contains("HTML_MESSAGE"));
		assertTrue(result.get(1).contains("RDNS_NONE"));
		assertTrue(result.get(2).contains("SPF_HELO_FAIL"));
	}
	
	@Test
	public void ShouldNotIncludeFileName() {
		LogParser parser = new LogParser();
		parser.parse(lines.get(0));
		assertFalse(parser.getResult().get(0).contains("xval_initial/9/_ham_/00035.a0e0e8cdca0b8352a9e9c2c81e5d5cd7"));
	}

}
