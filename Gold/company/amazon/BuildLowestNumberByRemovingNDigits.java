package com.company.amazon;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BuildLowestNumberByRemovingNDigits {

    public static void main(String[] args) {
        Integer[] inputs = {4325043, 765028321, 121168, 567891234};
        Integer[] removing = {3, 5, 2, 5};
        int counter = 0;
        for (Integer num : inputs) {
            List<Integer> list = prepareListFromNumber(num.toString());
            System.out.println(getLowestNumberByRemoving(list, removing[counter++]));
        }
    }

    private static Stack<Integer> getLowestNumberByRemoving(List<Integer> digits, int remove) {
        Stack<Integer> stack = new Stack<>();
        Integer digit = 0;
        int totalDigitsRequired = digits.size() - remove;
        for (int i = 0; i < digits.size(); i++) {
            digit = digits.get(i);

            while (!stack.isEmpty() && stack.peek() > digit) {

                // Check that if we pop this element, will we be able to make the final
                // result of length size - removed element
                if ((stack.size() + (digits.size() - i)) > totalDigitsRequired) {
                    stack.pop();
                } else {
                    break;
                }
            }
            if (stack.size() < digits.size() - remove) {
                stack.push(digit);
            }
        }
        return stack;
    }

    private static List<Integer> prepareListFromNumber(String s) {
        List<Integer> list = new ArrayList<>();
        for (char c : s.toCharArray()) {
            list.add(c - '0');
        }
        return list;
    }
}
