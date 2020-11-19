package IndiaStateAnalyserDay20.IndiaStateAnalyserDay20;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;
import org.apache.commons.io.FilenameUtils;
import org.junit.internal.Throwables;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opencsv.CSVReader;

public class StateCensusAnalyser<E> { 
	
	private static final String STATE_CENSUS_FILE = "C:\\Users\\Admin\\workspace\\IndiaStateAnalyserDay20\\src\\main\\resources\\stateCensusJson.json";
	private static final String STATE_CODES_FILE = "C:\\Users\\Admin\\workspace\\IndiaStateAnalyserDay20\\src\\main\\resources\\stateCodesJson.json";
	private static final String STATE_CENSUS_MOSTPOULOUS_FILE = "C:\\Users\\Admin\\workspace\\IndiaStateAnalyserDay20\\src\\main\\resources\\stateCensusMostPopulous.json";
	private static final String FILE_PATH_DENSITY = "C:\\Users\\Admin\\workspace\\IndiaStateAnalyserDay20\\src\\main\\resources\\stateCensusMostPoulationDensity.json";
	
	public static int loadStateCensusCsv(String FILE_PATH) throws StateCensusException { //Loads List for CSV State Census File
		try {	
			fileType(FILE_PATH);
			FileReader fileReader = new FileReader(FILE_PATH);
			CSVReader csvReader = new CSVReader(fileReader);
			LinkedList<CSVStateCensus> list = new LinkedList<>();
			String[] nextRecord;
			while((nextRecord = csvReader.readNext()) != null){
				try {
					list.add(createList(nextRecord));
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
	
	public static int loadStateCodeCsv(String FILE_PATH) throws StateCensusException { //Loads List for CSV State Code File
		
		try {
			fileType(FILE_PATH);
			FileReader fileReader = new FileReader(FILE_PATH);
			CSVReader csvReader = new CSVReader(fileReader);
			LinkedList<CSVStateCensus> list = new LinkedList<>();
			String[] nextRecord;
			while((nextRecord = csvReader.readNext()) != null){
				try {
					list.add(createList(nextRecord));
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
	
	private static void fileType(String FILE_PATH) throws StateCensusException {
		String extension = FilenameUtils.getExtension(FILE_PATH);
		if(!extension.contains("csv"))
			throw new StateCensusException(StateCensusException.CensusExceptionType.EXTENSION_INVALID, "Extension Miss match");	
	}

	public static String[] sortStates(String FILE_PATH) throws IOException {//Sorts according to state names
		ArrayList<CSVStateCensus> list = new ArrayList<>();
		try {	
			FileReader fileReader = new FileReader(FILE_PATH);
			CSVReader csvReader = new CSVReader(fileReader);
			String[] nextRecord;
			while((nextRecord = csvReader.readNext()) != null){
					list.add(createList(nextRecord));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return createJson(list);
	}

	private static String[] createJson(ArrayList<CSVStateCensus> list) throws IOException { //Sorts the states and Creates JSON file
		
		ArrayList<CSVStateCensus> sortedList = (ArrayList<CSVStateCensus>) list.stream()
												.sorted(Comparator.comparing(CSVStateCensus::getState))
												.collect(Collectors.toList());
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.setPrettyPrinting().create();
		String json = gson.toJson(sortedList);
		
		try (FileWriter file = new FileWriter(STATE_CENSUS_FILE)) {      	 
			 file.write(json);
			 file.flush();
		}
		String firstState = sortedList.get(0).getState();
		String lastState = sortedList.get(29).getState();
		String[] firstAndLastStates = {firstState, lastState};
		return firstAndLastStates;
	}
	
	private static CSVStateCensus createList(String[] attributes) { //Creates List
		
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

	public static String[] sortStateCodes(String FILE_PATH) throws IOException, StateCensusException {
		
		ArrayList<IndianStateCodes> list = new ArrayList<>();
		try {	
			FileReader fileReader = new FileReader(FILE_PATH);
			CSVReader csvReader = new CSVReader(fileReader);
			String[] nextRecord;
			while((nextRecord = csvReader.readNext()) != null)
				list.add(createCodeList(nextRecord));
		}
		catch(NullPointerException | IOException e) {
			e.printStackTrace();
		}
		return createStateCodeJson(list);
	}

	private static String[] createStateCodeJson(ArrayList<IndianStateCodes> list) throws IOException {
		
		ArrayList<IndianStateCodes> sortedList = (ArrayList<IndianStateCodes>) list.stream()
				.sorted(Comparator.comparing(IndianStateCodes::getState))
				.collect(Collectors.toList());
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.setPrettyPrinting().create();
		String json = gson.toJson(sortedList);

		try (FileWriter file = new FileWriter(STATE_CODES_FILE)) {      	 
			file.write(json);
			file.flush();
		}
		String firstState = sortedList.get(0).getStateCode();
		String lastState = sortedList.get(37).getStateCode();
		String[] firstAndLastStates = {firstState, lastState};
		return firstAndLastStates;

}

	private static IndianStateCodes createCodeList(String[] nextRecord) {
		String srNo = nextRecord[0];
		String state = nextRecord[1];
		String tin = nextRecord[2];
		String stateCode = nextRecord[3];
		
		IndianStateCodes indianStateCodes = new IndianStateCodes();
		
		indianStateCodes.setState(state);
		indianStateCodes.setSrNo(srNo);
		indianStateCodes.setTin(tin);
		indianStateCodes.setStateCode(stateCode);
		
		return indianStateCodes;
	}

	public static int sortPopulousStates(String FILE_PATH) throws IOException {
		ArrayList<CSVStateCensus> list = new ArrayList<>();
		try {	
			FileReader fileReader = new FileReader(FILE_PATH);
			CSVReader csvReader = new CSVReader(fileReader);
			String[] nextRecord;
			while((nextRecord = csvReader.readNext()) != null){
					list.add(createList(nextRecord));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return createMostPupulousJson(list);
	}

	private static int createMostPupulousJson(ArrayList<CSVStateCensus> list) throws IOException {
		ArrayList<CSVStateCensus> sortedList = (ArrayList<CSVStateCensus>) list.stream()
												.sorted((poulation1, poulation2) -> poulation2.getPopulation().compareTo(poulation1.getPopulation()))
												.collect(Collectors.toList());
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.setPrettyPrinting().create();
		String json = gson.toJson(sortedList);

		try (FileWriter file = new FileWriter(STATE_CENSUS_MOSTPOULOUS_FILE)) {      	 
			file.write(json);
			file.flush();
		}
		return sortedList.size() - 1;
	}

	public static int sortPopulousDensityStates(String FILE_PATH) throws IOException {
		ArrayList<CSVStateCensus> list = new ArrayList<>();
		try {	
			FileReader fileReader = new FileReader(FILE_PATH);
			CSVReader csvReader = new CSVReader(fileReader);
			String[] nextRecord;
			while((nextRecord = csvReader.readNext()) != null){
					list.add(createList(nextRecord));
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		return createMostPupulousDensityJson(list);
	}

	private static int createMostPupulousDensityJson(ArrayList<CSVStateCensus> list) throws IOException {
		ArrayList<CSVStateCensus> sortedList = (ArrayList<CSVStateCensus>) list.stream()
				.sorted((poulation1, poulation2) -> poulation2.getDensityPerSqKm().compareTo(poulation1.getDensityPerSqKm()))
				.collect(Collectors.toList());
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.setPrettyPrinting().create();
		String json = gson.toJson(sortedList);

		try (FileWriter file = new FileWriter(FILE_PATH_DENSITY)) {      	 
			file.write(json);
			file.flush();
		}
		return sortedList.size() - 1;
	}
}
