package com.leetcode.year_2020.trie;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neeraj on 27/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
@SuppressWarnings("ALL")
public class ImplementATrie {

    TrieNode root;

    public static void main(String[] args) {
        ImplementATrie trie = new ImplementATrie();
        // Input keys (use only 'a' through 'z' and lower case)
        String keys[] = {"the", "a", "there", "answer", "any",
                "by", "bye", "their"};

        for (String key : keys) {
            trie.insert(key);
        }

        for (String key : keys) {
            System.out.println("Key : " + key + " ==> exist in TrieNode ? " + trie.search(key));
        }

        String prefixes[] = {"th", "a", "thre", "ans", "an", "b", "by", "thei"};
        for (String prefix : prefixes) {
            System.out.println("Prefix : " + prefix + " ==> startsWith in TrieNode ? " + trie.startsWith(prefix));
        }

        keys = new String[]{"at", "ate", "bad", "bed", "beat", "beard", "cap", "capital", "caption", "capatian", "captain america"};
        for (String key : keys) {
            trie.insert(key);
        }
        System.out.println(trie.getAllWordsMatchingThePrefix("cap"));

        trie = new ImplementATrie();
        keys = new String[]{"this", "is", "a", "big", "string"};
        for (String key : keys) {
            trie.insert(key);
        }
        System.out.println(trie.search("yo"));
        System.out.println(trie.search("this"));
        System.out.println(trie.search("is"));
        System.out.println(trie.search("a"));
        System.out.println(trie.search("bigger"));
        System.out.println(trie.search("string"));
        System.out.println(trie.search("kappa"));

    }

    public ImplementATrie() {
        root = new TrieNode('#'); // DummyNode
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode curr = root;
        for (int level = 0; level < word.length(); level++) {
            char nextCharacter = word.charAt(level);

            int accessIndex = nextCharacter - 'a';
            if (Character.isWhitespace(nextCharacter)) {
                accessIndex = 26;
            }

            if (curr.adjacents[accessIndex] == null) {
                curr.adjacents[accessIndex] = new TrieNode(nextCharacter);
            }
            curr = curr.adjacents[accessIndex];
        }
        curr.endsWord = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode curr = root;
        for (int level = 0; level < word.length(); level++) {
            char nextCharacter = word.charAt(level);
            int accessIndex = nextCharacter - 'a';
            if (curr.adjacents[accessIndex] == null) return false;
            curr = curr.adjacents[accessIndex];
        }
        if (curr.endsWord) {
            return true;
        } else {
            return false;
        }
    }

    public List<String> getAllWordsMatchingThePrefix(final String prefix) {
        TrieNode curr = root;
        List<String> allMatchingPrefix = new ArrayList<>();
        if (startsWith(prefix)) {
            for (char c : prefix.toCharArray()) {
                int nextAccessIndex = c - 'a'; // to bring it under 0-25 index.
                curr = curr.adjacents[nextAccessIndex];
            }

            // Now we have reached to a point where prefix ends.... now from this point we have to basically
            // colled and return all words...
            collectAllWords(curr, prefix, allMatchingPrefix);

        } // Else if no such prefix we will not send anyting.
        return allMatchingPrefix;
    }

    private void collectAllWords(TrieNode curr, String word, List<String> allMatchingPrefix) {
        if (curr == null) return;
        if (curr.endsWord) {
            allMatchingPrefix.add(word);
        }

        for (int i = 0; i < curr.adjacents.length; i++) {
            if (curr.adjacents[i] != null) {
                collectAllWords(curr.adjacents[i], word + curr.adjacents[i].character, allMatchingPrefix);
            }
        }
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for (int level = 0; level < prefix.length(); level++) {
            char nextCharacter = prefix.charAt(level);
            int accessIndex = nextCharacter - 'a';
            if (curr.adjacents[accessIndex] == null) return false;
            curr = curr.adjacents[accessIndex];
        }
        return true;
    }

    private class TrieNode {
        char character;
        TrieNode[] adjacents;
        boolean endsWord;

        public TrieNode(char character) {
            this.character = character;
            this.adjacents = new TrieNode[27]; // This last index will be used to store TrieNode representing space.
            this.endsWord = false;
        }
    }
}


