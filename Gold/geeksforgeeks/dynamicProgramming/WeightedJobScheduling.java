package com.geeksforgeeks.dynamicProgramming;

import com.geeksforgeeks.array.ArrayRotation;

public class WeightedJobScheduling {

    public static void main(String[] args) {
        int[][] jobDetails = {
                {1, 4, 3},
                {2, 6, 5},
                {4, 7, 2},
                {5, 9, 4},
                {6, 8, 6},
                {7, 10, 8}};

        System.out.println("Maximum Profit is " + getMaximumProfit(jobDetails));
    }

    /**
     * JobDetails Array contains each job detail such as {startTime, FinishTime, Profit if job runs}
     *
     * @param jobDetails
     * @return
     */
    public static int getMaximumProfit(int[][] jobDetails) {

        int[] JS = new int[jobDetails.length]; // This is the Job Scheduling Matrix having profit rates

        // Sort JobDetails [{startTime1, finishTime1, profit1},{startTime2, finishTime2, profit2},{startTime3, finishTime3, profit3},]
        for (int i = 0; i < jobDetails.length; i++) {
            for (int j = 0; j < jobDetails.length - 1; j++) {
                if (jobDetails[j][1] > jobDetails[j + 1][1]) {
                    int[] temp = jobDetails[j];
                    jobDetails[j] = jobDetails[j + 1];
                    jobDetails[j + 1] = temp;
                }
            }
        }

        // When there is only 1 job in the pipeline so the maximum profit we can get is the profit came as input
        for (int i = 0; i < jobDetails.length; i++) {
            JS[i] = jobDetails[i][2];
        }

        //For Every i we will start j from the start (i.e. 0)
        int i = 1;
        for (int j = 0; j < i && i < JS.length; ) {

            //Check both job at i and j can run together or not
            if (canJobRunTogether(jobDetails, i, j)) {

                // Now let's check these job can actually give us any profit or not
                if (JS[j] + jobDetails[i][2] > JS[i]) {
                    JS[i] = JS[j] + jobDetails[i][2];
                }
            }
            j++;
            if (j >= i) {
                i++;
                j = 0;
            }
        }

        ArrayRotation.printArray(JS);
        return JS[JS.length - 1];
    }

    private static boolean canJobRunTogether(int[][] jobDetails, int i, int j) {
        Boolean canJobRunTogether = false;
        if (jobDetails[j][1] <= jobDetails[i][0]) {
            canJobRunTogether = true;
        }
        return canJobRunTogether;
    }

}
