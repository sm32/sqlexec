package com.mahesala.sqlexecuter;

import java.sql.SQLException;

import com.mahesala.sqlexecuter.databaseconnectors.DatabaseConnector;
import com.mahesala.sqlexecuter.databaseconnectors.Db2;
import com.mahesala.sqlexecuter.databaseconnectors.Postgres;
import com.mahesala.sqlexecuter.databaseconnectors.Redshift;
import com.mahesala.sqlexecuter.databaseconnectors.Teradata;

public class DatabaseSelector implements DatabaseConnector {
	
	private DatabaseConnector mDb;
	
	public DatabaseSelector(String UserName,String Password,String url) throws ClassNotFoundException, SQLException{
		String dbname = url.split("//")[0].toLowerCase();
		
		if (dbname.contains(Db2.DBName())){
			mDb = new Db2(UserName,Password,url);
		} else if (dbname.contains(Teradata.DBName())){
			mDb = new Teradata(UserName, Password, url);
		} else if (dbname.contains(Postgres.DBName())){
			mDb = new Postgres(UserName, Password, url);			
		} else if (dbname.contains(Redshift.DBName())){
			mDb = new Redshift(UserName, Password, url);
		}
	}
			
	@Override
	public String URL() {
		return mDb.URL();
	}

	@Override
	public java.sql.Connection Connection() throws SQLException, ClassNotFoundException {
		return mDb.Connection();
	}

	@Override
	public String UserName() {
		return mDb.UserName();
	}

	@Override
	public String Password() {
		return mDb.Password();
	}

	@Override
	public String JDBCClassName() {
		return mDb.JDBCClassName();
	}
	
	public void CloseConnection() throws ClassNotFoundException, SQLException{
		mDb.Connection().close();
	}
	
}
