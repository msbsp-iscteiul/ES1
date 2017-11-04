package pt.iscte.es1.antiSpamFilter.infrastructure;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import pt.iscte.es1.antiSpamFilter.domain.PositiveNegativeSet;;


public class PositiveNegativeParserTest {

	private final ArrayList<String> lines = new ArrayList<>();
	
	@Before
	public void setUp() throws Exception {
		lines.add("1.000 -4.000");
		lines.add("4.000 3.000");
		lines.add("2.000 -3.000");
	}
	
	@Test
	public void shouldParseCorrectLines() {
		PositiveNegativeParser parser = new PositiveNegativeParser();
		lines.forEach(line -> {
			parser.parse(line);
		});
		List<PositiveNegativeSet> result = parser.getResult();
		assertEquals(3, result.size());
		assertEquals(new PositiveNegativeSet(1.0, -4.0), result.get(0));
		assertEquals(new PositiveNegativeSet(4.0, 3.0), result.get(1));
		assertEquals(new PositiveNegativeSet(2.0, -3.0), result.get(2));
	}

}
