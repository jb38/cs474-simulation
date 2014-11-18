package edu.hood.cs.sim.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AIRPORTS")
public class Airport {

	private int id = -1;
	private String icao = null;
	private String name = null;
	private double latitude = 0;
	private double longitude = 0;
	
	@Id
    public int getId() {
        return this.id;
    }
 
    public void setId(int id) {
        this.id = id;
    }

    @Column(name="AIRPORT_ICAO")
    public String getIcao() {
		return icao;
	}

	public void setIcao(String icao) {
		this.icao = icao;
	}

	@Column(name="AIRPORT_NAME")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="LATITUDE")
	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	@Column(name="LONGITUDE")
	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
}
