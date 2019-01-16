package com.bd.bdr.dao;

import java.util.List;

import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.Query;
import org.influxdb.dto.QueryResult;
import org.influxdb.impl.InfluxDBResultMapper;

import com.bd.bdr.model.Consommation;

public class ConsommationDao {
	private InfluxDB influxDB;
	public List<Consommation> getAllByConsommation() {
		String query;
//		if (consommation == null ) {
			query = "SELECT * FROM consommation";
//		} else {
//			query = "SELECT * FROM consommation WHERE \"consommation\"=" + consommation;
//		}
		connection();
		String dbName = "BDR";
		QueryResult queryResult = influxDB.query(new Query(query, dbName));
		InfluxDBResultMapper resultMapper = new InfluxDBResultMapper();
		return resultMapper.toPOJO(queryResult, Consommation.class);
	}
	public List<Consommation> getAllByName(String nom, String prenom,String pd ,String pf) {
		boolean isFirst = false;
		String query = "SELECT * FROM consommation ";
		//query = "SELECT * FROM consommation WHERE nom='"+ nom +"' AND prenom='" + prenom +"'";
		if (nom != null && !nom.trim().isEmpty()) {
			if(isFirst == false) {
				query = query + " WHERE \"nom\"=" + "\'" + nom + "\' ";
				
			}
			else
				query = query + " AND \"nom\"=" + "\'" + nom + "\' ";
			
			isFirst = true;
				
		}
		if (prenom != null && !prenom.trim().isEmpty()) {
			if(isFirst == false) {
				query = query + " WHERE \"prenom\"=" + "\'" + prenom + "\' ";
				
			}
			else
				query = query + " AND \"prenom\"=" + "\'" + prenom + "\' ";
			
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
		return resultMapper.toPOJO(queryResult, Consommation.class);
	}	
	private void connection() {
		influxDB = InfluxDBFactory.connect("http://127.0.0.1:8086", "root", "root");
	}

}
