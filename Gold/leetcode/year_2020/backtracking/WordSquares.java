package com.leetcode.year_2020.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WordSquares {

    public static void main(String[] args) {
        solveWordSquares(Arrays.asList("area", "lead", "wall", "lady", "ball"));
        solveWordSquares(Arrays.asList("abat", "baba", "atan", "atal"));
    }

    public static void solveWordSquares(final List<String> words) {
        List<List<String>> wordSquares = new ArrayList<>();
        wordSquaresHelper(words, new ArrayList<>(), wordSquares);
        print(wordSquares);
    }

    private static void print(final List<List<String>> wordSquares) {
        System.out.println("[");
        for (final List<String> words : wordSquares) {
            System.out.println("\t[");
            for (String word : words) {
                System.out.println("\t\t" + word);
            }
            System.out.println("\t]");
        }
        System.out.println("]");
    }


    private static void wordSquaresHelper(final List<String> words,
                                          final ArrayList<String> current, final List<List<String>> all) {
        if (current.size() == words.get(0).length()) {
            all.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < words.size(); i++) {
            current.add(words.get(i));
            if (!isValidEntry(current)) {
                current.remove(current.size() - 1);
                continue;
            }
            wordSquaresHelper(words, current, all);
            current.remove(current.size() - 1);
        }
    }

    private static boolean isValidEntry(final ArrayList<String> current) {
        for (int i = 0; i < current.size(); i++) {
            final String row = getRow(current, i);
            final String col = getCol(current, i);
            if (!areEqual(row, col)) {
                return false;
            }
        }
        return true;
    }

    private static boolean areEqual(final String row, final String col) {
        if (row.length() < col.length()) return false;
        try {
            for (int i = 0; i < col.length(); i++) {
                if (row.charAt(i) != col.charAt(i)) {
                    return false;
                }
            }
            return true;
        } catch (Exception ex) {
            System.out.println("Failing for " + row + " and " + col);
            ex.printStackTrace();
            return false;
        }
    }


    private static String getCol(final ArrayList<String> current, final int colNumber) {
        final StringBuilder str = new StringBuilder();
        for (String word : current) {
            str.append(word.charAt(colNumber));
        }
        return str.toString();
    }

    private static String getRow(final ArrayList<String> current, final int rowNumber) {
        return current.get(rowNumber);
    }
}
