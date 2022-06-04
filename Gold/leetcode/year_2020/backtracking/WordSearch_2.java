package com.leetcode.year_2020.backtracking;

import java.util.*;

/**
 * https://leetcode.com/problems/word-search-ii/
 *
 * @author neeraj on 22/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class WordSearch_2 {

    static TrieNode root;

    public static void main(String[] args) {
        System.out.println(findWords(new char[][]{
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        }, new String[]{"oath", "pea", "eat", "rain"}));

        System.out.println(findWords(new char[][]{
                {'a', 'b'}
        }, new String[]{"ba"}));

        System.out.println(findWords(new char[][]{
                {'t', 'h', 'i', 's', 'i', 's', 'a'},
                {'s', 'i', 'm', 'p', 'l', 'e', 'x'},
                {'b', 'x', 'x', 'x', 'x', 'e', 'b'},
                {'x', 'o', 'g', 'g', 'l', 'x', 'o'},
                {'x', 'x', 'x', 'd', 't', 'r', 'a'},
                {'r', 'e', 'p', 'e', 'a', 'd', 'x'},
                {'x', 'x', 'x', 'x', 'x', 'x', 'x'},
                {'n', 'o', 't', 'r', 'e', 'r', 'e'},
                {'x', 'd', 'e', 't', 'a', 'e', 'p'},

        }, new String[]{"this", "is", "not", "a", "simple", "boggle", "board", "test", "repeated", "notrerepeated"}));
    }

    static class TrieNode {
        char data;
        TrieNode[] adjacents;
        boolean endsWord;

        public TrieNode(char item) {
            this.data = item;
            this.adjacents = new TrieNode[26];
            this.endsWord = false;
        }

        public boolean insert(String data) {
            TrieNode temp = root;
            for (char c : data.toCharArray()) {
                char nextCharacter = c;
                int accessIndex = nextCharacter - 'a';
                if (temp.adjacents[accessIndex] == null) {
                    temp.adjacents[accessIndex] = new TrieNode(c);
                }
                temp = temp.adjacents[accessIndex];
            }
            temp.endsWord = true;
            return true;
        }

        public boolean search(String prefix) {
            TrieNode temp = root;
            for (char c : prefix.toCharArray()) {
                char nextCharacter = c;
                int accessIndex = nextCharacter - 'a';
                if (temp.adjacents[accessIndex] == null) {
                    return false;
                }
                temp = temp.adjacents[accessIndex];
            }
            return temp.endsWord == true;
        }

        public boolean startsWith(String prefix) {
            TrieNode temp = root;
            for (char c : prefix.toCharArray()) {
                char nextCharacter = c;
                int accessIndex = nextCharacter - 'a';
                if (temp.adjacents[accessIndex] == null) {
                    return false;
                }
                temp = temp.adjacents[accessIndex];
            }
            return true;
        }
    }

    static Set<String> res = new HashSet<String>();

    public static List<String> findWords(char[][] board, String[] words) {
        res = new HashSet<String>();
        if (words.length == 0) return Collections.EMPTY_LIST;
        root = new TrieNode('#');
        for (String word : words) {
            root.insert(word);
        }

        // Now we have a trie so let's just simply do the DFS and reject the ones
        // whose prefix(starting with) are not present in the dictionary.
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                findWords(i, j, board, visited, "");
            }
        }
        return new ArrayList<>(res);
    }

    private static void findWords(int i, int j, char[][] board, boolean[][] visited, String current) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j]) return;

        current += board[i][j];

        // we can reject if there is no such prefix in the dictionary.
        if (!root.startsWith(current)) return;

        if (root.search(current)) {
            res.add(current);
        }


        visited[i][j] = true;
        // Go in all 4 directions
        findWords(i - 1, j, board, visited, current); // Top
        findWords(i, j + 1, board, visited, current); // Right
        findWords(i + 1, j, board, visited, current); // Bottom
        findWords(i, j - 1, board, visited, current); // Left
        visited[i][j] = false;
    }
}
