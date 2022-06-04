package com.leetcode.problems.medium;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author neeraj on 11/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class NextClosestTime {

    public static void main(String[] args) {
        solve(Arrays.asList("19:34"));
        solve(Arrays.asList("23:59"));
    }

    private static void solve(List<String> times) {
        times.forEach(time -> {
            System.out.println("Next Closest Time for " + time + " ==> " + nextClosestTime(time));
        });
    }

    public static String nextClosestTime(String time) {
        // We will convert the whole time into minutes and just keep adding 1 minute to it.
        // and every time we add a minute we can check our hash set that all the digits are present
        // in the hashSet of original time.
        Set<Character> values = new HashSet<>();
        for (char c : time.toCharArray()) {
            values.add(c);
        }
        int minutes = Integer.parseInt(time.substring(0, 2)) * 60;
        minutes += Integer.parseInt(time.substring(3));
        StringBuffer result = new StringBuffer();
        while (true) {
            // We will keep on adding 1 minute until we find a  next closest greater time
            // but we have to take special care of midnight i.e : 23:59 after we add 1 to it
            // the time should become 00:00 instead of 23:60
            int nextMinute = (minutes + 1) % (24 * 60); // why 24 * 60 , bcoz 1 day has 24 hours
            int[] nextTime = {
                    nextMinute / 60 / 10, // Converting minutes into hours and also taking left most digit
                    nextMinute / 60 % 10, // in this hour placement we are taking second digit hence modulo
                    nextMinute % 60 / 10, // Converting minutes into hours and left minutes
                    nextMinute % 60 % 10
            };
            minutes = nextMinute;

            // To Explain this calculation
            /**
             * Input : 19:34 == (19 * 60 + 34) minutes  ==> 1174 minutes
             * Now increment 1 minute ==> 1175 minute
             * Now let's convert it back to hour and minute
             *
             * int hours = 1174 / 60 ==rounded upto==> 19 ;
             * int minutes = 1174 % 60 ======> 35
             *
             * So the time is 19:35 but we have to store them in array
             * So hence for each hour and minute inorder to take their respective
             * left and right index value we did (divide by 10) and (Modulo by 10)
             */
            if (isValidTime(nextTime, values)) {
                for (int i : nextTime) {
                    result.append(i);
                }
                break;
            }
        }
        return result.toString();
    }

    private static boolean isValidTime(int[] nextTime, Set<Character> values) {
        Boolean isValid = true;
        for (int i : nextTime) {
            if (!values.contains((char)(i + '0'))) {
                isValid = false;
                break;
            }
        }
        return isValid;
    }
}
