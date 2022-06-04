package com.interviewbit.math;

import com.util.LogUtil;

/**
 * @author neeraj on 2019-07-27
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PowerOfTwoIntegers {

    static int X = 0;
    static int Y = 0;

    public static void main(String[] args) {

        int N = 50;
        for (int i = 1; i <= N; i++) {
            int number = i;
            LogUtil.logInSingleLine("Can We represent [" + number + "] as power of two integer ?");
            int result = isPower(number);
            System.out.print(result == 1 ? true : false);
            if (result == 1) {
                LogUtil.logIt(" and those two integers are X,Y = " + X + "^" + Y + " = " + Math.pow(X, Y), false);
            } else {
                LogUtil.newLine();
            }
        }
    }

    public static int isPower(int A) {
        int temp = A;
        if (A == 1) {
            return 1;
        }
        for (int i = 2; i <= Math.sqrt(A); i++) {
            X = 0;
            Y = 0;
            temp = A;
            while (temp % i == 0) {
                Y++;
                temp = temp / i;
            }
            if (temp == 1) {
                X = i;

                return 1;
            }
        }
        return 0;
    }
}
