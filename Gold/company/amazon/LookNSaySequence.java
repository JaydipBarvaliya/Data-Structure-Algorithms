package com.company.amazon;

/**
 * 1
 * 11
 * 21
 * 1211
 * 111221
 * 312211
 */
public class LookNSaySequence {

    private static Integer N = 5;
    private static Integer recursiveCounter = 0;

    public static void main(String[] args) {
        System.out.println(1); // Base Condition
        lookNSaySequence("1");
    }

    public static void lookNSaySequence(String val) {
        String[] splittedString = val.split("");
        int counter = 1; // Every Integer is at least occurred once, So base condition is this
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < splittedString.length; i++) {
            // Compare current Character with adjacent character
            if (i + 1 < splittedString.length &&
                    splittedString[i].equalsIgnoreCase(splittedString[i + 1])) {
                counter++;
            } else { // When adjacent chars doesn't match, So append the new count
                buffer.append(counter).append(splittedString[i]);
                counter = 1; // Reset the counter, to start a fresh map as continuity broke at this index
            }
        }
        System.out.println(buffer.toString());

        // Recursive call the method with the updated String
        while (recursiveCounter++ < N) {
            lookNSaySequence(buffer.toString());
        }
    }
}