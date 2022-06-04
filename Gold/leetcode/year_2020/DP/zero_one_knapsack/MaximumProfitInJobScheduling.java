package com.leetcode.year_2020.DP.zero_one_knapsack;

import java.util.Arrays;
import java.util.Comparator;

public class MaximumProfitInJobScheduling {

    public static void main(String[] args) {
        int[] startTime = new int[]{1, 2, 3, 3};
        int[] endTime = new int[]{3, 4, 5, 6};
        int[] profit = new int[]{50, 10, 40, 70};

        System.out.println(jobScheduling(startTime, endTime, profit));
    }

    static class Job {
        int startTime;
        int endTime;
        int profit;

        public Job(int startTime, int endTime, int profit) {
            this.startTime = startTime;
            this.endTime = endTime;
            this.profit = profit;
        }

        public String toString() {
            return "[" + startTime + "," + endTime + "," + profit + "]";
        }
    }

    static int[] T; // Memoization

    public static int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        T = new int[profit.length];
        Arrays.fill(T, -1);
        // Sort based on start time, Now with each selected time we just need
        // to find should we include this item or not [We will take max from both]
        // and if we take an item our responsibility is also to find the next job which will
        // satisfy the criteria of A[endTime] < B[startTime]
        Job[] jobs = new Job[startTime.length];
        for (int i = 0; i < startTime.length; i++) {
            jobs[i] = new Job(startTime[i], endTime[i], profit[i]);
        }

        Arrays.sort(jobs, Comparator.comparingInt(a -> a.startTime));
        int currentJob = 0;
        return findMaxProfit(currentJob, jobs);
    }

    // job[currentJob][0] == startTime
    // job[currentJob][1] == endTime
    // job[currentJob][2] == profit
    public static int findMaxProfit(int currentJob, Job[] jobs) {
        if (currentJob == jobs.length) {
            return 0; // No jobs, hence no profit.
        }

        if (T[currentJob] > -1) return T[currentJob]; // early exit from precalculated data

        // if we choose this job, let me find out the next job which matches the criteria
        int nextJob = searchNextJob(currentJob, jobs);

        // basically something similar to knapsack
        //                          profit + findMaxProfit(nextJob, jobs)
        int ifWeIncludeTheJob = jobs[currentJob].profit + ((nextJob == -1) ? 0 : findMaxProfit(nextJob, jobs));
        int ifWeExclude = findMaxProfit(currentJob + 1, jobs);

        return T[currentJob] = Math.max(ifWeIncludeTheJob, ifWeExclude);
    }


    public static int searchNextJob(int currentJob, Job[] jobs) {
        // Since we already sort the job we can simply apply binary search
        // to find out next job matching the criteria
        int beg = currentJob + 1;
        int end = jobs.length - 1;
        int nextJob = -1; // if we did not find anything

        while (beg <= end) {
//            System.out.println("Next Job is " + nextJob);
            int mid = beg + (end - beg) / 2;
//            System.out.println("Beg = " + beg + " and End = " + end + " and mid = " + mid);
            if (jobs[mid].startTime >= jobs[currentJob].endTime) {
                nextJob = mid; // now we know mid will definitely be a candidate
                //  we need to check what all other smaller jobs can accomodate constraint
                end = mid - 1;
            } else {
                beg = mid + 1;
            }
        }
        return nextJob;
    }
}
