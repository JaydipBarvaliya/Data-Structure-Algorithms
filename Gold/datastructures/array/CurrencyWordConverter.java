package com.datastructures.array;

/**
 * Created by jaine03 on 03/09/17.
 */
public class CurrencyWordConverter {

    private static final String[] twodigit = {
            "",
            " ten",
            " twenty",
            " thirty",
            " forty",
            " fifty",
            " sixty",
            " seventy",
            " eighty",
            " ninety"
    };

    private static final String[] oneDigit = {
            "",
            " one",
            " two",
            " three",
            " four",
            " five",
            " six",
            " seven",
            " eight",
            " nine",
            " ten",
            " eleven",
            " twelve",
            " thirteen",
            " fourteen",
            " fifteen",
            " sixteen",
            " seventeen",
            " eighteen",
            " nineteen"
    };

    private static String word = "";

    private static String convert(int currency) {
        String currencyInString = String.valueOf(currency);
        int lengthOfCurrency = currencyInString.length();

        if (lengthOfCurrency == 1) {
            word += oneDigit[currency];
        } else if (lengthOfCurrency == 2) {
            if (currency < 20) {
                word += oneDigit[currency];
            } else {
                int n = currency / 10;
                word += twodigit[n];
                word += oneDigit[currency % 10];
            }
        } else if (lengthOfCurrency == 3) {
            int rem = currency / 100;
            word += oneDigit[rem];
            word += " hundred";
            convert(currency % 100);
        } else if (lengthOfCurrency >= 4 && lengthOfCurrency <= 6) {
            convert(currency/1000);
            word += " thousand";
            convert(currency%1000);
        }

        return word;
    }

    public static void main(String[] args) {
        int currency = 12999;
        System.out.println("Converting currency " + currency + " into words :" + convert(currency));
    }

}
