package com.mahesala.sqlexecuter;

public class SQLScriptSettings {
	
	private  String JDBCString;
	private  String UserName;
	private  String Password;	
	private  String SQLFile;
	private  String DestinationPath;
	private  String DestinationFile;
	private  String Delimiter;
	private  boolean Header;
	private  boolean AppendTime;
	private  String ScriptFriendlyName;
	private boolean ContinueOnError;
	
	public String getScriptFriendlyName() {
		return ScriptFriendlyName;
	}

	public void setScriptFriendlyName(String scriptFriendlyName) {
		ScriptFriendlyName = scriptFriendlyName;
	}

	public  String getJDBCString() {
		return JDBCString;
	}

	public  void setJDBCString(String jDBCStr) {
		JDBCString = jDBCStr;
	}

	public  String getUserName() {
		return UserName;
	}

	public  void setUserName(String userName) {
		UserName = userName;
	}

	public  String getPassword() {
		return Password;
	}

	public  void setPassword(String password) {
		Password = password;
	}

	public  String getSQLFile() {
		return SQLFile;
	}

	public  void setSQLFile(String sQLFile) {
		SQLFile = sQLFile;
	}

	public  String getDestinationPath() {
		return DestinationPath;
	}

	public  void setDestinationPath(String destinationPath) {
		DestinationPath = destinationPath;
	}

	public  String getDestinationFile() {
		return DestinationFile;
	}

	public  void setDestinationFile(String destinationFile) {
		DestinationFile = destinationFile;
	}

	public  String getDelimiter() {
		return Delimiter;
	}

	public  void setDelimiter(String delimiter) {
		Delimiter = delimiter;
	}

	public  boolean isHeader() {
		return Header;
	}

	public  void setHeader(boolean header) {
		Header = header;
	}

	public  boolean isAppendTime() {
		return AppendTime;
	}

	public  void setAppendTime(boolean appendTime) {
		AppendTime = appendTime;
	}

	public boolean getContinueOnError() {
		return ContinueOnError;
	}

	public void setContinueOnError(boolean continueOnError) {
		ContinueOnError = continueOnError;
	}
	
}
