package com.bd.bdr.model;

import java.time.Instant;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

@Measurement(name = "meteo")
public class Meteo {
	public Meteo() {
	}

	@Column(name = "time")
	private Instant time;
	
	@Column(name = "ville")
	private String ville;
	
	@Column(name = "temperature")
	private long temperature;

	public Instant getTime() {
		return time;
	}

	public void setTime(Instant time) {
		this.time = time;
	}

	public String getVille() {
		return ville;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public float getTemperature() {
		return temperature;
	}

	public void setTemperature(long temperature) {
		this.temperature = temperature;
	}
	
	
}
