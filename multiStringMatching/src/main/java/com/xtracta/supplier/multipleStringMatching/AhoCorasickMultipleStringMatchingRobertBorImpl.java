package com.xtracta.supplier.multipleStringMatching;

import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Trie;
import org.ahocorasick.trie.Trie.TrieBuilder;

public class AhoCorasickMultipleStringMatchingRobertBorImpl extends
		AbstractMultipleStringMatching {

	private Trie trie;
	private TrieBuilder trieBuilder;

	public AhoCorasickMultipleStringMatchingRobertBorImpl(){
		trieBuilder = Trie.builder().removeOverlaps().onlyWholeWordsWhiteSpaceSeparated();
	}
	
	public void addKeyword(String keyword){
		trieBuilder.addKeyword(keyword);
	}
	
	public void buildTrie(){
		trie = trieBuilder.build();
	}
	
	public String findMatch(String textToSearch){
		Emit emit = trie.firstMatch(textToSearch);
		if(emit == null) return null;
		return emit.getKeyword();
	}
}
