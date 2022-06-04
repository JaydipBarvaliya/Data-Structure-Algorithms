package com.company.facebook;

public class RotationalCipher {

    public static void main(String[] args) {
        System.out.println(rotationalCipher("Zebra-493", 3));
        System.out.println(rotationalCipher("abcdefghijklmNOPQRSTUVWXYZ0123456789", 39));
    }

    static String rotationalCipher(String input, int rotationFactor) {
        // Write your code here
        StringBuilder res = new StringBuilder();
        for (char ch : input.toCharArray()) {

            if (ch >= 65 && ch <= 90) {
                res.append((char)(((ch+rotationFactor-'A') % 26) + 'A'));
            } else if (ch >= 97 && ch <= 122) {
                res.append((char)(((ch+rotationFactor-'a') % 26) + 'a'));
            } else if (ch >= 48 && ch <= 57) {
                int digit = ch - '0';
                digit = (digit + rotationFactor) % 10;
                res.append(digit);
            } else {
                // Non-AlphaNumeric
                res.append(ch);
            }
        }
        return res.toString();
    }


}
