package com.leetcode.year_2020;

import java.util.*;

/**
 * @author neeraj on 14/03/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class LargestValueFromLabels {

    public static void main(String[] args) {
        System.out.println(largestValsFromLabels(new int[]{5, 4, 3, 2, 1},
                new int[]{1, 1, 2, 2, 3}, 3, 1));

        System.out.println(largestValsFromLabels(new int[]{5, 4, 3, 2, 1},
                new int[]{1, 3, 3, 3, 2}, 3, 2));

        System.out.println(largestValsFromLabels(new int[]{9, 8, 8, 7, 6},
                new int[]{0, 0, 0, 1, 1}, 3, 1));

        System.out.println(largestValsFromLabels(new int[]{9, 8, 8, 7, 6},
                new int[]{0, 0, 0, 1, 1}, 3, 2));
    }

    static class ValAndLabel {
        Integer val;
        Integer label;

        public ValAndLabel(Integer val, Integer label) {
            this.val = val;
            this.label = label;
        }
    }

    public static int largestValsFromLabels(int[] values, int[] labels, int num_wanted, int use_limit) {

        List<ValAndLabel> valAndLabels = new ArrayList<>();
        for (int i = 0; i < values.length; i++) {
            valAndLabels.add(new ValAndLabel(values[i], labels[i]));
        }
        // Now we have values sorted in desc order.
        Collections.sort(valAndLabels, (a, b) -> b.val.compareTo(a.val));

        int count = 0;
        int maxSum = 0;
        Map<Integer, Integer> labelFreqMap = new HashMap<>();
        for (int i = 0; i < valAndLabels.size() && count < num_wanted; i++) {
            ValAndLabel valAndLabel = valAndLabels.get(i);
            if (labelFreqMap.containsKey(valAndLabel.label)) {
                if (labelFreqMap.get(valAndLabel.label) >= use_limit) {
                    continue;
                }
                labelFreqMap.put(valAndLabel.label, labelFreqMap.get(valAndLabel.label) + 1);
            } else {
                labelFreqMap.put(valAndLabel.label, 1);
            }
            maxSum += valAndLabel.val;
            count++;
        }
        return maxSum;
    }
}
