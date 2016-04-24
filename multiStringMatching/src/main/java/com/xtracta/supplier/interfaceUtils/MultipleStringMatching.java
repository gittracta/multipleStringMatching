package com.xtracta.supplier.interfaceUtils;

public interface MultipleStringMatching{
	public void addKeyword(String keyword);
	
	public void buildTrie();
	
	public String findMatch(String textToSearch);

}
