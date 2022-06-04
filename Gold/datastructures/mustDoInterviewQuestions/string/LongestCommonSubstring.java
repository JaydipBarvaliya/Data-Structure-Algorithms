package com.datastructures.mustDoInterviewQuestions.string;

/**
 * Created by jaine03 on 24/07/17.
 */
public class LongestCommonSubstring {

    public static void main(String[] args) {
        System.out.println(getLongestCommonSubString("GEEKSFORGEEKS","QUIZGFORES"));
    }

    public static String getLongestCommonSubString(String first,String second){
        String LCS = "";
        for(int i=0,j=0;i<second.length();){
            if(first.contains(second.substring(j,i+1))){
                if(LCS.length() < second.substring(j,i+1).length()) {
                    LCS = second.substring(j,i + 1);
                }
            } else {
                j=i;
            }
            i++;
        }
        return LCS;
    }
}
