package com.leetcode.year_2020.graph.dfs;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.List;

public class ThreeEqualParts {

    public static void main(String[] args) {
        LogUtil.printArray(threeEqualParts(new int[]{1, 0, 1, 0, 1}));
        LogUtil.printArray(threeEqualParts(new int[]{1, 1, 0, 1, 1}));
        LogUtil.printArray(threeEqualParts(new int[]{1, 1, 0, 0, 1}));
        LogUtil.printArray(threeEqualParts(new int[]{1, 0, 1, 1, 0}));
        LogUtil.printArray(threeEqualParts(new int[]{0, 1, 0, 1, 1}));
    }

    static List<List<Point>> result;
    static class Point {
        int start;
        int end;

        public Point(final int start, final int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static int[] threeEqualParts(int[] arr) {
        result = new ArrayList<>();
        if (dfs(arr, 0, -1, new ArrayList<>(), 3)) {
            List<Point> res = result.get(0);
            return new int[]{res.get(0).end, res.get(res.size() - 1).start};
        } else {
            return new int[]{-1, -1};
        }
    }

    public static boolean dfs(int[] arr, int pointer, int oldSum, List<Point> curr, int segment) {
        if (segment == 0 && pointer == arr.length) {
            result.add(new ArrayList<>(curr));
            return true;
        }
        if (segment == 0) return false;

        for (int i = pointer; i < arr.length; i++) {
            int constructedNumber = constructNum(arr, pointer, i);
            if (oldSum == -1 || constructedNumber == oldSum) {
                curr.add(new Point(pointer, i));
                if (dfs(arr, i + 1, constructedNumber, curr, segment - 1)) {
                    return true;
                }
                curr.remove(curr.size() - 1);
            }
        }
        return false;
    }

    public static int constructNum(int[] arr, int start, int end) {
        int num = 0;
        int counter = 0;
        while (end >= start) {
            num += Math.pow(2, counter++) * arr[end];
            end--;
        }
        return num;
    }
}
