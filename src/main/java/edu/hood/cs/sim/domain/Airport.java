package edu.hood.cs.sim.domain;

public class Airport {

	public static final String FIELD_ICAO = "icao";
	public static final String FIELD_NAME = "name";
	public static final String FIELD_LONGITUDE = "longitude";
	public static final String FIELD_LATITUDE = "latitude";
	
	private String icao = null;
	private String name = null;
	private double longitude = 0;
	private double latitude = 0;
	
	public Airport() {}
	
	public String getIcao() {
		return icao;
	}
	
	public void setIcao(String icao) {
		this.icao = icao;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public double getLongitude() {
		return longitude;
	}
	
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	
	public double getLatitude() {
		return latitude;
	}
	
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
}
