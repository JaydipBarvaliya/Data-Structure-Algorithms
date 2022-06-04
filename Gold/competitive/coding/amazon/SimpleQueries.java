package com.competitive.coding.amazon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by jaine03 on 13/09/17.
 */
public class SimpleQueries {

    public static void main(String[] args) {
        int nums[] = {2, 10, 5, 4, 8};
        int maxes[] = {4, 3, 1, 7, 8};
        int answer[] = counts(nums, maxes);
        print(answer);
    }

    public static void print(int[] answer) {
        for (int i : answer) {
            System.out.println(i);
        }
    }

    static int[] counts(int[] nums, int[] maxes) {
        List<Integer> answer = new ArrayList<>();
        Arrays.sort(nums);
        Boolean findOutSomething;
        for (int m : maxes) {
            findOutSomething = m < nums[0] ? true : false;
            if (findOutSomething) {
                answer.add(0);
            } else {
                for (int i = nums.length - 1; i >= 0; i--) {
                    if (nums[i] <= m) {
                        answer.add(i + 1);
                        break;
                    }
                }
            }
        }
        return answer.stream().mapToInt(i -> i).toArray();
    }
}
