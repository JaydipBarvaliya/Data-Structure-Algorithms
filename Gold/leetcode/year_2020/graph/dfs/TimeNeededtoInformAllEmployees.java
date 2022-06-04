package com.leetcode.year_2020.graph.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author neeraj on 31/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class TimeNeededtoInformAllEmployees {

    public static void main(String[] args) {
        System.out.println(numOfMinutes(1, 0, new int[]{-1}, new int[]{0}));
        System.out.println(numOfMinutes(6, 2, new int[]{2, 2, -1, 2, 2, 2}, new int[]{0, 0, 1, 0, 0, 0}));
        System.out.println(numOfMinutes(7, 6, new int[]{1, 2, 3, 4, 5, 6, -1}, new int[]{0, 6, 5, 4, 3, 2, 1}));
    }

    public static int numOfMinutes(int n, int headID, int[] manager, int[] informTime) {
        /**
         * We can prepare the adjacent list from manager array.
         * Using Key as the manager and List<Integer> ids as the child
         * which are child of this manager.
         *
         * Then we can start DFS from headOfCompany and keep maintaining
         * max inform time in all it's path... when recursion completes and we come with
         * a result of maxInform time from it's direct child we can then add inform
         * time of headOfCompany and that's our result.
         */
        Map<Integer, List<Integer>> managerReportees = new HashMap<>();
        for (int i = 0; i < manager.length; i++) {
            if (i == headID) continue; // we will skip populating map for company head.
            managerReportees.putIfAbsent(manager[i], new ArrayList<>());
            managerReportees.get(manager[i]).add(i); // Associating the child.
        }

//         Now we can do the DFS
        return doDFSAndFetchMaxInformTime(managerReportees, headID, informTime);
//        fastDfs(managerReportees, headID, informTime, informTime[headID]);
//        return MaxInformTime == Integer.MIN_VALUE ? 0 : MaxInformTime;
    }

    static int MaxInformTime = Integer.MIN_VALUE;

    private static void fastDfs(Map<Integer, List<Integer>> managerReportees, int manager, int[] informTime, int currInformTime) {
        if(!managerReportees.containsKey(manager)) return;
        MaxInformTime = Math.max(MaxInformTime, currInformTime);

        for (Integer reportee : managerReportees.get(manager)) {
            fastDfs(managerReportees, reportee, informTime, currInformTime + informTime[reportee]);
        }
    }

    private static int doDFSAndFetchMaxInformTime(Map<Integer, List<Integer>> managerReportees, int source, int[] informTime) {
        /**
         * The important thing is once we get maxTime from all direct children, we can add head's inform time.
         */
        int maxInformTime = 0;

        if (managerReportees.containsKey(source)) {
            for (Integer employeeId : managerReportees.get(source)) {
                maxInformTime = Math.max(maxInformTime, doDFSAndFetchMaxInformTime(managerReportees, employeeId, informTime));
            }
        }
        return maxInformTime + informTime[source];
    }
}
