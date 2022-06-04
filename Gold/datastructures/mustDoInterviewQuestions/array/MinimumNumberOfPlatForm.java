package com.datastructures.mustDoInterviewQuestions.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jaine03 on 22/07/17.
 */
public class MinimumNumberOfPlatForm {
    private static String ARRIVAL = "Arrival";
    private static String DEPARTURE = "Departure";

    public static void main(String[] args) {
        int arr[] = {900, 940, 950, 1100, 1500, 1800};
        int dep[] = {910, 1200, 1120, 1130, 1900, 2000};

        List<String> sortedEvents = new ArrayList<>();

        Arrays.sort(arr);
        Arrays.sort(dep);
        int arrCounter = 0;
        int depCounter = 0;
        while (arrCounter < arr.length && depCounter < arr.length) {
            if (arr[arrCounter] < dep[depCounter]) {
                sortedEvents.add(ARRIVAL);
                arrCounter++;
            } else {
                sortedEvents.add(DEPARTURE);
                depCounter++;
            }
        }
        while (arrCounter < arr.length) {
            sortedEvents.add(ARRIVAL);
            arrCounter++;
        }
        while (depCounter < arr.length) {
            sortedEvents.add(DEPARTURE);
            depCounter++;
        }
        System.out.println(getPlatforms(sortedEvents));
    }

    public static int getPlatforms(List<String> sortedEvents){
        int platform = 0;
        int maxPlatform = 0;
        for(String str: sortedEvents){
            if(str.equals(ARRIVAL))
                platform++;
            if(str.equals(DEPARTURE))
                platform--;
            if(platform > maxPlatform)
                maxPlatform = platform;

        }
        return maxPlatform;
    }
}
