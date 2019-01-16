package com.bd.bdr.dao;

import java.util.List;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;

import com.bd.bdr.model.Consommation;
import com.bd.bdr.model.Meteo;

public class MeteoDao {

	private InfluxDB influxDB;

	public List<Meteo> getAllByVille(String ville ,String pd, String pf) {
	
		boolean isFirst = false;
		String query = "SELECT * FROM meteo " ;
		if (ville != null && !ville.trim().isEmpty()) {
			if(isFirst == false) {
				query = query + " WHERE \"ville\"=" + "\'" + ville + "\' ";
				
			}
			else
				query = query + " AND \"ville\"=" + "\'" + ville + "\' ";
			
			isFirst = true;
				
		}
			
		if (pd != null && !pd.trim().isEmpty() && pf != null && !pf.trim().isEmpty()) {
			if(isFirst == false) {
				query = query + " WHERE time>" +"\'"+ pd +" 00:00:00\'"+ " AND time<" + "\'"+pf+" 00:00:00\'";
				
			}
			else
				query = query + " AND time>" +"\'"+ pd +" 00:00:00\'"+ " AND time<" + "\'"+pf+" 00:00:00\'";
			
			isFirst = true;
		}
		
			

		connection();
		String dbName = "BDR";
		QueryResult queryResult = influxDB.query(new Query(query, dbName));
		InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
		return resultMapper.toPOJO(queryResult, Meteo.class);
	}

	public List<Meteo> getAllByPeriode(String pd, String pf) {
		String query;
		query = "SELECT * FROM meteo WHERE time>" +"\'"+ pd +" 00:00:00\'"+ " AND time<" + "\'"+pf+" 00:00:00\'";
		connection();
		String dbName = "BDR";
		QueryResult queryResult = influxDB.query(new Query(query, dbName));
		InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
		return resultMapper.toPOJO(queryResult, Meteo.class);
	}


	private void connection() {
		influxDB = InfluxDBFactory.connect("http://127.0.0.1:8086", "root", "root");
	}
}
