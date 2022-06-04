package com.company.amazon;

public class ExcelColumnNameFromNumber {

    public static void main(String[] args) {
        printString(26);
        printString(51);
        printString(52);
        printString(80);
        printString(676);
        printString(702);
        printString(705);
        printString(90802332);


        // Fetch Number from Excel Column, Simple Binary to Decimal
        printNumber("A");
        printNumber("Z");
        printNumber("AA");
        printNumber("AB");
        printNumber("GPRFYJ");
    }

    public static void printString(int number) {
        System.out.println(getExcelColumn(number));
    }

    public static void printNumber(String str) {
        System.out.println(getNumberFromExcelColumn(new StringBuffer(str).reverse().toString().toCharArray()));
    }

    public static String getExcelColumn(int number) {
        StringBuffer result = new StringBuffer();

        while (number > 0) {
            int remainder = number % 26;
            if (remainder == 0) { // It's a multiple
                result.append("Z");
                number = (number - 1) / 26; // Since Z is 25th w.r.t A
            } else {
                result.append((char) (remainder - 1 + 'A')); // We are calculating the alphabet after remainder distance from A
                number = number / 26;
            }
        }
        return result.reverse().toString();
    }

    public static int getNumberFromExcelColumn(char[] arr) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result += ((int) (arr[i] - 'A') + 1) * (int) Math.pow(26, i);
        }
        return result;
    }
}
