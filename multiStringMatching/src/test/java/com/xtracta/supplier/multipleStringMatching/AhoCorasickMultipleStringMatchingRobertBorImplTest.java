package com.xtracta.supplier.multipleStringMatching;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;

import com.xtracta.supplier.XtractaTest;
import com.xtracta.supplier.interfaceUtils.InterfaceFactory;
import com.xtracta.supplier.interfaceUtils.Invoice;
import com.xtracta.supplier.interfaceUtils.Suppliers;

public class AhoCorasickMultipleStringMatchingRobertBorImplTest extends XtractaTest{
	@Test
	public void canFindMatch(){
		AhoCorasickMultipleStringMatchingRobertBorImpl ahoCor = new AhoCorasickMultipleStringMatchingRobertBorImpl();
		ahoCor.addKeyword("Xtracta Ltd");
		ahoCor.addKeyword("Jonathan Spencer");
		ahoCor.addKeyword("Ammar Mohemmed");
		ahoCor.addKeyword("Firas Swidan");
		
		ahoCor.buildTrie();
		
		Assert.assertNull(ahoCor.findMatch("mar M"));
		Assert.assertEquals("Xtracta Ltd", ahoCor.findMatch("Xtracta Ltd"));
		Assert.assertNull(ahoCor.findMatch("Xtractad Lsd"));
		Assert.assertEquals("Xtracta Ltd", ahoCor.findMatch("the company Xtracta Ltd is cool :)"));
	}
	@Test
	public void canHandleTestInvoice(){
		findSupplierInInvoice(TEST_SUPPLIER_FILE, TEST_INVOICE_FILE, "Demo Company");
		
	}
	@Test
	public void canHandleHugeSuppliers(){
		findSupplierInInvoice(TEST_HUGE_SUPPLIER_FILE, TEST_INVOICE_FILE, "Demo Company");		
	}
	@Test
	public void canHandleHugeRandomNonRepeatingSuppliers(){
		findSupplierInInvoice(TEST_HUGE_RANDOM_SUPPLIER_FILE, TEST_INVOICE2_FILE, "Xtracta Ltd");
	}
	
	private void findSupplierInInvoice(String supplierFile, String invoiceFile, String expectedMatch) {
		try {
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
					Assert.assertEquals(expectedMatch, match);
					return;
				}
			}
			Assert.fail();
			
			
		} catch (IOException e) {
			Assert.fail("unexpected exception: " + e.getMessage());
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
