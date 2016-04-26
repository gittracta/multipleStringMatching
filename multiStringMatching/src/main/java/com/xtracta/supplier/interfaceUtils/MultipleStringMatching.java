package com.xtracta.supplier.interfaceUtils;

public interface MultipleStringMatching{
	/**
	 * include the given <code> String </code> in future searches
	 * @param keyword string to add to the searchable patterns
	 */

	public void addKeyword(String keyword);
	/**
	 * build the searchable data-structure from the set of added keywords and readies it 
	 * for search
	 */

	public void buildTrie();
	
	/**
	 * finds the first match of the data-structure searchable patterns in the given text
	 * @param textToSearch text to search for given patterns
	 * @return the first found pattern in the text, or <code> null </code> otherwise
	 */

	public String findMatch(String textToSearch);

}
