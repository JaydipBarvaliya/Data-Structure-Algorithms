package com.stackoverflow;

public class CharCount {

    public static void main(String[] args) {
        String s = "sagarbatra";
        int counter;
        char[] myArray = new char[s.length()];


        for (int i = 0; i < s.length(); i++) {
            counter = 1;
            char temp = s.charAt(i);
            for (int j = i + 1; j < s.length(); j++) {
                if (s.charAt(j) == temp) {
                    counter++;
                }
            }
            if (!myArrayContains(myArray, temp)) {
                myArray[i] = temp;
                System.out.println(s.charAt(i) + "=" + counter);
            }
        }
    }

    public static Boolean myArrayContains(char[] myArray, char characterToFind) {
        for (char ch : myArray) {
            if (ch == characterToFind) {
                return true;
            }
        }
        return false;
    }
}
