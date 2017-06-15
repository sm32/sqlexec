package com.mahesala.sqlexecuter.databaseconnectors;

import java.sql.Connection;
import java.sql.SQLException;

public interface DatabaseConnector {
	public String URL();
	public Connection Connection() throws SQLException, ClassNotFoundException;
	public String UserName();
	public String Password();
	public String JDBCClassName();
}
