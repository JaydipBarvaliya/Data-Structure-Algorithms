package com.company.amazon;

import java.util.HashMap;
import java.util.Map;

public class Splitwise_MinimizeCashFlowInFriends {

    private static Map<Integer, String> personMap = new HashMap<>();

    static {
        personMap.put(0, "Jitu");
        personMap.put(1, "Sagar");
        personMap.put(2, "Neeraj");
    }

    public static void main(String[] args) {
        int balanceSheet[][] = {{0, 1000, 2000},
                {0, 0, 5000},
                {0, 0, 0},};

        splitWisely(balanceSheet);
    }

    public static void splitWisely(int[][] balanceSheet) {
        int[] netAmount = getNetAmount(balanceSheet);
        minimizeCashFlow(netAmount);
    }

    /**
     * Find Maximum Creditor and Maximum Debitor
     * <p>
     * Transfer Minimum Net Amount from Maximum Debtor to Max Creditor
     *
     * @param netAmount
     */
    private static void minimizeCashFlow(int[] netAmount) {
        int maxCreditor = getMaxCreditor(netAmount);
        int maxDebtor = getMaxDebtor(netAmount);

        if (netAmount[maxCreditor] == 0 && netAmount[maxDebtor] == 0) { // Base condition
            return;
        }

        int minNetAmount = Math.min(-netAmount[maxDebtor], netAmount[maxCreditor]);

        netAmount[maxCreditor] -= minNetAmount;
        netAmount[maxDebtor] += minNetAmount;

        System.out.println(personMap.get(maxDebtor) + " pays " + minNetAmount + "  to " + personMap.get(maxCreditor));

        minimizeCashFlow(netAmount);
    }

    private static int getMaxCreditor(int[] netAmount) {
        int maxCreditorIndex = 0;
        int maxCredit = Integer.MIN_VALUE;

        for (int i = 0; i < netAmount.length; i++) {
            if (maxCredit < netAmount[i]) {
                maxCredit = netAmount[i];
                maxCreditorIndex = i;
            }
        }
        return maxCreditorIndex;
    }

    private static int getMaxDebtor(int[] netAmount) {
        int maxDebtorIndex = 0;
        int maxDebit = Integer.MAX_VALUE;

        for (int i = 0; i < netAmount.length; i++) {
            if (maxDebit > netAmount[i]) {
                maxDebit = netAmount[i];
                maxDebtorIndex = i;
            }
        }
        return maxDebtorIndex;
    }

    public static int[] getNetAmount(int[][] balanceSheet) {
        int[] netAmount = new int[balanceSheet.length];

        for (int i = 0; i < balanceSheet.length; i++) {
            for (int j = 0; j < balanceSheet[i].length; j++) {
                netAmount[i] += balanceSheet[j][i] - balanceSheet[i][j]; // Credited Amount - Debited Amount
            }
        }
        return netAmount;
    }
}
