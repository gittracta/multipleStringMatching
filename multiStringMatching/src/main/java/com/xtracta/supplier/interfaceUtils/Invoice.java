package com.xtracta.supplier.interfaceUtils;

import java.util.ArrayList;



public interface Invoice {

	/**
	 * parse the invoice into an ordered array based on line numbers 
	 * so that each invoice line corresponds to a <code> String </code>
	 * @return an ordered array of the invoice lines
	 */
	public ArrayList<String> parse();
}
