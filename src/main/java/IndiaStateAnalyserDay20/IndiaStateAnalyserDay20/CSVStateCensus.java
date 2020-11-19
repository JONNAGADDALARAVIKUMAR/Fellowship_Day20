package IndiaStateAnalyserDay20.IndiaStateAnalyserDay20;

import com.opencsv.bean.CsvBindByName;

public class CSVStateCensus {
	
	@CsvBindByName(column = "State")
	private String state;
	
	@CsvBindByName(column = "Population")
	private String population;
	
	@CsvBindByName(column = "AreaInSqKm")
	private String areaInSqKm;
	
	@CsvBindByName(column = "DensityPerSqKm", required = true)
	private String densityPerSqKm;
	
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getPopulation() {
		return population;
	}
	public void setPopulation(String population) {
		this.population = population;
	}
	public String getAreaInSqKm() {
		return areaInSqKm;
	}
	public void setAreaInSqKm(String areaInSqKm) {
		this.areaInSqKm = areaInSqKm;
	}
	public String getDensityPerSqKm() {
		return densityPerSqKm;
	}
	public void setDensityPerSqKm(String densityPerSqKm) {
		this.densityPerSqKm = densityPerSqKm;
	}
	
	@Override
	public String toString() {
		return "state Name = " + state + 
				", population = " + population +
				", areaInSqKm = " + areaInSqKm +
				", densityPerSqKm = " + densityPerSqKm;
	}
}
