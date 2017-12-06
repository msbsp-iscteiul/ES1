package pt.iscte.es1.antiSpamFilter.infrastructure;

import org.junit.Before;
import org.junit.Test;
import pt.iscte.es1.antiSpamFilter.domain.PositiveNegativeSet;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class PositiveNegativeParserTest {

	private final ArrayList<String> lines = new ArrayList<>();

	@Before
	public void setUp() throws Exception {
		lines.add("1.000 4.000");
		lines.add("4.000 3.000");
		lines.add("2.000 3.000");
	}

	@Test
	public void shouldParseCorrectLines() {
		PositiveNegativeParser parser = new PositiveNegativeParser();
		assertEquals(new PositiveNegativeSet(1.0, 4.0), parser.parse(lines.get(0)));
		assertEquals(new PositiveNegativeSet(4.0, 3.0), parser.parse(lines.get(1)));
		assertEquals(new PositiveNegativeSet(2.0, 3.0), parser.parse(lines.get(2)));
	}

}
