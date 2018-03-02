package com.database;

import java.beans.PropertyVetoException;
import java.sql.*;

import com.mchange.v2.c3p0.*;

public class DataSource {

	private static DataSource dataSource;
	private ComboPooledDataSource cpds;
	
	public static String dbStatus = "OFFLINE";
	
	private DataSource() throws SQLException, PropertyVetoException {
		cpds = new ComboPooledDataSource();
		cpds.setDriverClass("com.mysql.jdbc.Driver");
		cpds.setJdbcUrl("jdbc:mysql://DT-SRV-DT5/SP2GR5");
		cpds.setUser("SP2GR5");
		cpds.setPassword("p783D");
		
		// settings:
		cpds.setInitialPoolSize(5);
		cpds.setMinPoolSize(5);
		cpds.setAcquireIncrement(5);
		cpds.setMaxPoolSize(25);
		cpds.setMaxStatements(100);
	}
	
	public static Connection getConnection() throws SQLException {
		if (dataSource == null){
			try{
				dataSource = new DataSource();
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return dataSource.getPooledConnection();
	}
	
	public Connection getPooledConnection() throws SQLException{
		return cpds.getConnection();
	}
	
	//functie test de connectie en update de dbStatus variabele
	public static boolean testConn() {
		boolean bool = false;
		try (Connection conn = DataSource.getConnection()) {
			bool = conn.isValid(0);
			if (bool == true){
				dbStatus = "ONLINE";
			}
			else dbStatus = "OFFLINE";
		} catch (SQLException e) {
			System.out.println(e);
		}
		return bool;
	}

}
