package com.xtracta.supplier.invoiceParser;

import java.util.EnumMap;

import org.json.simple.JSONObject;

/**
 * Represents an invoice record as an <code> EnumMap </code> between tags as defined in {@link JSON_TAGS}
 * and corresponding values
 * @author firas
 *
 */
public class InvoiceRecord extends EnumMap<JSON_TAGS, Object> implements Comparable<InvoiceRecord>{

	/**
	 * Constructs an invoice record from the given JSON object that contains all the tags defined in
	 * {@link JSON_TAGS}
	 * @param jsonObject containing the needed values corresponding to the {@link JSON_TAGS} 
	 */
	public InvoiceRecord(JSONObject jsonObject) {
		super(new EnumMap<JSON_TAGS, Object>(JSON_TAGS.class));
		
		put(JSON_TAGS.word, jsonObject.get(JSON_TAGS.word.name()));
		put(JSON_TAGS.page_id, jsonObject.get(JSON_TAGS.page_id.name()));
		put(JSON_TAGS.line_id, jsonObject.get(JSON_TAGS.line_id.name()));
		put(JSON_TAGS.pos_id, jsonObject.get(JSON_TAGS.pos_id.name()));
	}

	/**
	 * comparator to determine the order of records upon sorting
	 * @param rec to compare this <code> InvoiceRecord </code> to
	 * @return positive integer if this record is greater than <code> rec </code>, a negative integer if 
	 * 			this record is less than <code> rec </code>, and zero in case of equality
	 */
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


