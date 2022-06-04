package com.interviewbit.math;

import com.util.LogUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://www.interviewbit.com/problems/excel-column-number/
 * <p>
 * Given a column title as appears in an Excel sheet, return its corresponding column number
 * A -> 1
 * <p>
 * B -> 2
 * <p>
 * C -> 3
 * <p>
 * ...
 * <p>
 * Z -> 26
 * <p>
 * AA -> 27
 * <p>
 * AB -> 28
 *
 * @author neeraj on 2019-07-27
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ExcelColumnNumbers {

    static Map<Character, Integer> characterToNumberMapping = new HashMap<>();

    static {
        characterToNumberMapping.put('A', 1);
        characterToNumberMapping.put('B', 2);
        characterToNumberMapping.put('C', 3);
        characterToNumberMapping.put('D', 4);
        characterToNumberMapping.put('E', 5);
        characterToNumberMapping.put('F', 6);
        characterToNumberMapping.put('G', 7);
        characterToNumberMapping.put('H', 8);
        characterToNumberMapping.put('I', 9);
        characterToNumberMapping.put('J', 10);
        characterToNumberMapping.put('K', 11);
        characterToNumberMapping.put('L', 12);
        characterToNumberMapping.put('M', 13);
        characterToNumberMapping.put('N', 14);
        characterToNumberMapping.put('O', 15);
        characterToNumberMapping.put('P', 16);
        characterToNumberMapping.put('Q', 17);
        characterToNumberMapping.put('R', 18);
        characterToNumberMapping.put('S', 19);
        characterToNumberMapping.put('T', 20);
        characterToNumberMapping.put('U', 21);
        characterToNumberMapping.put('V', 22);
        characterToNumberMapping.put('W', 23);
        characterToNumberMapping.put('X', 24);
        characterToNumberMapping.put('Y', 25);
        characterToNumberMapping.put('Z', 26);
    }

    public static void main(String[] args) {
        List<String> inputs = Arrays.asList("A", "B", "C", "AB", "AZ", "ABD", "CZ", "WX", "DZ", "BAQUZ", "BAQTZ");
        for (int i = 0; i < inputs.size(); i++) {
            LogUtil.logIt(" Column Title " + inputs.get(i) + " to number is  " + titleToNumber(inputs.get(i)));
        }
    }


    /**
     * The logic is simple, you have to do the base conversion, but this time the base is 26
     *
     * @param A
     * @return
     */
    public static int titleToNumber(String A) {
        int finalResult = 0;
        for (int i = 0; i < A.length(); i++) {
            finalResult += Math.pow(26, i) * characterToNumberMapping.get(A.charAt(A.length() - 1 - i));
        }
        return finalResult;
    }
}
