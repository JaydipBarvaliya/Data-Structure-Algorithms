package com.competitive.coding.spoj;

import java.util.Scanner;

/**
 * Created by jaine03 on 22/04/17.
 */
public class TheNextPalindrome {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();
        int testCaseCounter = 0;

        while (testCaseCounter++ < testCases) {
            String inputValue = sc.next();
            try {
                Long input = Long.parseLong(inputValue);
                Long value1 = getNextPalindrome(input);
                Long value2 = NextPalindromeStackOverflow.getNextPalindrome(input);
                System.out.println(getNextPalindrome(input));
                System.out.println(NextPalindromeStackOverflow.getNextPalindrome(input));
                if(value1.longValue() != value2.longValue()){
                    System.out.println("\nNot Matched for "+inputValue);
                    System.out.println(value1 + ":::::::::::::::::::::::::::::::::::::::::::::: "+value2);
                }
//                if (testCaseCounter < testCases)
//                    System.out.println();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static Boolean isPalindrome(Long input){
        StringBuffer str = new StringBuffer(input.toString());
        return input.toString().equals(str.reverse().toString());
    }

    public static Long getNextPalindrome(Long input){
        while (true){
            input++;
            if(isPalindrome(input)){
                return input;
            }
        }
    }
}
