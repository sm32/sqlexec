package com.mahesala.sqlexecuter.databaseconnectors;

import java.sql.SQLException;

public class Teradata extends DatabaseDefinition {
	
	public Teradata(String UserName, String Password, String URL) throws ClassNotFoundException, SQLException {
		super(UserName, Password, URL);
		
	}

	@Override
	public String JDBCClassName() {
		return "com.teradata.jdbc.TeraDriver";
	}

	public static String DBName(){
		return "teradata";
	}

}
