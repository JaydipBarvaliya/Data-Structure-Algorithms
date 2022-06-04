package com.company.amazon;

import java.util.ArrayList;
import java.util.List;

public class IntegerToBinary {

    public static void main(String[] args) {
        printBinary(7);
        printBinary(14);
    }

    public static void printBinary(int number) {
        List<Integer> list = new ArrayList<>();

        while (number > 0) {
            list.add(number%2);
            number = number/2;
        }
        System.out.println(list);
    }
}
