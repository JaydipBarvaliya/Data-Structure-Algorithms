package com.competitive.coding.hackerrank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by jaine03 on 17/06/17.
 *
 * https://www.hackerrank.com/contests/w33/challenges/pattern-count
 *
 *
 */
public class PatternCount {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Integer totalTestCases = Integer.parseInt(br.readLine());

        for(int i=0;i<totalTestCases;i++){
            String input = br.readLine();
            processAndCalculateUniqueSequence(input);
        }
    }

    public static void processAndCalculateUniqueSequence(String input){
        Pattern pattern = Pattern.compile("(?=(10+1))");
        //Pattern pattern = Pattern.compile("(?<=1)0+(?=1)");
        Matcher matcher = pattern.matcher(input);
        int totalSeq = 0;
        while (matcher.find()){
            totalSeq++;
        }
        System.out.println(totalSeq);
    }

}
