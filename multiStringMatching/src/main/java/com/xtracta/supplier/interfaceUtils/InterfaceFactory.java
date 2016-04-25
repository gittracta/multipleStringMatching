package com.xtracta.supplier.interfaceUtils;

import java.io.IOException;

import com.xtracta.supplier.invoiceParser.JSONInvoiceParser;
import com.xtracta.supplier.multipleStringMatching.AhoCorasickMultipleStringMatchingRobertBorImpl;
import com.xtracta.supplier.supplierParser.CSVApacheParser;

public class InterfaceFactory {

	static public MultipleStringMatching getDefaultMultipleStringMatching(){
		return new AhoCorasickMultipleStringMatchingRobertBorImpl();
	}
	
	static public Suppliers getDefaultSuppliers(String supplierPath) throws IOException{
		return new CSVApacheParser(supplierPath);
	}
	
	static public Invoice getDetaultInvoice(String invoicePath){
		return new JSONInvoiceParser(invoicePath);
	}
}
