package com.leetcode.year_2020.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/map-sum-pairs/
 */
public class MapSumPairs {

    static class MapSum {

        private Map<String, Integer> mapSum;
        private TrieNode head;

        public MapSum() {
            mapSum = new HashMap<>();
            head = new TrieNode('#');
        }

        public void insert(String key, int val) {
            // Difference of newValue with oldValue
            // we will keep this difference at each node, so that sum method can simply traverse
            // the node and provide sum
            int difference = val - mapSum.getOrDefault(key, 0);
            TrieNode curr = head;
            for (char ch : key.toCharArray()) {
                ch -= 'a'; // only lower case letters
                if (curr.children[ch] == null) {
                    curr.children[ch] = new TrieNode(ch);
                }
                curr = curr.children[ch];
                curr.sum += difference;
            }
            mapSum.put(key, val);
        }

        public int sum(String prefix) {
            TrieNode curr = head;
            for (char ch : prefix.toCharArray()) {
                ch -= 'a'; // only lower case letters
                if (curr.children[ch] == null) {
                    return 0;
                }
                curr = curr.children[ch];
            }
            return curr.sum;
        }

        class TrieNode {
            char val;
            TrieNode[] children;
            int sum; // Store the sum at each node for the entire word

            TrieNode(char val) {
                this.val = val;
                children = new TrieNode[26];
            }
        }
    }

    public static void main(String[] args) {
        MapSum mapSum = new MapSum();
        mapSum.insert("apple", 3);
        System.out.println(mapSum.sum("ap"));
        mapSum.insert("app", 2);
        System.out.println(mapSum.sum("ap"));
    }
}
