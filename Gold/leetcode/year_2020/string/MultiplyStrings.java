package com.leetcode.year_2020.string;

public class MultiplyStrings {

    public static void main(String[] args) {
        System.out.println(multiply("12", "2") + " and using calculator " + (12 * 2));
        System.out.println(multiply("123", "24") + " and using calculator " + (123 * 24));
        System.out.println(multiply("123", "45678") + " and using calculator " + (123 * 45678));
        System.out.println(multiply("4455889923232", "45678"));
        System.out.println(multiply("9", "9") + " and using calculator " + (9 * 9));
        System.out.println(multiply("12344", "0") + " and using calculator " + (12344 * 0));
    }

    public static String multiply(String num1, String num2) {
        if (num1.length() < num2.length()) return multiply(num2, num1);
        // "12345", num2 = "456"
        int t1;
        int t2 = num2.length() - 1;
        int carry = 0, multiply = 0;
        StringBuilder prev = null;
        StringBuilder res = new StringBuilder();

        int zerosToAdd = 0, zerosToAddCounter = 0;
        for (int i = t2; i >= 0; i--) {
            t1 = num1.length() - 1;
            res = new StringBuilder();
            int second = num2.charAt(i) - '0';
            carry = zerosToAddCounter = 0;
            while (zerosToAddCounter++ < zerosToAdd) {
                res.append('0');
            }
            while (t1 >= 0) {
                int first = num1.charAt(t1) - '0';
                multiply = (first * second) + carry;
                res.append(multiply % 10);
                carry = multiply / 10;
                t1--;
            }
            if (carry > 0) {
                res.append(carry);
            }
            res.reverse();
            if (prev != null) {
                // Add res to the previous
                res = addToNumbers(prev, res);
            }
            prev = res;
            zerosToAdd++;
        }
        return (res.charAt(0) == '0') ? "0" : res.toString();
    }

    public static StringBuilder addToNumbers(final StringBuilder prev, final StringBuilder res) {
        int t1 = prev.length() - 1;
        int t2 = res.length() - 1;
        int sum = 0, carry = 0;
        final StringBuilder str = new StringBuilder();
        while (t1 >= 0 || t2 >= 0) {
            sum = carry;
            if (t1 >= 0) {
                sum += prev.charAt(t1) - '0';
                t1--;
            }

            if (t2 >= 0) {
                sum += res.charAt(t2) - '0';
                t2--;
            }

            carry = sum / 10;
            str.append(sum % 10);
        }
        if (carry > 0) str.append(carry);
        return str.reverse();
    }
}
