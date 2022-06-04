package com.competitive.coding.spoj;

import java.util.Scanner;

/**
 * Created by jaine03 on 23/04/17.
 */
public class NextPalindromeStackOverflow {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
//        int testCases = sc.nextInt();
//        int testCaseCounter = 0;
//        String inputValue = sc.next();
//            try {
//                Long input = Long.parseLong(inputValue);
//                System.out.println(getNextPalindrome(input));
//            } catch (Exception e) {
//                //e.printStackTrace();
//            }
        printNextPalindrome(1245401);
        printNextPalindrome(1045778);
        printNextPalindrome(1234544322l);
        printNextPalindrome(8999l);
        printNextPalindrome(1234994322l);
        printNextPalindrome(1234554321l);
    }

    public static Long getNextPalindrome(Long input) {
        String inputString = input.toString();
        int length = inputString.length();

        if (length % 2 == 0) { // Handling Even Length Input
            int mid = length / 2;

            String firstHalf = inputString.substring(0, mid);
            String secondHalf = inputString.substring(mid, length);

            Long firstHalfLong = Long.valueOf(firstHalf);
            Long secondHalfLong = Long.valueOf(secondHalf);
            int l1 = firstHalf.length() - 1;
            int l2 = 0;

            while (l1 > -1 && l2 < secondHalf.length()) {
                if (firstHalf.charAt(l1) < secondHalf.charAt(l2)) {
                    firstHalfLong = Long.parseLong(firstHalf) + 1;
                    firstHalf = firstHalfLong.toString();
                    break;
                } else if (firstHalf.charAt(l1) > secondHalf.charAt(l2)) {
                    break;
                } else {
                    l2++;
                    l1--;
                }
            }

//            if(firstHalf.equals(secondHalf) || firstHalf.equals(reverseLongValue(secondHalfLong).toString())){
//                firstHalfLong = Long.parseLong(firstHalf)+1;
//                return Long.parseLong(firstHalfLong.toString() +new StringBuffer(firstHalfLong.toString()).reverse().toString());
//            }else{
//                StringBuffer finalResult = new StringBuffer(firstHalfLong.toString());
//                //System.out.println(finalResult);
//                String result = finalResult.toString();
//                return Long.parseLong(result+finalResult.reverse().toString());
//            }
            if (firstHalf.equals(secondHalf)) {
                firstHalfLong = firstHalfLong + 1;
                return Long.parseLong(firstHalfLong + new StringBuffer(firstHalfLong.toString()).reverse().toString());
            } else {
                StringBuffer finalResult = new StringBuffer(firstHalfLong.toString());
                //System.out.println(finalResult);
                String result = finalResult.toString();
                return Long.parseLong(result + finalResult.reverse().toString());
            }
        } else { // Handling Odd Cases

            if (String.valueOf(input + 1).length() % 2 == 0) {
                return getNextPalindrome(input + 1);
            }
            if (String.valueOf(input).length() == 1) { // Handles number b/w 0-8
                return input + 1;
            }

            int mid = (length / 2);
            String firstHalf = inputString.substring(0, mid);
            Long middle = Long.valueOf(inputString.substring(mid, mid + 1));
            String secondHalf = inputString.substring(mid + 1, length);
            Long firstHalfLong = Long.valueOf(firstHalf);
            Long secondHalfLong = Long.valueOf(secondHalf);

            if (firstHalfLong == secondHalfLong) {
                middle = middle + 1;
            }

            if (firstHalfLong < secondHalfLong) {
                if (reverseLongValue(firstHalfLong) < secondHalfLong) {
                    if (middle + 1 > 9) {
                        middle = 0L;
                        firstHalfLong = firstHalfLong + 1;
                    } else {
                        middle = middle + 1;
                    }
                }
            }
            return Long.parseLong(firstHalfLong.toString() + middle + new StringBuffer(firstHalfLong.toString()).reverse().toString());
        }
    }

    public static Long reverseLongValue(Long value) {
        return Long.valueOf(new StringBuffer(value.toString()).reverse().toString());
    }

    public static void printNextPalindrome(long number) {
        number++;
        while (true) {
            StringBuffer val = new StringBuffer(String.valueOf(number));
            if (val.reverse().toString().equals(String.valueOf(number))) {
                System.out.println(number);
                break;
            }
            number = number+1l;
        }
    }
}
