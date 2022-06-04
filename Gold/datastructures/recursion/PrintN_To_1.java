package com.datastructures.recursion;

/**
 * @author neeraj on 26/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PrintN_To_1 {

    public static void main(String[] args) {
        print(10);
    }

    public static void print(int N) {
        if (N == 0) return;
        System.out.println(N);
        print(N - 1);
    }
}
