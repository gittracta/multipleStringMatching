package com.xtracta.supplier.interfaceUtils;


public interface Suppliers {
	/**
	 * Check if there are more suppliers. This method should be always invoked before
	 * {@link #getNextSupplierName getNextSupplierName}
	 * @return <code> true </code> if there are more suppliers, and otherwise <code> false </code>
	 * @see #getNextSupplierName()
	 */
	public boolean hasNext();
	
	/**
	 * Gets the next supplier name. This method should be invoked only after 
	 * checking the existence of further supplier names using {@link #hasNext()}
	 * @return the next supplier name 
	 * @see #hasNext()
	 */
	public String getNextSupplierName();

}
