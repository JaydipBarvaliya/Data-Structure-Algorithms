package com.leetcode.year_2020.trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FaultyKeyBoard {

    static TrieNode root;

    public static void main(String[] args) {
        String input = "i lik  to  xplor   univ rs ";
        Set<String> dictionary = new HashSet<>(Arrays.asList("like", "explore", "toe", "universe", "rse"));
        for (String str : validSentences(input, dictionary)) {
            System.out.println(str);
        }
    }

    static List<String> allSentences;

    public static List<String> validSentences(final String input,
                                              final Set<String> dictionary) {
        root = new TrieNode('#');
        allSentences = new ArrayList<>();
        buildTrieFromDictionary(dictionary);

        int maxLengthOfWordInDictionary = 0;
        for (final String word : dictionary) {
            maxLengthOfWordInDictionary = Math.max(maxLengthOfWordInDictionary, word.length());
        }
        bacTrack(input, 0, input.length(), "", maxLengthOfWordInDictionary);
        return allSentences;
    }

    private static void bacTrack(final String input, final int start, final int end, final String curr,
                                 final int maxLengthOfWordInDictionary) {
        if (start == end) {
            allSentences.add(curr);
            return;
        }

        bacTrack(input, start + 1, end, curr + input.charAt(start), maxLengthOfWordInDictionary);

        final List<Integer> jumpPositions = searchInTrie(input.toCharArray(), start, Math.min(end, start + maxLengthOfWordInDictionary));
        for (int i = 0; i < jumpPositions.size(); i++) {
            char[] newWord = input.substring(start, start + (jumpPositions.get(i) - start + 1)).toCharArray();
            for (int j = 0; j < newWord.length; j++) {
                if (newWord[j] == ' ') {
                    newWord[j] = 'e';
                }
            }
            bacTrack(input, jumpPositions.get(i) + 1, end, curr + new String(newWord), maxLengthOfWordInDictionary);
        }
    }

    private static void buildTrieFromDictionary(final Set<String> dictionary) {
        for (final String word : dictionary) {
            insertIntoTrie(word);
        }
    }


    public static List<Integer> searchInTrie(final char[] word, int start, int end) {
        TrieNode curr = root;
        final List<Integer> positionsToJump = new ArrayList<>();
        for (int i = start; i < end; i++) {
            if (word[i] == ' ') word[i] = 'e'; // Replace on the fly

            if (curr.children[word[i] - 'a'] == null) {
                return positionsToJump;
            }
            curr = curr.children[word[i] - 'a'];
            if (curr.endsWord) {
                positionsToJump.add(i);
            }
        }
        return positionsToJump;
    }

    public static void insertIntoTrie(final String word) {
        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            int nextIndex = ch - 'a';
            if (curr.children[nextIndex] == null) {
                curr.children[nextIndex] = new TrieNode(ch);
            }
            curr = curr.children[nextIndex];
        }
        curr.endsWord = true;
    }

    static class TrieNode {
        char val;
        TrieNode[] children;
        boolean endsWord;

        public TrieNode(final char val) {
            this.val = val;
            children = new TrieNode[52];
        }
    }
}
