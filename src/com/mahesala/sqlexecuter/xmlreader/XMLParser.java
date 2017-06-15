package com.mahesala.sqlexecuter.xmlreader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.*;
import org.xml.sax.*;
import org.xml.sax.helpers.*;

import com.mahesala.sqlexecuter.SQLScriptSettings;

public final class XMLParser {
	private DefaultHandler mhandler;
	private List<SQLScriptSettings> Scripts = new ArrayList<SQLScriptSettings>();
	
	public List<SQLScriptSettings> Parse(String filename) throws ParserConfigurationException, SAXException, IOException{
		SAXParser saxparser = SAXParserFactory.newInstance().newSAXParser();
		
		mhandler = new XMLhandler();
		saxparser.parse(filename, mhandler);
		return Scripts;
	}
	
	private class XMLhandler extends DefaultHandler{
		
		private SQLScriptSettings mScript;
		private String mtmpValue;
		
		@Override
		public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
			
			if (qName.equalsIgnoreCase("ExecutionScript")){
				mScript = new SQLScriptSettings();
				mScript.setScriptFriendlyName(attributes.getValue("friendlyname"));
				mScript.setContinueOnError(!attributes.getValue("onerror").equalsIgnoreCase("fail"));
			}
			
		}

		@Override
		public void endElement(String uri, String localName, String qName) throws SAXException {
				if(qName.equalsIgnoreCase("ExecutionScript")){
					Scripts.add(mScript);
				}
				if(qName.equalsIgnoreCase("jdbcstring")) {
					mScript.setJDBCString(mtmpValue);
				}
				if(qName.equalsIgnoreCase("username")) {
					mScript.setUserName(mtmpValue);
				}
				if(qName.equalsIgnoreCase("password")) {
					mScript.setPassword(mtmpValue);
				}
				if(qName.equalsIgnoreCase("sqlfile")) {
					mScript.setSQLFile(mtmpValue);
				}
				if(qName.equalsIgnoreCase("destinationpath")) {
					mScript.setDestinationPath(mtmpValue);
				}
				if(qName.equalsIgnoreCase("destinationfilename")) {
					mScript.setDestinationFile(mtmpValue);
				}
				if(qName.equalsIgnoreCase("delimiter")) {
					mScript.setDelimiter(mtmpValue);
				}
				if(qName.equalsIgnoreCase("header")) {
					mScript.setHeader(true);
				}
				if(qName.equalsIgnoreCase("appendtime")) {
					mScript.setAppendTime(true);
				}
			
		}

		@Override
		public void characters(char[] ch, int start, int length) throws SAXException {
			mtmpValue = new String(ch, start, length);
		}
		
	}
	
}
