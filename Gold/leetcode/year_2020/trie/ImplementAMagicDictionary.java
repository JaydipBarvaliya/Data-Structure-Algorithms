package com.leetcode.year_2020.trie;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ImplementAMagicDictionary {

    static class MagicDictionary {

        MagicTrieNode root;

        static class MagicTrieNode {
            char val;
            MagicTrieNode[] children;
            boolean endsWord;

            public MagicTrieNode(final char val) {
                this.val = val;
                children = new MagicTrieNode[26];
            }
        }

        public void insertIntoTrie(final String word) {
            MagicTrieNode curr = root;
            for (char ch : word.toCharArray()) {
                int nextIndex = ch - 'a';
                if (curr.children[nextIndex] == null) {
                    curr.children[nextIndex] = new MagicTrieNode(ch);
                }
                curr = curr.children[nextIndex];
            }
            curr.endsWord = true;
        }

        public boolean searchTrie(final String word) {
            MagicTrieNode curr = root;
            for (int i = 0; i < word.length(); i++) {
                char ch = word.charAt(i);
                int nextIndex = ch - 'a';
                for (char val = 'a'; val <= 'z'; val++) {
                    if (val == ch || curr.children[val - 'a'] == null) continue;
                    if (trieSearchHelper(curr, val + word.substring(i + 1))) {
                        return true;
                    }
                }
                if (curr.children[nextIndex] == null) {
                    return false;
                }
                curr = curr.children[nextIndex];
            }
            return false;
        }

        private boolean trieSearchHelper(MagicTrieNode tempNode, final String remainingWord) {
            for (char ch : remainingWord.toCharArray()) {
                int nextIndex = ch - 'a';
                if (tempNode.children[nextIndex] == null) {
                    return false;
                }
                tempNode = tempNode.children[nextIndex];
            }
            return tempNode.endsWord;
        }

        /**
         * Initialize your data structure here.
         */
        public MagicDictionary() {
            root = new MagicTrieNode('#');
        }

        public void buildDict(String[] dictionary) {
            for (String word : dictionary) {
                insertIntoTrie(word);
            }
        }

        public boolean search(String word) {
            return searchTrie(word);
        }
    }

    class MagicDictionaryUsingMap {

        private Map<Integer, List<String>> wordsGroupedByLength;

        public MagicDictionaryUsingMap() {
            wordsGroupedByLength = new HashMap<>();
        }

        public void buildDict(String[] dictionary) {
            for (String word : dictionary) {
                int len = word.length();
                wordsGroupedByLength.putIfAbsent(len, new LinkedList<>());
                wordsGroupedByLength.get(len).add(word);
            }
        }

        public boolean search(String searchWord) {
            int len = searchWord.length();
            // If lengths are different kick them out.
            if (!wordsGroupedByLength.containsKey(len)) return false;
            for (String wordInDict : wordsGroupedByLength.get(len)) {
                int count = 0;
                for (int i = 0; i < len; i++) {
                    if (wordInDict.charAt(i) != searchWord.charAt(i)) {
                        count++;
                        if (count > 1) break;
                    }
                }
                if (count == 1) {
                    return true;
                }
            }
            return false;
        }
    }


    public static void main(String[] args) {
        MagicDictionary magicDictionary = new MagicDictionary();
        magicDictionary.buildDict(new String[]{"hello", "leetcode"});

        magicDictionary.searchTrie("hello");

        final List<String> wordsToSearch = Arrays.asList("hello", "hallo", "hhllo", "hell", "leetcoded");
        for (String word : wordsToSearch) {
            System.out.println("Is word [" + word + "] present in magic dictionary ? " + magicDictionary.search(word));
        }
    }
}
