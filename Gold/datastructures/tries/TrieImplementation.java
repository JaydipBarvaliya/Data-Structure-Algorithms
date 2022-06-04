package com.datastructures.tries;

/**
 * @author neeraj on 17/10/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
@SuppressWarnings("DuplicatedCode")
public class TrieImplementation {

    static TrieNode root;

    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        boolean isEndOfWord;

        TrieNode() {
            isEndOfWord = false;
            for (int i = 0; i < children.length; i++) {
                children[i] = null;
            }
        }
    }

    /**
     * Inserts a word into the TrieNode.
     */
    public static void insert(String str) {
        TrieNode temp = root;
        for (char c : str.toCharArray()) {
            int index = c - 'a';

            if (temp.children[index] == null) {
                temp.children[index] = new TrieNode();
            }
            temp = temp.children[index];
        }
        temp.isEndOfWord = true;
    }

    /**
     * Returns if the word is in the TrieNode.
     */
    public static boolean search(String key) {
        Boolean isPresent = true;
        TrieNode temp = root;
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - 'a';
            if (temp.children[index] == null) {
                isPresent = false;
                break;
            }
            temp = temp.children[index];
        }
        if (!temp.isEndOfWord) {
            isPresent = false;
        }
        return isPresent;
    }

    /**
     * Returns if there is any word in the TrieNode that starts with the given prefix.
     */
    public static boolean startsWith(String key) {
        Boolean isPresent = true;
        TrieNode temp = root;
        for (int i = 0; i < key.length(); i++) {
            int index = key.charAt(i) - 'a';
            if (temp.children[index] == null) {
                isPresent = false;
                break;
            }
            temp = temp.children[index];
        }
        return isPresent;
    }

    public static void main(String[] args) {
        // Input keys (use only 'a' through 'z' and lower case)
        String keys[] = {"the", "a", "there", "answer", "any",
                "by", "bye", "their"};

        root = new TrieNode();
        for (String key : keys) {
            insert(key);
        }

        for (String key : keys) {
            System.out.println("Key : " + key + " ==> exist in TrieNode ? " + startsWith(key));
        }
    }

}
