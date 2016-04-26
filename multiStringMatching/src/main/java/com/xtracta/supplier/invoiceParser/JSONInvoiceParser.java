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
	
	/**
	 * Parsing the invoice is done by utilizing the <a href="https://github.com/fangyidong/json-simple"> JSON-simple library </a>
	 * The format of the invoice is almost JSON-compatible, with the one exception 
	 * that the fields are wrapped by single quote "'" instead of double quote
	 * '"' as required by the JSON standard. Hence, single quotes are 
	 * replaced with double quotes before passing the reconds to the 
	 * JSON parser. This results in the limitation that supplier names
	 * cannot contain a single quote
	 * @param filePath parse the file at the given path
	 * @throws IOException in case of IO errors
	 */
	public JSONInvoiceParser(String filePath) throws IOException{
		br = new BufferedReader(new FileReader(filePath));
	}
	/**
	 * Gets next record from invoice file. The records are assumed to 
	 * of JSON format, up to having a single quote "'" around their fields
	 * rather than double quotes '"'.
	 * <p/>
	 * To enable the JSON library to parse the records, the single quotes 
	 * are replaced with double quotes.
	 * <p/>
	 * This results in the limitation that supplier names cannot have
	 * single quotes in them
	 * 
	 * @return the next record or <code> null </code> otherwise
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
		long invoiceSize = (Long)invoiceRecords.get(invoiceRecords.size() - 1).get(JSON_TAGS.line_id);
		ArrayList<String> parsedInvoice = new ArrayList<String>( (int)invoiceSize);
		long lineNumber = 0;
		StringBuilder currentString = new StringBuilder();
		for(int i = 0; i < invoiceRecords.size(); ++i){
			InvoiceRecord record = invoiceRecords.get(i);
			if( (Long)record.get(JSON_TAGS.line_id) != lineNumber){
				parsedInvoice.add(currentString.toString());
				currentString.setLength(0);
				lineNumber = (Long)record.get(JSON_TAGS.line_id);
			}
			if(currentString.length() > 0) currentString.append(' ');
			currentString.append(record.get(JSON_TAGS.word));
		}
		if(currentString.length() > 0) parsedInvoice.add(currentString.toString());
		return parsedInvoice;
	}
}
