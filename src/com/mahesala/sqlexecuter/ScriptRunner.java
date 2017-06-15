package com.mahesala.sqlexecuter;

import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ScriptRunner {
	
	private DatabaseSelector mDB;
	private String mSQLFile;
	private String mDelimiter=",";
	private String mDestination;
	private String mQuery;
	private Boolean mPrintHeader;
	private Boolean mAppendTimeStamp = false;
	private String mDestFileName;
	
	public ScriptRunner (DatabaseSelector DB, String SQLFile, String Destination, String DestFileName) throws IOException{
		mDB = DB;
		mSQLFile = SQLFile;
		mDestination = Destination;
		mDestFileName = DestFileName;
		mQuery = ReadQuery();
	}
	
	public void SetDelimiter(String Delimiter){
		mDelimiter = Delimiter;
	}
	
	public void SetAppendTimeStamp(Boolean append){
		mAppendTimeStamp = append;
	}
	
	public void Execute() throws ClassNotFoundException, SQLException, FileNotFoundException{
	    Execute(mQuery);
	}
	
	private void Execute(String Query) throws ClassNotFoundException, SQLException, FileNotFoundException {
		
		SqlExecuter.PrintLog("Executing Query Statement - "+Query);
		Statement statement = mDB.Connection().createStatement();
		statement.execute(Query);
		
		ResultSet rs = statement.getResultSet();
		if (rs != null){
			
			StringBuilder sb = new StringBuilder();
			ResultSetMetaData md = rs.getMetaData();
			int cols = md.getColumnCount();
			
			//Write Headers
			if(mPrintHeader){
							
				for(int i=1; i<=cols; i++){
					sb.append(md.getColumnLabel(i));
					if(i!=cols) sb.append(mDelimiter);
				}
				sb.append("\n");
			}
			
			//Write Result Set
			Boolean fi = true;
			while(rs.next()){
				if(fi){
					fi=false;
				}else{
					sb.append("\n");
				}
				
				for(int i=1; i<=cols; i++){
					String value = rs.getString(i);	
					if (rs.wasNull()){
						value = "NULL";
					}
					sb.append(value);
					if(i!=cols) sb.append(mDelimiter);
				}
				
			}
			
			Print(sb.toString());
		}		
		
	}
	
	private String ReadQuery() throws IOException{
		
		SqlExecuter.PrintLog("Reading SQL File - "+mSQLFile);
		
		BufferedReader bufferedReader = new BufferedReader(new FileReader(mSQLFile));
		StringBuilder sb = new StringBuilder();
		String line;
		
		while ((line = bufferedReader.readLine()) != null)
		{
		    sb.append(line).append("\n");
		}
		
		bufferedReader.close();
		
		return sb.toString();
	}
	
	/**
	 * @param ResultString
	 * 		- Holds delimited string with header
	 * @param Destination
	 * 		- File destination full path
	 * @throws FileNotFoundException
	 */
	private void Print(String ResultString) throws FileNotFoundException{
		
				
		String FileName="";
		String Extension=".csv";
		String ts_appnd_str = "_"+(new SimpleDateFormat("yyyyMMddHHmmss")).format(Calendar.getInstance().getTime());
		
		if (!mAppendTimeStamp) {
			ts_appnd_str = Extension;
		}else{
			ts_appnd_str = ts_appnd_str+Extension;
		}
		
		if (mDestination.endsWith(File.separator)) {
			FileName=mDestination+mDestFileName+ts_appnd_str;
		} else {
			FileName=mDestination+File.separator+mDestFileName+ts_appnd_str;
		}
		
		SqlExecuter.PrintLog("Writing to location - "+FileName);
		
		PrintWriter out = new PrintWriter(FileName);
		out.println(ResultString);
		out.close();
	}
	
	public String GetSQLQuery(){
		return mQuery;
	}
	
	public void SetPrintHeader(Boolean prntheader){
		mPrintHeader = prntheader;
	}
}
