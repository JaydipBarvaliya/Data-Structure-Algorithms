package com.company.amazon;

public class Fibonaci {

    public static void main(String[] args) {
        for(int i=0;i<10;i++) {
            System.out.print(fibonaci(i)+"\t");
        }
    }

    public static int fibonaci(int n) {
        int a = 0, b = 1, c, i;
        if (n == 0)
            return a;

        for (i = 2; i <= n; i++) {
            c = a + b;
            a = b;
            b = c;
        }
        return b;
    }
}
