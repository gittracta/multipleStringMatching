package com.xtracta.supplier.supplierParser;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Iterator;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
/**
 * Uses CSV apache to parse CSV supplier file. The parsing is done iteratively to avoid reading a big file into memory
 * @see <a href="https://commons.apache.org/proper/commons-csv/">Commons CSV</a>
 * @author firas
 *
 */
public class CSVApacheParser extends AbstractSupplierParser {


	private static final String SUPPLIER_NAME = "SupplierName";
	private Iterator<CSVRecord> iterator;

	/**
	 * Reads the supplier names from the given CSV file.
	 * The parsing is done using <a href="https://commons.apache.org/proper/commons-csv/"> Apache Commons CSV</a>
	 * @param filePath path to the CSV file
	 * @throws IOException in case of error parsing the file
	 */
	public CSVApacheParser(String filePath) throws IOException{
		Reader in = new FileReader(filePath);
		CSVParser parser = CSVFormat.DEFAULT.withHeader().parse(in);
		iterator = parser.iterator();		
		
	}

	public boolean hasNext(){
		return iterator.hasNext();
	}
	
	public String getNextSupplierName() {
		return iterator.next().get(SUPPLIER_NAME);
	}
}
