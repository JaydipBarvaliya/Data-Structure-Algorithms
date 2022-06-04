package com.datastructures.recursion;

import com.util.LogUtil;

/**
 * @author neeraj on 22/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class TowerOfHanoi {

    static int TOTAL_STEPS = 0;

    public static void main(String[] args) {
        TOTAL_STEPS = 0;
        move(2, 'A', 'B', 'C');
        LogUtil.logIt("Total Steps needed to solve Tower of Hanoi problem for 2 disc " + TOTAL_STEPS);
        TOTAL_STEPS = 0;
        move(3, 'A', 'B', 'C');
        LogUtil.logIt("Total Steps needed to solve Tower of Hanoi problem for 3 disc " + TOTAL_STEPS);
    }

    public static void move(int noOfDisc, char source, char helper, char destination) {
        // Base condition
        if (noOfDisc == 0) {
            return;
        }

        // 1) Now we will first place all n-1th disc on helper
        // 2) Move last disc on destination
        // 3) then move all n-1ith disc from helper to destination
        move(noOfDisc - 1, source, destination, helper);

        TOTAL_STEPS++;
        System.out.println("Moving disc " + noOfDisc + " from " + source + " to " + destination);

        move(noOfDisc - 1, helper, source, destination);
    }

}
