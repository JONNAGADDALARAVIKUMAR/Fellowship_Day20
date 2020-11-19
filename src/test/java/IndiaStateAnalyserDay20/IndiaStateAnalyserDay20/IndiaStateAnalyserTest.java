package IndiaStateAnalyserDay20.IndiaStateAnalyserDay20;

import org.junit.Assert;
import org.junit.Test;

public class IndiaStateAnalyserTest {
	private static final String FILE_PATH = "C:\\Users\\Admin\\workspace\\IndiaStateAnalyserDay20\\src\\main\\resources\\IndiaStateCensusData.csv";
	private static final String WRONG_FILE_PATH = "C:\\Users\\Admin\\workspace\\StateAnalyserDay20\\src\\main\\resources\\IndiaStateCensusData.csv";
	private static final String FILE_PATH_WRONG_EXTENSION = "C:\\Users\\Admin\\workspace\\IndiaStateAnalyserDay20\\src\\main\\resources\\IndiaStateCensusData.json";
	private static final String FILE_PATH_DELIMITER = "C:\\Users\\Admin\\workspace\\IndiaStateAnalyserDay20\\src\\main\\resources\\IndiaStateCensusDataDelimiter.csv";
	private static final String FILE_PATH_HEADER = "C:\\Users\\Admin\\workspace\\IndiaStateAnalyserDay20\\src\\main\\resources\\IndiaStateCensusDataHeader.csv";
	
	private static final String FILE_PATH_STATECODE = "C:\\Users\\Admin\\workspace\\IndiaStateAnalyserDay20\\src\\main\\resources\\IndiaStateCode.csv";
	private static final String FILE_PATH_STATECODE_EXTENSION = "C:\\Users\\Admin\\workspace\\IndiaStateAnalyserDay20\\src\\main\\resources\\IndiaStateCode.json";
	private static final String FILE_PATH_STATECODE_DELIMITER = "C:\\Users\\Admin\\workspace\\IndiaStateAnalyserDay20\\src\\main\\resources\\IndiaStateCodeDelimiter.csv";
	private static final String FILE_PATH_STATECODE_HEADER = "C:\\Users\\Admin\\workspace\\IndiaStateAnalyserDay20\\src\\main\\resources\\IndiaStateCodeHeader.csv";

	@Test
	public void thisTestCasePasesWhenReturnValueEqualsTonumberOfStates() throws StateCensusException {
		int count = StateCensusAnalyser.loadStateCensusCsv(FILE_PATH);
		Assert.assertEquals(29, count);
	}
	
	@Test
	public void thisTestCasePasesWhenReturnExceptionIsNoSUchFile() {
		try{
			StateCensusAnalyser.loadStateCensusCsv(WRONG_FILE_PATH);
		} catch(StateCensusException e) {
			Assert.assertEquals(StateCensusException.CensusExceptionType.NO_SUCH_FILE, e.type);
		}
	}
	
	@Test
	public void thisTestCasePasesWhenReturnExceptionWhenExtensionIncorrect() {
		try{
			StateCensusAnalyser.loadStateCensusCsv(FILE_PATH_WRONG_EXTENSION);
		} catch(StateCensusException e) {
			Assert.assertEquals(StateCensusException.CensusExceptionType.EXTENSION_INVALID, e.type);
		}
	}
	
	@Test
	public void thisTestCasePasesWhenReturnExceptionWhenIncorrectDelimiterArised() {
		try{
			StateCensusAnalyser.loadStateCensusCsv(FILE_PATH_DELIMITER);
		} catch(StateCensusException e) {
			Assert.assertEquals(StateCensusException.CensusExceptionType.DELIMITER_EXCEPTION, e.type);
		}
	}
	
	@Test
	public void thisTestCasePasesWhenReturnExceptionWhenIncorrectHeader() {
		try{
			StateCensusAnalyser.loadStateCensusCsv(FILE_PATH_HEADER);
		} catch(StateCensusException e) {
			Assert.assertEquals(StateCensusException.CensusExceptionType.HEADER_INVALID, e.type);
		}
	}
	
	@Test
	public void thisTestCasePasesWhenReturnValueEqualsTonumberOfStatesStateCodes() throws StateCensusException, CSVBuilderException {
		int count = StateCensusAnalyser.loadStateCodeCsv(FILE_PATH_STATECODE);
		Assert.assertEquals(37, count);
	}
	
	@Test
	public void thisTestCasePasesWhenReturnExceptionIsNoSUchFileStatesStateCodes() throws CSVBuilderException {
		try{
			StateCensusAnalyser.loadStateCodeCsv(WRONG_FILE_PATH);
		} catch(StateCensusException e) {
			Assert.assertEquals(StateCensusException.CensusExceptionType.NO_SUCH_FILE, e.type);
		}
	}
	
	@Test
	public void thisTestCasePasesWhenReturnExceptionWhenExtensionIncorrectStateCodes() throws CSVBuilderException {
		try{
			StateCensusAnalyser.loadStateCodeCsv(FILE_PATH_STATECODE_EXTENSION);
		} catch(StateCensusException e) {
			Assert.assertEquals(StateCensusException.CensusExceptionType.EXTENSION_INVALID, e.type);
		}
	}
	
	@Test
	public void thisTestCasePasesWhenReturnExceptionWhenIncorrectDelimiterArisedSteteCodes() throws CSVBuilderException {
		try{
			StateCensusAnalyser.loadStateCodeCsv(FILE_PATH_STATECODE_DELIMITER);
		} catch(StateCensusException e) {
			Assert.assertEquals(StateCensusException.CensusExceptionType.DELIMITER_EXCEPTION, e.type);
		}
	}
	
	@Test
	public void thisTestCasePasesWhenReturnExceptionWhenIncorrectHeaderStateCodes() throws CSVBuilderException {
		try{
			StateCensusAnalyser.loadStateCodeCsv(FILE_PATH_STATECODE_HEADER);
		} catch(StateCensusException e) {
			Assert.assertEquals(StateCensusException.CensusExceptionType.HEADER_INVALID, e.type);
		}
	}
}
