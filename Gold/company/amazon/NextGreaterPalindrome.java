package com.company.amazon;

import java.util.Arrays;
import java.util.stream.Collectors;

public class NextGreaterPalindrome {

    public static void main(String[] args) {
        printNextGreaterPalindrome("1");
        printNextGreaterPalindrome("9");
        printNextGreaterPalindrome("89");
        printNextGreaterPalindrome("90");
        printNextGreaterPalindrome("99");
        printNextGreaterPalindrome("900");
        printNextGreaterPalindrome("999");
        printNextGreaterPalindrome("899");
        printNextGreaterPalindrome("123321");
        printNextGreaterPalindrome("12921");
        printNextGreaterPalindrome("12345");
        printNextGreaterPalindrome("713322");
        printNextGreaterPalindrome("783322");
        printNextGreaterPalindrome("123450000004");
    }

    /**
     * 3 Cased to be handled
     * <p>
     * Case 1: When all the integers are 9 we have to handle specifically
     * Case 2: When input is already a palindrome (and digit is not all 9)
     * Case 3: When input is not a palindrome
     *
     * @param input
     */
    public static void printNextGreaterPalindrome(String input) {
        int[] arr = convertStringToIntArray(input);

        // if Single digit number
        if (arr.length == 1) {
            System.out.print("Next Smallest Palindrome larger than number : " + input + " is ");
            if (arr[0] == 9) {
                System.out.println(11);
            } else {
                System.out.println(arr[0] + 1);
            }
        } else if (all9s(arr)) { // First check if all 9's are there
            System.out.print("Next Smallest Palindrome larger than number : " + input + " is ");
            System.out.print(1);
            for (int i = 0; i < arr.length; i++) {
                System.out.print(0);
            }
            System.out.println(1);
        } else {  // Case 2 and Case 3
            handlePalindromeForOtherCases(arr);
            System.out.print("Next Smallest Palindrome larger than number : " + input + " is ");
            printArr(arr);
        }
    }

    private static void handlePalindromeForOtherCases(int[] arr) {

        // Let's divide the input into 2 parts
        int mid = arr.length / 2;
        int carry = 1;
        int leftPointer = mid - 1;
        int rightPointer = arr.length % 2 == 0 ? mid : mid + 1;


        while (leftPointer >= 0 && rightPointer < arr.length) {
            if (arr[leftPointer] == arr[rightPointer]) {
                leftPointer--;
                rightPointer++;
            } else {
                break;
            }
        }

        // Palindrome Case 123321  OR 12921
        if (leftPointer < 0 && rightPointer >= arr.length) {

            // Increment the middle node and propagate carry till the end while keep mirroring as well
            leftPointer = mid - 1;
            rightPointer = arr.length % 2 == 0 ? mid : mid + 1;

            // If Number is Even length then we will not increment the mid pointer 1 2 3 3 2 1 ==> 1 2 4 4 2 1
            // We will increment that only in case of odd pointer 1 2 3 2 1 ==>  1 2 4 2 1

            addCarryAndPropagate(arr, carry);

        } else { // Number is not a palindrome ... 1 2 3 4 5...... 1 2 3 4 5 6 ........ 8 3 4 2 2 4 6 9


            // Now there a 2 cases value at leftPointer is smaller than right pointer
            // If this is the case then increment the middle node and propagate the carry
            // and simultaneously keep on putting the left half to the right half
            if (arr[leftPointer] < arr[rightPointer]) {
                addCarryAndPropagate(arr, carry);
            } else {
                // Case where value at left pointer is greater than right pointer
                // If this is the case copy the left half into the right half
                copyLeftHalfToRightHalf(arr, leftPointer, rightPointer);
            }
        }
    }

    private static void addCarryAndPropagate(int[] arr, int carry) {
        int mid = arr.length / 2;
        int leftPointer = mid - 1;
        int rightPointer = arr.length % 2 == 0 ? mid : mid + 1;
        if (arr.length % 2 != 0) {
            arr[mid] += carry;
            carry = arr[mid] / 10;
            arr[mid] %= 10;
        }

        while (leftPointer >= 0) {
            arr[leftPointer] = arr[leftPointer] + carry;
            carry = arr[leftPointer] / 10;
            arr[rightPointer] = arr[leftPointer];

            leftPointer--;
            rightPointer++;
        }
    }

    private static void copyLeftHalfToRightHalf(int[] arr, int leftPointer, int rightPointer) {
        while (leftPointer >= 0) {
            arr[rightPointer] = arr[leftPointer];
            leftPointer--;
            rightPointer++;
        }
    }

    private static int[] convertStringToIntArray(String input) {
        return input.chars().map(s -> s - '0').toArray();
    }

    private static void printArr(int[] arr) {
        System.out.println(Arrays.stream(arr).boxed().collect(Collectors.toList()));
    }

    private static boolean all9s(int[] arr) {
        for (int i : arr) {
            if (i != 9)
                return false;
        }
        return true;
    }
}
