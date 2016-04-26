package com.xtracta.supplier.invoiceParser;

import org.junit.Assert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.EnumMap;

import org.junit.Test;

import com.xtracta.supplier.XtractaTest;
import com.xtracta.supplier.invoiceParser.InvoiceRecord;
import com.xtracta.supplier.invoiceParser.JSONInvoiceParser;
import com.xtracta.supplier.invoiceParser.JSON_TAGS;

public class JSONInvoiceParserTest extends XtractaTest{
    @Test
    public void canParseFirstLineOfInvoice() {
    	JSONInvoiceParser parser;
		try {
			parser = new JSONInvoiceParser(TEST_INVOICE_FILE);
	    	EnumMap<JSON_TAGS, Object> record = parser.next();
	    	
	        Assert.assertEquals( (long)0, record.get(JSON_TAGS.pos_id));
	        Assert.assertEquals( "(PI", record.get(JSON_TAGS.word));
	        Assert.assertEquals((long) 0, record.get(JSON_TAGS.line_id));
	        Assert.assertEquals((long) 1, record.get(JSON_TAGS.page_id));

		} catch (IOException e) {
			Assert.fail("Unexpected exception: " + e.getMessage());
		}

    }
    @Test
    public void canParseWholeInvoice(){
    	JSONInvoiceParser parser;
		try {
			parser = new JSONInvoiceParser(TEST_INVOICE_FILE);
	    	int lineCount = 0;
	    	for(;parser.next() != null; ++lineCount);
	    	Assert.assertEquals(143, lineCount);
		} catch (IOException e) {
			Assert.fail("Unexpected exception: " + e.getMessage());
		}

    }
    @Test
    public void canOrder(){
    	JSONInvoiceParser parser;
		try {
			parser = new JSONInvoiceParser(TEST_INVOICE_FILE);
	    	ArrayList<InvoiceRecord> invoice = parser.order();
	    	InvoiceRecord record = invoice.get(6);
	    	Assert.assertEquals( (long)2, record.get(JSON_TAGS.line_id));
	    	Assert.assertEquals( (long)0, record.get(JSON_TAGS.pos_id));

		} catch (IOException e) {
			Assert.fail("Unexpected exception: " + e.getMessage());
		}
    }
    @Test 
    public void canParse(){
    	JSONInvoiceParser parser;
		try {
			parser = new JSONInvoiceParser(TEST_INVOICE_FILE);
	    	ArrayList<String> invoice = parser.parse();
	    	Assert.assertEquals("(PI INVOICE", invoice.get(0));
	    	Assert.assertEquals("An invoice footer can go here", invoice.get(invoice.size() - 1));
		} catch (IOException e) {
			Assert.fail("Unexpected exception: " + e.getMessage());
		}
    }
}
