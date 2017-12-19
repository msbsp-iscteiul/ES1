package pt.iscte.es1.antiSpamFilter.infrastructure;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import pt.iscte.es1.antiSpamFilter.domain.WeightedRule;

/**
 * Tests the {@link RulesWriter}
 */
public class RulesWriterTest {

	/**
	 * Tests the write logic
	 */
	@Test
	public void shouldWriteRules() throws IOException {
		ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
		OutputStreamWriter writer = new OutputStreamWriter(byteArrayOutputStream);

		List<WeightedRule> weightedRules = new ArrayList<>();
		weightedRules.add(new WeightedRule("regra", 1.0));
		weightedRules.add(new WeightedRule("regra2", 2.0));
		RulesWriter rw = new RulesWriter(writer);
		rw.write(weightedRules);

		assertEquals("regra\t1.0\nregra2\t2.0\n", byteArrayOutputStream.toString());
	}

	/**
	 * Tests an exception case to raise coverage
	 */
	@Test(expected = IOException.class)
	public void canTrowIOExceptionWhenWriting() throws IOException {
		Writer writer = new Writer() {
			@Override
			public void write(char[] cbuf, int off, int len) throws IOException {
				throw new IOException();
			}

			@Override
			public void flush() throws IOException {
			}

			@Override
			public void close() throws IOException {
			}
		};

		List<WeightedRule> weightedRules = new ArrayList<>();
		weightedRules.add(new WeightedRule("regra", 1.0));
		RulesWriter rw = new RulesWriter(writer);
		rw.write(weightedRules);
	}
}
