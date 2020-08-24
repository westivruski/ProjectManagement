package com.wesley.utils;


import java.sql.Connection;
import java.sql.DriverManager;


public class DatabaseConnectionManager {
/*	private final String url;
	private	final Properties properties;
 
	public DatabaseConnectionManager(String host, String databaseName, String username, String password){
		Class.forName("org.postgresql.Driver"); 
		this.url = "jdbc:postgresql://"+ host +"/" + databaseName;
		this.properties = new Properties();
		this.properties.setProperty("user", username);
		this.properties.setProperty("password", password);
		
	}
	
	public Connection getConnection() throws SQLException{
		return	DriverManager.getConnection(this.url,this.properties);
		
	}*/

	private static final String url = "jdbc:postgresql://localhost/ProjectMng";
    private static final String user = "postgres";
    private static final String password = "1111";
   
	    
	 
	    public static Connection getConnection() {
	    	Connection connection = null;

	        if (connection == null) {
	            try {	 
	            	 Class.forName("org.postgresql.Driver");
	                connection = DriverManager.getConnection(url, user, password);
	            } catch (Exception ex) {
	                System.out.println(ex.getMessage());
	            }
	        }
	        return connection;
	    }
}
