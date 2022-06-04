package com.stackoverflow;

public class RemoveSpace {

    public static void main(String[] args) {
        String s = "                            What is java";

        StringBuffer t = new StringBuffer(s);
        for (int i = 0; i < t.length(); i++) {
            if (t.charAt(i) == ' ') {
                t.deleteCharAt(i);
                i--;
            } else {
                break;
            }
        }
        System.out.println(t.toString());
    }
}
