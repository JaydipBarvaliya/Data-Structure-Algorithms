package com.leetcode.year_2020.DP.interview_prep;

import com.util.LogUtil;

/**
 * https://www.geeksforgeeks.org/assembly-line-scheduling-dp-34/
 * @author neeraj on 06/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class AssemblyLineScheduling {

    public static void main(String[] args) {
        int[][] timeSpentOnEachStation = new int[][]{{4, 5, 3, 2}, {2, 10, 1, 4}};
        int[][] penaltyOnChangingLine = new int[][]{{0, 7, 4, 5}, {0, 9, 2, 8}};
        int[] timeSpentOnEntryToAssemblyLine = new int[]{10, 12};
        int[] timeSpentOnExit = new int[]{18, 7};
        findMinCostOfAssemblyLineScheduling(timeSpentOnEachStation, penaltyOnChangingLine, timeSpentOnEntryToAssemblyLine, timeSpentOnExit);
    }

    public static int findMinCostOfAssemblyLineScheduling(int[][] timeSpentOnEachStation,
                                                          int[][] penaltyOnChangingLine,
                                                          int[] timeSpentOnEntryToAssemblyLine,
                                                          int[] timeSpentOnExit) {

        /**
         * Now at every ith Station we just need to know that what is the min
         * time spent before reaching to this station either from Assembly line 1
         * or 2 so that we can choose how much time will be required when i leave this station.
         */
        int[] timeWhenExitingAnyStationOfAssembly1 = new int[timeSpentOnEachStation[0].length];
        int[] timeWhenExitingAnyStationOfAssembly2 = new int[timeSpentOnEachStation[1].length];

        /**
         * Now for Assembly 1 station 1st the timeWhenExiting 1st station will be timeSpentOnEntryToAssemblyLine[0] + timeSpentOnEachStation[0][0].
         * Now for Assembly 2 station 1st the timeWhenExiting 1st station will be timeSpentOnEntryToAssemblyLine[1] + timeSpentOnEachStation[1][0].
         */
        timeWhenExitingAnyStationOfAssembly1[0] = timeSpentOnEntryToAssemblyLine[0] + timeSpentOnEachStation[0][0];
        timeWhenExitingAnyStationOfAssembly2[0] = timeSpentOnEntryToAssemblyLine[1] + timeSpentOnEachStation[1][0];

        for (int i = 1; i < timeSpentOnEachStation[0].length; i++) {

            /**
             * Now at any ith Station, time when exiting this station will be
             *  Max of
             * 1) TimeWhenExitingPrevious Station in same assembly line  + timeSpentOnThisIthStation)
             * 2)  TimeWhenExitingPrevious Station in "OPPOSITE" assembly line + Penalty to change the line  + timeSpentOnThisIthStation)
             */
            timeWhenExitingAnyStationOfAssembly1[i] = Math.min(
                    timeWhenExitingAnyStationOfAssembly1[i - 1] + timeSpentOnEachStation[0][i],
                    timeWhenExitingAnyStationOfAssembly2[i - 1] + penaltyOnChangingLine[1][i] + timeSpentOnEachStation[0][i]
            );

            timeWhenExitingAnyStationOfAssembly2[i] = Math.min(
                    timeWhenExitingAnyStationOfAssembly2[i - 1] + timeSpentOnEachStation[1][i],
                    timeWhenExitingAnyStationOfAssembly1[i - 1] + penaltyOnChangingLine[0][i] + timeSpentOnEachStation[1][i]
            );
        }

        /**
         * Now final Minimum Cost will be
         */
        int minTime = Math.min(
                timeWhenExitingAnyStationOfAssembly1[timeWhenExitingAnyStationOfAssembly1.length - 1] + timeSpentOnExit[0],
                timeWhenExitingAnyStationOfAssembly2[timeWhenExitingAnyStationOfAssembly2.length - 1] + timeSpentOnExit[1]
        );
        LogUtil.logIt("Minimum Time ..." + minTime);
        return minTime;
    }
}
