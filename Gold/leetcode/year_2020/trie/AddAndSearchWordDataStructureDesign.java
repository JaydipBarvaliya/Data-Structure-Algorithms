package com.leetcode.year_2020.trie;


/**
 * https://leetcode.com/problems/add-and-search-word-data-structure-design/
 *
 * @author neeraj on 05/08/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class AddAndSearchWordDataStructureDesign {

    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();
//        wordDictionary.addWord("bad");
//        wordDictionary.addWord("dad");
//        wordDictionary.addWord("mad");
//
//
//        System.out.println(wordDictionary.search("bad"));
//        System.out.println(wordDictionary.search("dad"));
//        System.out.println(wordDictionary.search("mads"));
//        System.out.println(wordDictionary.search(".ad"));
//        System.out.println(wordDictionary.search("b.."));


        wordDictionary.addWord("at");
        wordDictionary.addWord("and");
        wordDictionary.addWord("an");
        wordDictionary.addWord("add");

        System.out.println(wordDictionary.search(".a"));
        System.out.println(wordDictionary.search(".at"));
        wordDictionary.addWord("bat");

        System.out.println(wordDictionary.search(".at"));
        System.out.println(wordDictionary.search("an."));
        System.out.println(wordDictionary.search("a.d."));
        System.out.println(wordDictionary.search("b."));
        System.out.println(wordDictionary.search("a.d"));
        System.out.println(wordDictionary.search("."));
    }

    static class WordDictionary {

        TrieNode head;

        static class TrieNode {
            String val = "";
            TrieNode[] adjacents = new TrieNode[26];
        }

        /**
         * Initialize your data structure here.
         */
        public WordDictionary() {
            head = new TrieNode();
        }

        private boolean exist(char[] word, int pointer, TrieNode node) {
            if (pointer == word.length) return !node.val.equals("");
            if (word[pointer] != '.') {
                return node.adjacents[word[pointer] - 'a'] != null && exist(word, pointer + 1, node.adjacents[word[pointer] - 'a']);
            } else {
                for (int i = 0; i < node.adjacents.length; i++) {
                    if (node.adjacents[i] != null) {
                        if (exist(word, pointer + 1, node.adjacents[i])) {
                            return true;
                        }
                    }
                }
            }
            return false;
        }

        /**
         * Adds a word into the data structure.
         */
        public void addWord(String word) {
            TrieNode curr = head;
            for (char c : word.toCharArray()) {
                if (curr.adjacents[c - 'a'] == null) {
                    curr.adjacents[c - 'a'] = new TrieNode();
                }
                curr = curr.adjacents[c - 'a'];
            }
            curr.val = word;
        }

        /**
         * Returns if the word is in the data structure. A word could contain the dot character '.' to represent any one letter.
         */
        public boolean search(String word) {
            return exist(word.toCharArray(), 0, head);
        }
    }

}
