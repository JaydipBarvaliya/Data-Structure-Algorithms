package com.datastructures.recursion;

import com.util.LogUtil;

/**
 * https://practice.geeksforgeeks.org/problems/game-of-death-in-a-circle/0
 *
 * @author neeraj on 30/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class GameOfDeathInACircle {

    public static void main(String[] args) {
        findSafePosition(5, 2);
        findSafePosition(40, 7);
    }

    static int safePosition = 0;

    public static int findSafePosition(int totalPerson, int kthPersonToBeKilled) {
        safePosition = 0;
        int[] persons = new int[totalPerson];
        for (int i = 0; i < totalPerson; i++) {
            persons[i] = i + 1;
        }
        solve(persons, 0, kthPersonToBeKilled - 1); // Why -1 since we start counting from first index itself.
        LogUtil.logIt("Safe Position when n = " + totalPerson + " and we kill every k = " + kthPersonToBeKilled + "  is " + safePosition);
        return safePosition;
    }

    private static void solve(int[] persons, int startingIndex, int kthPersonToBeKilled) {
        if (persons.length == 1) {
            safePosition = persons[0];
            return;
        }

        startingIndex = (startingIndex + kthPersonToBeKilled) % persons.length;
        int[] afterPersonOnKthPositionIsKilled = killPerson(persons, startingIndex);
        solve(afterPersonOnKthPositionIsKilled, startingIndex, kthPersonToBeKilled);
    }

    private static int[] killPerson(int[] persons, int startingIndex) {
        int[] afterPersonOnKthPositionIsKilled = new int[persons.length - 1]; // Why -1 since we are killing that 1 person
        int counter = 0;
        for (int i = 0; i < persons.length; i++) {
            if (i == startingIndex) continue;
            afterPersonOnKthPositionIsKilled[counter++] = persons[i];
        }
        return afterPersonOnKthPositionIsKilled;
    }
}
