package com.mahesala.sqlexecuter.databaseconnectors;

import java.sql.SQLException;

public class Postgres extends DatabaseDefinition {

	public Postgres(String UserName, String Password, String URL) throws ClassNotFoundException, SQLException {
		super(UserName, Password, URL);
		// TODO Auto-generated constructor stub
	}

	@Override()
	public String JDBCClassName() {
		return "org.postgresql.Driver";
	}

	public static String DBName(){
		return "postgres";
	}
	
}
