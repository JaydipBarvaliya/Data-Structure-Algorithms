package com.stackoverflow;

/* package whatever; // don't place package name! */

class StringCountAndChange {

    public static void main(String[] args) {
        String s = "sagar";

        String h = "neeraj";
        System.out.println(s.length() + h.length());

        StringBuffer sagarBuffer = new StringBuffer(s);
        if (sagarBuffer.charAt(0) >= 97 && sagarBuffer.charAt(0) <= 122) {
            sagarBuffer.setCharAt(0, (char) (sagarBuffer.charAt(0) - 32));
        }
        System.out.println(sagarBuffer.toString());

        StringBuffer neerajBuffer = new StringBuffer(h);
        if (neerajBuffer.charAt(0) >= 97 && neerajBuffer.charAt(0) <= 122) {
            neerajBuffer.setCharAt(0, (char) (neerajBuffer.charAt(0) - 32));
        }
        System.out.println(neerajBuffer.toString());
    }


}