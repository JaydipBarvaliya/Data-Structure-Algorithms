package com.interviewbit.string_parsing;

import com.util.LogUtil;

/**
 * https://www.interviewbit.com/problems/zigzag-string/
 * <p>
 * The string "PAYPALISHIRING" is written in a zigzag pattern on a given number of rows like this: (you may want to display this pattern in a fixed font for better legibility)
 * <p>
 * P.......A........H.......N
 * ..A..P....L....S....I...I....G
 * ....Y.........I........R
 * And then read line by line: PAHNAPLSIIGYIR
 * Write the code that will take a string and make this conversion given a number of rows:
 * <p>
 * string convert(string text, int nRows);
 * convert("PAYPALISHIRING", 3) should return "PAHNAPLSIIGYIR"
 * **Example 2 : **
 * ABCD, 2 can be written as
 * <p>
 * A....C
 * ...B....D
 * and hence the answer would be ACBD.
 *
 * @author neeraj on 2019-08-03
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ZigZagString {

    public static void main(String[] args) {
        zigZagString("PAYPALISHIRING", 3);
        zigZagString("ABCD", 2);
        zigZagString("ABCDEFGHIJKL", 3);
        zigZagString("ABCDEFGHIJKL", 4);
//        zigZagString("B", 1);
    }

    public static void zigZagString(String input, int Rows) {
        LogUtil.logIt("ZigZag String of [" + input + "] is " + convert(input, Rows));
    }

    public static String convert(String input, int Rows) {
        if (input.length() <= 1) {
            return input;
        }
        String[] arr = input.split("");
        StringBuffer result = new StringBuffer();
        // This will be used to build the formula, and it should always start from 0 based index;
        int tempRows = 0;
        int zigZagCounter = 0;
        for (int i = 0; i < Rows; i++) {
            tempRows = i;
            zigZagCounter = 0;

            for (int j = i; j < arr.length; ) {
                result.append(arr[j]).append(" , ");
                j += findGap(Rows, tempRows, zigZagCounter++);
            }
        }
        return result.toString();
    }

    /**
     * So finding gap solution is simple
     * Firstly if you are on 1st or Last Row, the gap between 1st and next item will be
     * (currentRow == FIRST_ROW || currentRow == LAST_ROW) = 2 * (totalRows -1); // Why minus one bcoz we are taking input with 0 based index.
     * <p>
     * Now if currentRow is in middle we have to identify that whether we are going downwards \/ or upwards /\
     * * if you are going downward than the gap will be more as more items will be present to push in below rows
     * * So (zigZagCounter is even) then
     * gap = 2 * ( (totalRows - 1) - currentRow)
     * <p>
     * if zigZagCounter is odd then
     * <p>
     * gap = 2 * currentRow
     *
     * @param totalRows
     * @param currentRow
     * @param zigZagCounter
     * @return
     */
    public static int findGap(int totalRows, int currentRow, int zigZagCounter) {
        if (currentRow == 0 || currentRow == totalRows - 1) {
            return 2 * (totalRows - 1);
        }
        if (zigZagCounter % 2 == 0) {
            return 2 * (totalRows - currentRow - 1);
        }
        // For Odd Counter 
        return 2 * (currentRow);
    }
}
