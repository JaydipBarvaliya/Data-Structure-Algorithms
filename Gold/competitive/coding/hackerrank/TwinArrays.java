package com.competitive.coding.hackerrank;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jaine03 on 17/06/17.
 *
 * https://www.hackerrank.com/contests/w33/challenges/twin-arrayss
 */
public class TwinArrays {

    public static void main(String[] args) {
        try{
            List<Long> first = new ArrayList<>();
            List<Long> second = new ArrayList<>();

            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            br.readLine();
            for(String s: br.readLine().split(" ")){
                first.add(Long.parseLong(s));
            }
            for(String s: br.readLine().split(" ")) {
                second.add(Long.parseLong(s));
            }

            Integer firstArrayMinValuePos = getMinPosition(first);
            Integer secondArrayMinValuePos = getMinPosition(second);

            if(firstArrayMinValuePos == secondArrayMinValuePos){
                Long firstCombinationSum = first.get(firstArrayMinValuePos) + second.get(getSecondMinPosition(second));
                Long secondCombinationSum = first.get(getSecondMinPosition(first)) + second.get(secondArrayMinValuePos);

                System.out.println(firstCombinationSum>secondCombinationSum?secondCombinationSum:firstCombinationSum);
            }
            else {
                System.out.println(first.get(firstArrayMinValuePos)+ second.get(secondArrayMinValuePos));
            }


        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static Integer getSecondMinPosition(List<Long> list){
        Long MIN_VALUE = Long.MAX_VALUE;
        Long SECOND_MIN_VALUE = Long.MIN_VALUE;
        Integer MIN_VALUE_POS = 0;
        Integer SECOND_MIN_VALUE_POS = 0;

        for(int i=0;i<list.size();i++){
            if(list.get(i) < MIN_VALUE){
                SECOND_MIN_VALUE = MIN_VALUE;
                MIN_VALUE = list.get(i);
                SECOND_MIN_VALUE_POS = MIN_VALUE_POS;
                MIN_VALUE_POS = i;
            }
            else if(list.get(i) < SECOND_MIN_VALUE){
                SECOND_MIN_VALUE = list.get(i);
                SECOND_MIN_VALUE_POS = i;
            }
        }
        return SECOND_MIN_VALUE_POS;
    }

    public static Integer getMinPosition(List<Long> list){
        Long MIN_VALUE = Long.MAX_VALUE;
        Integer MIN_VALUE_POS = 0;

        for(int i=0;i<list.size();i++){
            if(list.get(i) < MIN_VALUE){
                MIN_VALUE = list.get(i);
                MIN_VALUE_POS = i;
            }
        }
        return MIN_VALUE_POS;
    }
}
