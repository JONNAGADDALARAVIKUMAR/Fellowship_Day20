package IndiaStateAnalyserDay20.IndiaStateAnalyserDay20;

public class CSVBuilderException extends Exception{
	public enum CSVBuilderExceptionType {
        UNABLE_TO_PARSE
    }
	public CSVBuilderExceptionType type;

	public CSVBuilderException(CSVBuilderExceptionType type, String message) {
		super(message);
		this.type = type;
	}
}
