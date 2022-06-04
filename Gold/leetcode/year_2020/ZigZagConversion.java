package com.leetcode.year_2020;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neeraj on 12/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ZigZagConversion {

    public static void main(String[] args) {
        System.out.println(zigzag("YELLOWPINK", 4));
        System.out.println(zigzag("REDBLUEBLACK", 2));
    }

    public static String zigzag(String s, int rows) {
        // Edge case
        if (rows < 1) return s;
        List<StringBuilder> rowsData = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            rowsData.add(new StringBuilder());
        }
        int value = -1;
        int rowCounter = 1;

        for (int i = 0; i < s.length(); i++) {
            if (rowCounter == 1 || rowCounter == rows) {
                value = value < 0 ? 1 : -1;
            }
            rowsData.get(rowCounter - 1).append(s.charAt(i));

            if (rowCounter + value > 0 || rowCounter + value < rows) {
                rowCounter = rowCounter + value;
            }
        }

        StringBuilder result = new StringBuilder();
        for (StringBuilder row : rowsData) {
            result.append(row.toString());
        }
        return result.toString();
    }
}
