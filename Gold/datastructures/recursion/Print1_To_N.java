package com.datastructures.recursion;

/**
 * @author neeraj on 25/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class Print1_To_N {

    public static void main(String[] args) {
        print(10);
    }

    public static void print(int N) {
        if(N == 0) return;
        print(N-1);
        System.out.println(N);
    }
}
