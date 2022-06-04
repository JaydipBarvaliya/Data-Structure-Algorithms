package com.competitive.coding.hackerearth;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Given an array and two integers I andJ, modify the array such that you retain I nodes, then delete J nodes. Do this till end of the array
 * Created by jaine03 on 09/08/17.
 */
public class ModifyArray {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        try{
            String []input = br.readLine().split(",");
            StringBuffer result = new StringBuffer();
            Integer valOfI = Integer.parseInt(br.readLine());
            Integer valOfJ = Integer.parseInt(br.readLine());

            for(int i=0,counter=0;i<input.length;i++){
                if(counter < valOfI){
                    counter++;
                    result.append(input[i]).append(",");
                } else {
                    counter = 0;
                    i = i+valOfJ-1;
                }
            }
            System.out.println(result.deleteCharAt(result.length()-1).toString());

        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
