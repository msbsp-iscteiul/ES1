package pt.iscte.es1.antiSpamFilter.infrastructure;

public interface FileReaderParser<R> {
	public void parse(String line);
	
	public R getResult();
}
