package IndiaStateAnalyserDay20.IndiaStateAnalyserDay20;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import org.apache.commons.io.FilenameUtils;
import com.opencsv.CSVReader;

public class StateCensusAnalyser<E> { 
	public static int loadStateCensusCsv(String FILE_PATH) throws StateCensusException { //Loads List for CSV State Census File
		try {	
			fileType(FILE_PATH);
			FileReader fileReader = new FileReader(FILE_PATH);
			CSVReader csvReader = new CSVReader(fileReader);
			LinkedList<CSVStateCensus> list = new LinkedList<>();
			String[] nextRecord;
			while((nextRecord = csvReader.readNext()) != null){
				try {
					list.add(createBook(nextRecord));
				} catch(RuntimeException e) {
					throw new StateCensusException(StateCensusException.CensusExceptionType.DELIMITER_EXCEPTION, "Delimiter Issue");
				}
			}
			return list.size() - 1;
		}  catch(FileNotFoundException e) {
			throw new StateCensusException(StateCensusException.CensusExceptionType.NO_SUCH_FILE, "File Not Found");
		}  catch(IOException e) {
			throw new StateCensusException(StateCensusException.CensusExceptionType.IO_EXCEPTION, "IO Exception");
		} catch(RuntimeException e) {
			throw new StateCensusException(StateCensusException.CensusExceptionType.HEADER_INVALID, "Header Miss Match");
		}
	}
	
	public static int loadStateCodeCsv(String FILE_PATH) throws StateCensusException { //Loads List for CSV State C File
		
		try {
			fileType(FILE_PATH);
			FileReader fileReader = new FileReader(FILE_PATH);
			CSVReader csvReader = new CSVReader(fileReader);
			LinkedList<CSVStateCensus> list = new LinkedList<>();
			String[] nextRecord;
			while((nextRecord = csvReader.readNext()) != null){
				try {
					list.add(createBook(nextRecord));
				} catch(RuntimeException e) {
					throw new StateCensusException(StateCensusException.CensusExceptionType.DELIMITER_EXCEPTION, "Delimiter Issue");
				}
			}
			return list.size() - 1;
		} catch(FileNotFoundException e) {
			throw new StateCensusException(StateCensusException.CensusExceptionType.NO_SUCH_FILE, "File Not Found");
		} catch(IOException e) {
			throw new StateCensusException(StateCensusException.CensusExceptionType.IO_EXCEPTION, "IO Exception");
		} catch(RuntimeException e) {
			throw new StateCensusException(StateCensusException.CensusExceptionType.HEADER_INVALID, "Header Miss Match");
		}	
	}
	
	private static CSVStateCensus createBook(String[] attributes) { 
		
		String state = attributes[0];
		String population = attributes[1];
		String areaInSqKm = attributes[2];
		String densityPerSqKm = attributes[3];
		
		CSVStateCensus csvStateCensus = new CSVStateCensus();
		
		csvStateCensus.setState(state);
		csvStateCensus.setPopulation(population);
		csvStateCensus.setDensityPerSqKm(densityPerSqKm);
		csvStateCensus.setAreaInSqKm(areaInSqKm);
		
		return csvStateCensus;
	}
	
	private static void fileType(String FILE_PATH) throws StateCensusException {
		String extension = FilenameUtils.getExtension(FILE_PATH);
		if(!extension.contains("csv"))
			throw new StateCensusException(StateCensusException.CensusExceptionType.EXTENSION_INVALID, "Extension Miss match");	
	}
}
