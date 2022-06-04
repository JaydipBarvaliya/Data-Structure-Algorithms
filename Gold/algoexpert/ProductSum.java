package com.algoexpert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author neeraj on 10/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ProductSum {

    public static void main(String[] args) {
        List<Object> input = Arrays.asList(
                5, 2, Arrays.asList(7, -1), 3, Arrays.asList(6, Arrays.asList(-13, 8), 4)
        );
        System.out.println(getProductSum(input, 1));
    }

    public static int getProductSum(List<Object> input, int multiple) {
        int sum = 0;
        for (Object item : input) {
            if (item instanceof List) {
                int tempSum = getProductSum((List<Object>) item, multiple + 1);
                sum += tempSum;
            } else {
                sum += (Integer) item;
            }
        }
        return sum * multiple;
    }
}
