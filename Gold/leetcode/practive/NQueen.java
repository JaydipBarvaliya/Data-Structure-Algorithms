package com.leetcode.practive;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neeraj on 2019-08-24
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class NQueen {

    public static void main(String[] args) {
        solveNQueens(4);
        solveNQueens(5);
    }
    public static List<List<String>> solveNQueens(int totalQueenToBePlaced) {
        List<List<String>> result = new ArrayList<>();
        int whichQueenIsBeingPlaced = 0;

        placeQueen(whichQueenIsBeingPlaced, totalQueenToBePlaced, new ArrayList<>(), result);
        System.out.println(result);
        return result;
    }

    private static void placeQueen(int whichQueenIsBeingPlaced,
                            int totalQueenToBePlaced,
                            List<Integer> currentPlacements,
                            List<List<String>> result) {
        if (whichQueenIsBeingPlaced == totalQueenToBePlaced) {
            List<String> placementInTheRow = new ArrayList<>();
            for (int i = 0; i < totalQueenToBePlaced; i++) {
                StringBuffer temp = new StringBuffer();
                for(int j=0;j<totalQueenToBePlaced;j++) {
                    if(j == currentPlacements.get(i)) {
                        temp.append("Q");
                    } else {
                        temp.append(".");
                    }
                }
                placementInTheRow.add(temp.toString());
            }
            result.add(placementInTheRow);
            return;
        }

        for (int i = 0; i < totalQueenToBePlaced; i++) {
            currentPlacements.add(i);
            if (isValidPlacements(currentPlacements)) {
                placeQueen(whichQueenIsBeingPlaced + 1, totalQueenToBePlaced,
                        currentPlacements, result);
            }
            currentPlacements.remove(currentPlacements.size() - 1);
        }
    }

    private static boolean isValidPlacements(List<Integer> currentPlacements) {
        // 2 things
        // If the column placements difference between the column we are trying to
        // find the valid and the current Queen we are iterating
        int rowWeAreValidating = currentPlacements.size() - 1;
        for (int queen = 0; queen < rowWeAreValidating; queen++) {
            int absoluteColumnDifference = Math.abs(currentPlacements.get(queen) - currentPlacements.get(rowWeAreValidating));

            // We are in same column the previous queen can attack me
            if (absoluteColumnDifference == 0) {
                return false;
            }
            if (absoluteColumnDifference == Math.abs(rowWeAreValidating - queen)) {
                return false;
            }
        }
        return true;
    }
}
