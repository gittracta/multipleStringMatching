package com.xtracta.supplier.invoiceParser;

import java.util.EnumMap;

import org.json.simple.JSONObject;


public class InvoiceRecord extends EnumMap<JSON_TAGS, Object> implements Comparable<InvoiceRecord>{

	
	public InvoiceRecord(JSONObject jsonObject) {
		super(new EnumMap<JSON_TAGS, Object>(JSON_TAGS.class));
		
		put(JSON_TAGS.word, jsonObject.get(JSON_TAGS.word.name()));
		put(JSON_TAGS.page_id, jsonObject.get(JSON_TAGS.page_id.name()));
		put(JSON_TAGS.line_id, jsonObject.get(JSON_TAGS.line_id.name()));
		put(JSON_TAGS.pos_id, jsonObject.get(JSON_TAGS.pos_id.name()));

	}

	public int compareTo(InvoiceRecord rec) {
		if( (Long)get(JSON_TAGS.page_id) > (Long)rec.get(JSON_TAGS.page_id) ) return 1;
		if( (Long)get(JSON_TAGS.page_id) < (Long)rec.get(JSON_TAGS.page_id) ) return -1;

		if( (Long)get(JSON_TAGS.line_id) > (Long)rec.get(JSON_TAGS.line_id) ) return 1;
		if( (Long)get(JSON_TAGS.line_id) < (Long)rec.get(JSON_TAGS.line_id) ) return -1;
		
		if( (Long)get(JSON_TAGS.pos_id) > (Long)rec.get(JSON_TAGS.pos_id) ) return 1;
		if( (Long)get(JSON_TAGS.pos_id) < (Long)rec.get(JSON_TAGS.pos_id) ) return -1;

		return 0;

	}
	

}


