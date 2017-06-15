package com.mahesala.sqlexecuter.databaseconnectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class DatabaseDefinition implements DatabaseConnector {
	
	private Connection mConnection;
	private String mUserName;
	private String mPassword;
	private String mURL;
	//private String mDBName;
	
	public DatabaseDefinition(String UserName, String Password, String URL) throws ClassNotFoundException, SQLException{
		mUserName = UserName;
		mPassword = Password;
		mURL = URL;
		SetConnection();
	}
	
	@Override
	public String URL() {
		// TODO Auto-generated method stub
		return mURL;
	}

	@Override
	public java.sql.Connection Connection() throws SQLException, ClassNotFoundException {
		//Database Connection
		return mConnection;
	}

	@Override
	public String UserName() {
		// UserName
		return mUserName;
	}

	@Override
	public String Password() {
		// Password
		return mPassword;
	}

	@Override
	public String JDBCClassName() {
		// TODO Auto-generated method stub
		return null;
	}

	private void SetConnection() throws ClassNotFoundException, SQLException  {
		//Load class into memory
		Class.forName(JDBCClassName());
        //Establish connection
        mConnection = DriverManager.getConnection(URL(), UserName(), Password());
	}

	
}
