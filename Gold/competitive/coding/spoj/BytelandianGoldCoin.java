package com.competitive.coding.spoj;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Created by jaine03 on 19/04/17.
 */
public class BytelandianGoldCoin {

   static Map<Long,Long> memory = new HashMap<>();

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCases = 3;

        int testCaseCounter = 0;
        String input = null;
        Long goldCoin = null;
        while (true) {
            try {
                input = br.readLine();
                 goldCoin = Long.parseLong(input);
            }
            catch (Exception e){
                break;
            }

            System.out.println(getMaxCoin(goldCoin));
        }
    }

    public static Long getMaxCoin(Long goldCoinValue){
        if(goldCoinValue < 12)
            return goldCoinValue;

        if(memory.containsKey(goldCoinValue))
            return memory.get(goldCoinValue);

        memory.put(goldCoinValue,
                getMaxCoin(goldCoinValue/2)+getMaxCoin(goldCoinValue/3)+getMaxCoin(goldCoinValue/4));

        return memory.get(goldCoinValue);
    }
}
