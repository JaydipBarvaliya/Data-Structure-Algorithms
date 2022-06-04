package com.leetcode.year_2020.challenges.October_challenge;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @author neeraj on 05/10/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class NumberofRecentCalls {

    public static void main(String[] args) {
        RecentCounter recentCounter = new RecentCounter();
        recentCounter.ping(1);
        recentCounter.ping(100);
        recentCounter.ping(3000);
        recentCounter.ping(3002);
    }

    static class RecentCounter {

        Deque<Integer> deque;

        public RecentCounter() {
            deque = new ArrayDeque<>();
        }

        public int ping(int t) {
            deque.add(t);
            int res = findRecent(t - 3000);
            System.out.println(res);
            return res;
        }

        public int findRecent(int low) {
            while (deque.peekFirst() < low) {
                deque.pollFirst();
            }
            return deque.size();
        }
    }

}
