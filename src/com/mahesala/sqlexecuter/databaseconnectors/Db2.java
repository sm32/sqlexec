package com.mahesala.sqlexecuter.databaseconnectors;

import java.sql.SQLException;

public class Db2 extends DatabaseDefinition {
	
	public Db2(String UserName, String Password, String URL) throws ClassNotFoundException, SQLException {
		super(UserName, Password, URL);
	}
		
	@Override
	public String JDBCClassName() {
		// TODO Auto-generated method stub
		return "com.ibm.db2.jcc.DB2Driver";
	}

	public static String DBName(){
		return "db2";
	}
	
}
