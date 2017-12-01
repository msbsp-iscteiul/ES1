package pt.iscte.es1.antiSpamFilter.infrastructure;

import org.junit.Before;
import org.junit.Test;
import pt.iscte.es1.antiSpamFilter.domain.WeightedRule;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class AntiSpamFileReaderTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void shouldReadLineSeparatedStrings() throws IOException {
		AntiSpamFileReader<WeightedRule> fr = new AntiSpamFileReader<>(new RuleParser());

		StringBuilder sb = new StringBuilder();
		sb.append("RULE_ONE\n");
		sb.append("RULE_TWO\n");

		List<WeightedRule> result = fr.readFile(new StringReader(sb.toString()));
		assertEquals(2, result.size());
	}

	@Test(expected = IOException.class)
	public void shouldOnLockedFile() throws IOException {
		AntiSpamFileReader<WeightedRule> fr = new AntiSpamFileReader<>(new RuleParser());
		fr.readFile(new Reader() {

			@Override
			public int read(char[] cbuf, int off, int len) throws IOException {
				throw new IOException("Mocked Exception");
			}

			@Override
			public void close() throws IOException {
			}
		});
	}
}
