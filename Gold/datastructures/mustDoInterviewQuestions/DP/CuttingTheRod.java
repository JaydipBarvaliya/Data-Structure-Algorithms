package com.datastructures.mustDoInterviewQuestions.DP;

/**
 * http://www.geeksforgeeks.org/dynamic-programming-set-13-cutting-a-rod/
 *
 * @author jaine03
 */
public class CuttingTheRod {

    int lengthOfRod = 5;
    int price[] = {2, 5, 7, 8};
    int cost[][] = new int[price.length][lengthOfRod + 1];

    public static void main(String[] args) {
        CuttingTheRod cr = new CuttingTheRod();
        cr.initialize();
        cr.cutRod();
        cr.print();
    }

    /**
     * This method will set up the cost for the 1cm length
     */
    public void initialize() {
        for (int i = 0; i < lengthOfRod + 1; i++)
            cost[0][i] = i * price[0];
    }

    /**
     * This method will set up the cost for all remaining cuts
     */
    public void cutRod() {
        for (int i = 1; i < price.length; i++) {
            for (int j = 1; j < cost[i].length; j++) {
                if (j - i - 1 >= 0) {
                    cost[i][j] = Math.max(cost[i - 1][j], cost[i][j - i - 1] + price[i]);
                } else {
                    cost[i][j] = cost[i - 1][j];
                }
            }
        }
        System.out.println(cost[price.length-1][lengthOfRod]);
    }

    void print() {
        for (int i = 0; i < price.length; i++) {
            for (int j = 0; j <= lengthOfRod; j++)
                System.out.printf(cost[i][j] + "\t");
            System.out.println();
        }
    }
}
