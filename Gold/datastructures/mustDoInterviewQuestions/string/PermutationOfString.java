package com.datastructures.mustDoInterviewQuestions.string;

/**
 * Created by jaine03 on 23/07/17.
 */
public class PermutationOfString {

    public static void main(String[] args) {
        printAllPermutations("", "AABC");
    }

    public static void printAllPermutations(String prefix, String str) {
        if(str.length() == 0){
            System.out.println(prefix);
        }
        for(int i=0;i<str.length();i++){
            printAllPermutations(prefix+str.charAt(i),str.substring(0,i)+str.substring(i+1,str.length()));
        }
    }
}
