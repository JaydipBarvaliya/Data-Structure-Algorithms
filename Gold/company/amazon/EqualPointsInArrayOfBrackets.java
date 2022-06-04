package com.company.amazon;

public class EqualPointsInArrayOfBrackets {

    public static void main(String[] args) {
        System.out.println(getEqualPoint("(())))(".toCharArray()));
        System.out.println(getEqualPoint("))".toCharArray()));
        System.out.println(getEqualPoint("(()))(()()())))".toCharArray()));
        System.out.println(getEqualPoint("((".toCharArray()));
    }

    public static int getEqualPoint(char[] arr) {
        int openingBrackets = 0;
        int index = 0;

        for (char c : arr) {
            if (c == '(') {
                openingBrackets++;
            }
        }

        for (int i = arr.length-1; i >= 0; i--) {
            if(openingBrackets == 0) {
                index = i;
                break;
            }
            openingBrackets--;
//            if(arr[c] == ')') {
//                openingBrackets--;
//            } else if(arr[c] == '(') {
//                openingBrackets--;
//            }
        }
        return index;
    }
}
