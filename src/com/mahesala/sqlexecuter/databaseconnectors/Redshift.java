package com.mahesala.sqlexecuter.databaseconnectors;

import java.sql.SQLException;

public class Redshift extends DatabaseDefinition {

	public Redshift(String UserName, String Password, String URL) throws ClassNotFoundException, SQLException {
		super(UserName, Password, URL);
		// TODO Auto-generated constructor stub
	}
	
	@Override()
	public String JDBCClassName() {
		return "com.amazon.redshift.jdbc4.Driver";
	}

	public static String DBName(){
		return "redshift";
	}

}
