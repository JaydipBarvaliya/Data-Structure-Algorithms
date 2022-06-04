package com.interviewbit.two_pointers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FourSum {

    public static void main(String[] args) {
        fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
        fourSum(new int[]{2, 2, 2, 2}, 8);
    }

    public static List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 3; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            List<List<Integer>> threeSumList = threeSum(nums, i + 1, target - nums[i]);
//            System.out.println(threeSumList);
            for (List<Integer> threeSum : threeSumList) {
                threeSum.add(0, nums[i]);
                result.add(threeSum);
            }
        }
        System.out.println(result);
        return result;
    }

    public static List<List<Integer>> threeSum(int[] nums, int start, int target) {
        List<List<Integer>> result = new ArrayList<>();

        //1,0,-1,0,-2,2
        // System.out.println("Start "+ start + " target : "+ target);
        // -2 -1 0 0 1 2
        for (int i = start; i < nums.length - 2; i++) {
            if (i != start && nums[i] == nums[i - 1]) continue;
            int first = nums[i];
            // System.out.println("Target before: "+ target);
            int sumToFind = target - nums[i];

            // System.out.println("Target is "+ target);
            int low = i + 1;
            int high = nums.length - 1;
            while (low < high) {
                if (nums[low] + nums[high] == sumToFind) {
                    List<Integer> triplet = new ArrayList<>();
                    triplet.add(first);
                    triplet.add(nums[low]);
                    triplet.add(nums[high]);

                    result.add(triplet);
                    // to skip duplicates
                    while (low < high && nums[low] == nums[low + 1]) low++;
                    while (low < high && nums[high] == nums[high - 1]) high--;

                    low++;
                    high--;
                } else if (nums[low] + nums[high] > target) {
                    high--;
                } else {
                    low++;
                }
            }
        }
        return result;
    }
}
