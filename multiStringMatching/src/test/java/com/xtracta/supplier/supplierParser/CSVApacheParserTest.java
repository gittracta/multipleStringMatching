package com.xtracta.supplier.supplierParser;

import org.junit.Assert;

import java.io.IOException;

import org.junit.Test;

public class CSVApacheParserTest {
	@Test
	public void canParseFirstLineOfSuppliers(){
		try {
			CSVApacheParser parser = new CSVApacheParser("/home/firas/xtracta/multiStringMatching/instructions/suppliernames.txt");
			Assert.assertTrue(parser.hasNext());
			Assert.assertEquals("Blue NRG Pty Ltd", parser.getNextSupplierName());
			
		} catch (IOException e) {
			Assert.fail("unexpected exception: " + e.getMessage());
		}
	}
	@Test
	public void canParseWholeFile(){
		try {
			CSVApacheParser parser = new CSVApacheParser("/home/firas/xtracta/multiStringMatching/instructions/suppliernames.txt");

			int lineCount = 0;
			for(;parser.hasNext(); ++lineCount) parser.getNextSupplierName();
			Assert.assertEquals(92, lineCount);
			
		} catch (IOException e) {
			Assert.fail("unexpected exception: " + e.getMessage());
		}
	}
}
