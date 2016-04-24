package com.xtracta.supplier.invoiceParser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * This class parses invoice text using the JSON.simple library. This library provides a reasonable balance between ease-of-use and performance
 * @see <a href="http://blog.takipi.com/the-ultimate-json-library-json-simple-vs-gson-vs-jackson-vs-json/">comparison benchmark</a>
 * @author firas
 *
 */
public class JSONInvoiceParser extends AbstractInvoiceParser {

	private BufferedReader br = null;
	private ArrayList<InvoiceRecord> invoice = null;
	
	public JSONInvoiceParser(String filePath){
		try {
			br = new BufferedReader(new FileReader(filePath));
		}
		catch(IOException e){
			e.printStackTrace();
			br = null;
		}
	}
	/**
	 * 
	 * @return an <code> EnumMap </code> over <code> AbstractInvoiceParser.JSON_TAGS </code> with the corresponding values of the tags
	 * in the next line. <code> null </code> returned in case end of file reached, in case of an exception
	 * or in case the file is empty or could not be opened for reading
	 */
	public InvoiceRecord next(){
		if(br == null) return null;
		try {
			String line = br.readLine();
			if(line == null) return null;
			//much better option to print invoice file with double quotes
			line = line.replace("\'", "\"");
			JSONParser parser = new JSONParser();
			
			InvoiceRecord record = new InvoiceRecord( (JSONObject) parser.parse(line) );

			return record;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	/**
	 * order the invoice records according to their <code>JSON_TAGS.page_id </code>, <code>JSON_TAGS.line_id </code>, and
	 * <code> JSON_TAGS.pos_id </code> correspondingly
	 * @return an array of ordered records 
	 */
	public ArrayList<InvoiceRecord> order(){
		if(invoice != null) return invoice;
		
		invoice = new ArrayList<InvoiceRecord>();
		InvoiceRecord nextRecord = null;
		while( (nextRecord = next()) != null ){
			invoice.add(nextRecord);
		}
		
		Collections.sort(invoice);
		
		return invoice;
	}
	
	public ArrayList<String> parse(){
		ArrayList<InvoiceRecord> invoiceRecords = order();
		int invoiceSize = (Integer)invoiceRecords.get(invoiceRecords.size() - 1).get(JSON_TAGS.line_id);
		ArrayList<String> parsedInvoice = new ArrayList<String>(invoiceSize);
		int lineNumber = 0;
		StringBuilder currentString = new StringBuilder();
		for(int i = 0; i < invoiceRecords.size(); ++i){
			InvoiceRecord record = invoiceRecords.get(i);
			if( (Integer)record.get(JSON_TAGS.line_id) != lineNumber){
				parsedInvoice.add(currentString.toString());
				currentString.setLength(0);
				lineNumber = (Integer)record.get(JSON_TAGS.line_id);
			}
			if(currentString.length() > 0) currentString.append(' ');
			currentString.append(record.get(JSON_TAGS.word));
		}
		return parsedInvoice;
	}
}
