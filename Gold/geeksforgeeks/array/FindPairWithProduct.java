package com.geeksforgeeks.array;

import java.util.ArrayList;
import java.util.List;

public class FindPairWithProduct {

    public static void main(String[] args) {
        int[][] arr = {
                {10, 20, 9, 40},
                {10, 20, 9, 40},
                {-10, 20, 9, -40},
                {-10, 20, 9, 40},
                {0, 20, 9, 40},
                {2, 2, 3}
        };
        int[] products = {400, 190, 400, -400, 0, 6};
        int counter = 0;
        for (int[] input : arr) {
            System.out.println(getStringRepresentation(input) + "-->" + isPairExist(input, products[counter++]));
        }
    }

    public static String getStringRepresentation(int[] arr) {
        StringBuffer sb = new StringBuffer();
        sb.append("{");
        for (int i : arr) {
            sb.append(i + ",");
        }
        sb.append("}");
        return sb.toString();
    }

    public static boolean isPairExist(int[] input, int product) {
        List<Integer> helperList = new ArrayList<>();
        int temp = 0;

        for (int i : input) {
            if (helperList.contains(i) || (product == 0 && product == i)) {
                return true;
            }
            if (product % i == 0) {
                temp = product / i;
                helperList.add(temp);
            }
        }
        return false;
    }
}
