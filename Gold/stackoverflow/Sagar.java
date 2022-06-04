package com.stackoverflow;

import java.util.ArrayList;
import java.util.Arrays;

public class Sagar {
    private static Integer counter = 1;

    /**
     * 1
     * 2 6
     * 3 7 10
     * 4 8 11 13
     * 5 9 12 14 15
     */
    public static void printPattern(int N) {
        int offset = 0, value = 0;
        for (int i = 1; i <= N; i++) {
            offset = N - 1;
            value = i;
            for (int j = 1; j <= i; j++) {
                if (j > 1) {
                    value = value + offset;
                    System.out.print((value) + " ");
                    offset--;
                } else {
                    System.out.print(i + " ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
//        String[] sentence = {"sagar", "batra", "is", "learning", "java"};
//        printMyStringArray(sentence);
//               Arrays.sort(sentence);
//        printMyStringArray(sentence);
//        for (int i = 0; i < sentence.length; i++) {
//            for (int j = 0; j < sentence.length - 1; j++) {
//                String A = sentence[j];
//                String B = sentence[j + 1];
//
//                int whoIsBigger = whoIsBigger(A, B);
//                if (whoIsBigger == 1) {
//                    sentence[j] = B;
//                    sentence[j + 1] = A;
//                }
//            }
//        }
//        printMyStringArray(sentence);

//        method1();
//        MyList list = new MyList();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        System.out.println(list.toString());
//
//
//        try {
//            parentExceptionMethod();
//        } catch (ChildException ex) {
//            ex.printStackTrace();
//        } catch (ParentException ex) {
//            ex.printStackTrace();
//        }

        printPattern(10);
    }

    public static void childExceptionMethod() throws ChildException {
        throw new ChildException("I am the Child Exception");
    }

    public static void parentExceptionMethod() throws ParentException {
        throw new ParentException("I am the Parent Exception");
    }

    public static void method1() {
        if (counter <= 100) {
            method2();
        }
    }

    public static void method2() {
        System.out.println(counter++);
        method1();
    }

    public static void printMyStringArray(String[] sentence) {
        for (String s : sentence) {
            System.out.print(s + "\t");
        }
        System.out.println();
    }

    public static int whoIsBigger(String s1, String s2) {
        int c1 = -1, c2 = -1;

        while (c1 < s1.length() && c2 < s2.length()) {
            c1++;
            c2++;
            if (s1.charAt(c1) == s2.charAt(c2)) {
                continue;
            } else if (s1.charAt(c1) > s2.charAt(c2)) {
                return 1;
            } else {
                return 2;
            }
        }
        return 1;
    }

    public static int myStrCmp(String a, String b) {
        int i = 0;
        int l = 0;
        int s = 0;
        int r = 0;
        if (b.length() <= a.length())
            l = b.length();
        else
            l = a.length();
        for (i = 0; i < l; i++) {
            if (a.charAt(i) > b.charAt(i)) {
                r = 1;
                break;
            }
            if (a.charAt(i) < b.charAt(i)) {
                r = -1;
                break;
            } else {
                continue;
            }
        }
        return r;
    }
}
