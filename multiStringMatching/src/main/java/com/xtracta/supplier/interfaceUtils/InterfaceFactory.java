package com.xtracta.supplier.interfaceUtils;

import java.io.IOException;

import com.xtracta.supplier.multipleStringMatching.AhoCorasickMultipleStringMatchingRobertBorImpl;
import com.xtracta.supplier.supplierParser.CSVApacheParser;

public class InterfaceFactory {

	static public MultipleStringMatching getDefaultMultipleStringMatching(){
		return new AhoCorasickMultipleStringMatchingRobertBorImpl();
	}
	
	static public Suppliers getDefaultSupplierNames(String filePath) throws IOException{
		return new CSVApacheParser(filePath);
	}
}
