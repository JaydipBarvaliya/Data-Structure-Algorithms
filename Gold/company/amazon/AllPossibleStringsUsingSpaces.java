package com.company.amazon;

public class AllPossibleStringsUsingSpaces {

    public static void main(String[] args) {
//        combination("ABC", 1);
        printPermutations("", "ABC");
    }

    public static void printPermutations(String soFar, String rest) {
        if(rest.length() <= 0) {
            System.out.println(soFar);
            return;
        }

        for(int i=0;i<rest.length();i++) {
            printPermutations(soFar+rest.substring(0,i+1)+" ", rest.substring(i+1));
        }
    }

}
