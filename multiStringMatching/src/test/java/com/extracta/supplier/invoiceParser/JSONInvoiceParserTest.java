package com.extracta.supplier.invoiceParser;

import org.junit.Assert;

import java.util.ArrayList;
import java.util.EnumMap;

import org.junit.Test;

import com.xtracta.supplier.invoiceParser.InvoiceRecord;
import com.xtracta.supplier.invoiceParser.JSONInvoiceParser;
import com.xtracta.supplier.invoiceParser.JSON_TAGS;

public class JSONInvoiceParserTest {
    @Test
    public void canParseFirstLineOfInvoice() {
    	JSONInvoiceParser parser = new JSONInvoiceParser("/home/firas/xtracta/multiStringMatching/instructions/invoice.txt");

    	EnumMap<JSON_TAGS, Object> record = parser.next();
    	
        Assert.assertEquals( (long)0, record.get(JSON_TAGS.pos_id));
        Assert.assertEquals( "(PI", record.get(JSON_TAGS.word));
        Assert.assertEquals((long) 0, record.get(JSON_TAGS.line_id));
        Assert.assertEquals((long) 1, record.get(JSON_TAGS.page_id));
    }
    @Test
    public void canParseWholeInvoice(){
    	JSONInvoiceParser parser = new JSONInvoiceParser("/home/firas/xtracta/multiStringMatching/instructions/invoice.txt");

    	int lineCount = 0;
    	for(;parser.next() != null; ++lineCount);
    	Assert.assertEquals(143, lineCount);
    }
    @Test
    public void canOrder(){
    	JSONInvoiceParser parser = new JSONInvoiceParser("/home/firas/xtracta/multiStringMatching/instructions/invoice.txt");
    	ArrayList<InvoiceRecord> invoice = parser.order();
    	InvoiceRecord record = invoice.get(6);
    	Assert.assertEquals( (long)2, record.get(JSON_TAGS.line_id));
    	Assert.assertEquals( (long)0, record.get(JSON_TAGS.pos_id));
    }
}
