package IndiaStateAnalyserDay20.IndiaStateAnalyserDay20;

public class StateCensusException extends Exception{
	
	enum CensusExceptionType {
		NO_SUCH_FILE,IO_EXCEPTION,EXTENSION_INVALID,DELIMITER_EXCEPTION,HEADER_INVALID
	}
	
	CensusExceptionType type;
	
	public StateCensusException() {
		
	}
	
	public StateCensusException(CensusExceptionType type, String exceptionMessage) {
		super(exceptionMessage);
		this.type = type;
	}
}
