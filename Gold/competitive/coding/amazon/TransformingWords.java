package com.competitive.coding.amazon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class TransformingWords {
    public static void main(String[] args) throws Exception {
        List<String> dictionary = new ArrayList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String start = br.readLine();
        String end = br.readLine();
        int dictionaryLength = Integer.parseInt(br.readLine());
        int counter = 0;
        while (counter++ < dictionaryLength){
            dictionary.add(br.readLine());
        }

        StringBuffer buff = new StringBuffer(start);
        int totalTransformation = 0;
        int i=0;
        int words = 0;
        while (true){
            if(start.charAt(i) != end.charAt(i)){
                if(dictionary.contains(buff.replace(i,i+1,end.charAt(i)+"").toString())){
                    totalTransformation++;
                    start = buff.toString();
                    words++;
                    if(words >= start.length()-1){
                        break;
                    }
                } else {
                    buff = new StringBuffer(start);
                }
                i++;
                if(i>=start.length()){
                    i=0;
                }

            }
        }
        System.out.println("Total transformation "+totalTransformation);
    }
}
