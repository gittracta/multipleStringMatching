package com.xtracta.supplier.multipleStringMatching;

import org.ahocorasick.trie.Emit;
import org.ahocorasick.trie.Trie;
import org.ahocorasick.trie.Trie.TrieBuilder;

/**
 * the problem of multiple string matching (or string searching with a finite set of patterns) is well studied and was tackled 
 * using different algorithms, including Aho–Corasick, Commentz-Walter, and Rabin–Karp.
 * 
 * The Aho-Corasick approach is similar to a dictionary-matching algorithm that simultaneously searches the text for multiple string patterns.
 * The algorithm utilizes clever links between the trie's internal nodes for fast transition on failed string matches to avoid the expensive
 * backtracking
 * 
 * In addition to the time and memory complexity, the ability to implement the algorithm to run on multi-threaded, multi-core, and 
 * computer-clusters is important.
 * 
 * Based on a <a href="http://www.ijorcs.org/uploads/archive/Vol2-Issue-06-04-commentz-walter-any-better-than-aho-corasick-for-peptide-identification.pdf"> comparison study</a>,
 * the Aho-Corasick algorithm was picked with the multi-threaded particular implementation given <a href="https://github.com/robert-bor/aho-corasick"> here</a> 
 * 
 * @see <a href="http://www.ijorcs.org/uploads/archive/Vol2-Issue-06-04-commentz-walter-any-better-than-aho-corasick-for-peptide-identification.pdf"> comparison study</a>
 * @see <a href="http://ieeexplore.ieee.org/xpl/login.jsp?tp=&arnumber=5928330&url=http%3A%2F%2Fieeexplore.ieee.org%2Fxpls%2Fabs_all.jsp%3Farnumber%3D5928330"> parallel implementations</a>
 * @see <a href="https://github.com/robert-bor/aho-corasick"> picked Aho-Corasick implementation</a>
 * @see <a href="https://github.com/raymanrt/aho-corasick"> Annother Aho-Corasick implementation</a>
 * @author firas
 *
 */
public class AhoCorasickMultipleStringMatchingRobertBorImpl extends
		AbstractMultipleStringMatching {

	private Trie trie;
	private TrieBuilder trieBuilder;

	/**
	 * Constructs the underlying data-structure.
	 * The searches are done without reporting on overlaps and on whole words including white spaces
	 */
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
