package com.bd.bdr.model;

import java.time.Instant;

import org.influxdb.annotation.Column;
import org.influxdb.annotation.Measurement;

@Measurement(name = "consommation")
public class Consommation {
	public Consommation() {
	}
	@Column(name = "time")
	private Instant time;
	
	@Column(name = "nom")
	private String nom;
	
	@Column(name = "prenom")
	private String prenom;
	
	@Column(name = "consommation")
	private long consommation;

	public Instant getTime() {
		return time;
	}

	public void setTime(Instant time) {
		this.time = time;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public float getConsommation() {
		return consommation;
	}

	public void setConsommation(long consommation) {
		this.consommation = consommation;
	}
}
