package com.mahesala.sqlexecuter;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;
import com.mahesala.sqlexecuter.xmlreader.*;

/**
 * @author Sreekanth Mahesala
 * $java -jar SQLExecuter-with-dependencies.jar jdbc:db2://edwprcr01:40000/SEDSPRDB prdetl Gordon /Users/mahsr001/work/misc/db2_test.sql /Users/mahsr001/work/misc/ "||" false
 */
public class SqlExecuter {

	/**
	 * @param args 
	 * 		- command line arguments
	 * @throws ClassNotFoundException
	 * 		- if any JDBC class file name is not found
	 * @throws IOException
	 * 		- if any Reader operation errors occur
	 * @throws SQLException
	 * 		- if there is a SQL error
	 * @throws SAXException 
	 * @throws ParserConfigurationException 
	 */
	
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		
		
		PrintLog(SqlExecuter.class.getSimpleName()+"- Process Initiate");
		
		/* Parse XML File
		 * SQLExecuterXMLSettings will have all resultant varaiables
		 * */
//		String fileName="";
//		if(args.length>0){
//			fileName = args[0];
//		}else{
//			PrintLog("Missing argument - xml file");
//			System.exit(0);
//		}
		
		PrintLog("Reading execution settings");
		List<SQLScriptSettings> Scripts = new XMLParser().Parse("/Users/mahsr001/work/misc/sqlexecuter/SQLExecuterConfig.xml");
		
		for(SQLScriptSettings script: Scripts){
			try{
				PrintLog("Executing script - "+script.getScriptFriendlyName());
				
				/*Select appropriate database connection settings
				 * and pass it to Script runner to execute
				 * */
				
				DatabaseSelector database = new DatabaseSelector(script.getUserName(), script.getPassword(), script.getJDBCString());
				ScriptRunner sr = new ScriptRunner(database,script.getSQLFile(), script.getDestinationPath(),script.getDestinationFile());
				
				/*Set delimiter
				 * */
				sr.SetDelimiter(script.getDelimiter());
				sr.SetPrintHeader(script.isHeader());
				sr.SetAppendTimeStamp(script.isAppendTime());
				
				/*Execute the connection with the statement in SQL file
				 * */
				sr.Execute();
			}catch(SQLException|IOException|ClassNotFoundException e){
				PrintLog("Error "+e.getMessage());
				if(!script.getContinueOnError()){
					PrintLog("System shutdown post error");
					System.exit(0);
				}
			}			
		}
		
		
	    PrintLog(SqlExecuter.class.getSimpleName()+"- Process Complete");
	}
	
	/*Print Messages
	 * */
	public static void PrintLog(String msg){
		System.out.println(msg+"\n");
	}

}
