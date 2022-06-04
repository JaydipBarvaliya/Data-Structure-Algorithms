package com.company.amazon;

/**
 * Boyer–Moore majority vote Algorithm
 * <p>
 * A majority element in an array A[] of size n is an element that appears more than n/2 times
 * (and hence there is at most one such element).
 * <p>
 * Input : {3, 3, 4, 2, 4, 4, 2, 4, 4}
 * Output : 4
 * <p>
 * Input : {3, 3, 4, 2, 4, 4, 2, 4}
 * Output : No Majority Element
 */
public class MajorityElementInArray {

    public static void main(String[] args) {
        getMajorityElement(new int[]{3, 3, 4, 2, 4, 4, 2, 4, 4});
        getMajorityElement(new int[]{3, 3, 4, 2, 4, 4, 2, 4});
    }

    /**
     * This algo is divided into 2 steps
     * <p>
     * In Step 1 we identify the find the majority element by counting it's occurrence in 1 go
     * In Step 2 we confirm that the element found in step 1 is actually a Majority Element.
     *
     * @param arr
     */
    public static void getMajorityElement(int[] arr) {

        // Step 1
        int count = 0;
        int CANDIDATE = 0;
        for (int i = 0; i < arr.length; i++) {
            if (count == 0) {
                CANDIDATE = arr[i];
                count = 1;
            } else {
                if (CANDIDATE == arr[i]) { // Check if this element is the one which got selected for Voting
                    count++; // Is so then increment it's frequency
                } else {
                    count = 0;
                }
            }
        }

        if (count > 0) { // We have a candidate
            // Step 2
            int N = arr.length;
            count = 0; // Now the counter is being use to count the actual occurrence

            for (int i : arr) {
                if (i == CANDIDATE) {
                    count++;
                }
            }

            if (count > N / 2) {
                System.out.println("Majority Element is " + CANDIDATE);
            } else {
                System.out.println("No Majority Element");
            }
        } else {
            System.out.println("No Majority Element");
        }
    }
}
