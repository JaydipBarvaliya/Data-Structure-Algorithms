package com.interviewbit.math;

import com.util.LogUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://www.interviewbit.com/problems/excel-column-title/
 * <p>
 * Given a positive integer, return its corresponding column title as appear in an Excel sheet.
 * <p>
 * For example:
 * <p>
 * 1 -> A
 * 2 -> B
 * 3 -> C
 * ...
 * 26 -> Z
 * 27 -> AA
 * 28 -> AB
 *
 * @author neeraj on 2019-07-27
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ExcelColumnTitle {

    static Map<Integer, Character> integerToCharacterMapping = new HashMap<>();

    static {
        integerToCharacterMapping.put(0, 'Z');
        integerToCharacterMapping.put(1, 'A');
        integerToCharacterMapping.put(2, 'B');
        integerToCharacterMapping.put(3, 'C');
        integerToCharacterMapping.put(4, 'D');
        integerToCharacterMapping.put(5, 'E');
        integerToCharacterMapping.put(6, 'F');
        integerToCharacterMapping.put(7, 'G');
        integerToCharacterMapping.put(8, 'H');
        integerToCharacterMapping.put(9, 'I');
        integerToCharacterMapping.put(10, 'J');
        integerToCharacterMapping.put(11, 'K');
        integerToCharacterMapping.put(12, 'L');
        integerToCharacterMapping.put(13, 'M');
        integerToCharacterMapping.put(14, 'N');
        integerToCharacterMapping.put(15, 'O');
        integerToCharacterMapping.put(16, 'P');
        integerToCharacterMapping.put(17, 'Q');
        integerToCharacterMapping.put(18, 'R');
        integerToCharacterMapping.put(19, 'S');
        integerToCharacterMapping.put(20, 'T');
        integerToCharacterMapping.put(21, 'U');
        integerToCharacterMapping.put(22, 'V');
        integerToCharacterMapping.put(23, 'W');
        integerToCharacterMapping.put(24, 'X');
        integerToCharacterMapping.put(25, 'Y');
    }

    public static void main(String[] args) {
        List<Integer> inputs = Arrays.asList(1, 2, 3, 28, 52, 732, 104, 622,943592, 943566);
        for (int i = 0; i < inputs.size(); i++) {
            LogUtil.logIt(" Column Title for number " + inputs.get(i) + " is  " + convertToTitle(inputs.get(i)));
        }
    }

    public static String convertToTitle(int A) {
        StringBuffer result = new StringBuffer();
        while (A > 0) {
            result.append(integerToCharacterMapping.get(A % 26));
            if(A%26 ==0) {
                A = A - 26;
            }
            A = A / 26;
        }
        return result.reverse().toString();
    }
}


//    Column Title A to number is  1
//        Column Title B to number is  2
//        Column Title C to number is  3
//        Column Title AB to number is  28
//        Column Title AZ to number is  52
//        Column Title ABD to number is  732
//        Column Title CZ to number is  104
//        Column Title WX to number is  622
//        Column Title DZ to number is  130
//        Column Title BAQUZ to number is  943592
//        Column Title BAQTZ to number is  943566
