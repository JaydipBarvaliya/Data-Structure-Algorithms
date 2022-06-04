package com.leetcode.year_2020.binary_search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/discuss/interview-experience/272412/Facebook-Onsite-Interview-Experience/263509
 * Calculate tax if Salary and Tax Brackets are given as list in the form
 * [ [10000, 0.3],[20000, 0.2], [30000, 0.1], [null, .1]]
 * <p>
 * https://blog.taxact.com/how-tax-brackets-work/
 * <p>
 * How tax brackets work
 * Say you’re single with no dependents, and your taxable income is $9,000. Your marginal tax rate, according to the Federal Income Brackets chart below, is 10 percent. You pay $900 in income tax. That’s simple.
 * <p>
 * What if your taxable income is $19,000?
 * <p>
 * As a Single filer, you’re now in the 12 percent tax bracket. That doesn’t mean you pay 12 percent on all your income, however.
 * <p>
 * You pay 10 percent on the first $9,525, plus 12 percent of the amount over $9,525.
 * <p>
 * Here’s the math:
 * <p>
 * First tax bracket: $9,525 X 10% =	$952.50
 * Second tax bracket: ($19,000 – $9,525) X 12%  =	$1,137.00
 * Total income tax:	$2,089.50
 */
public class TaxBracketFacebookProblem {
    private List<TaxBracket> taxBracketList;

    static class TaxBracket {
        double maxIncomeForThisBracket;
        double tax;

        public TaxBracket(final double maxIncomeForThisBracket, final double tax) {
            this.maxIncomeForThisBracket = maxIncomeForThisBracket;
            this.tax = tax;
        }

        @Override
        public String toString() {
            return "{" +
                    "maxIncomeForThisBracket=" + maxIncomeForThisBracket +
                    ", taxBracket=" + tax +
                    '}';
        }
    }

    public TaxBracketFacebookProblem(List<TaxBracket> taxBracketList) {
        Collections.sort(taxBracketList, (a, b) -> (int) (a.maxIncomeForThisBracket - b.maxIncomeForThisBracket));
        this.taxBracketList = taxBracketList;
        for (TaxBracket taxBracket : taxBracketList) {
            System.out.println(taxBracket);
        }
    }

    private double calculateTax(double salary) {
        final int bracket = getBracket(salary);
        System.out.println("Bracket for salary " + salary + " is " + bracket);

        // Now we have to calculate tax from start to the designated bracket
        double incomeTax = 0;
        for (int i = 0; i <= bracket && salary > 0; i++) {
            final TaxBracket taxBracket = taxBracketList.get(i);
            incomeTax += Math.min(taxBracket.maxIncomeForThisBracket, salary) * taxBracket.tax;
            salary -= taxBracket.maxIncomeForThisBracket;
        }
        return incomeTax;
    }

    private int getBracket(final double salary) {
        // Do binary search and find the right bracket for our tax
        int low = 0, high = taxBracketList.size() - 1;
        int bracket = 0;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (taxBracketList.get(mid).maxIncomeForThisBracket > salary) {
                bracket = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return bracket;
    }

    private static List<TaxBracket> constructTaxBracketList(double[][] taxBrackets) {
        final List<TaxBracket> taxBracketList = new ArrayList<>();
        for (double[] bracket : taxBrackets) {
            taxBracketList.add(new TaxBracket(bracket[0], bracket[1]));
        }
        return taxBracketList;
    }

    public static void main(String[] args) {
        final List<TaxBracket> taxBracketList = constructTaxBracketList(new double[][]
                {{38700, .12}, {9525, .10}, {82500, .22}, {157500, .24}, {Double.MAX_VALUE, .32}});
        TaxBracketFacebookProblem taxBracket = new TaxBracketFacebookProblem(taxBracketList);
        System.out.println(taxBracket.calculateTax(38699));
        System.out.println(taxBracket.calculateTax(38700));
        System.out.println(taxBracket.calculateTax(38701));
    }
}
