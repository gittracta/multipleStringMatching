package com.xtracta.supplier.supplierFinder;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;

import com.xtracta.supplier.interfaceUtils.InterfaceFactory;
import com.xtracta.supplier.interfaceUtils.Invoice;
import com.xtracta.supplier.interfaceUtils.Suppliers;
import com.xtracta.supplier.multipleStringMatching.AhoCorasickMultipleStringMatchingRobertBorImpl;

public class SupplierFinder {
	
	public String findSupplier(String invoiceFile, String supplierFile) throws IOException{
		AhoCorasickMultipleStringMatchingRobertBorImpl ahoCor = new AhoCorasickMultipleStringMatchingRobertBorImpl();

		Suppliers suppliers = InterfaceFactory.getDefaultSuppliers(supplierFile);
		Invoice invoice = InterfaceFactory.getDetaultInvoice(invoiceFile);

		while(suppliers.hasNext()){
			ahoCor.addKeyword(suppliers.getNextSupplierName());
		}
		ahoCor.buildTrie();

		ArrayList<String> invoiceContent = invoice.parse();

		for(String string : invoiceContent){
			String match = null;
			if( (match = ahoCor.findMatch(string)) != null ){
				return match;
			}
		}
		return null;
			
	}
}
