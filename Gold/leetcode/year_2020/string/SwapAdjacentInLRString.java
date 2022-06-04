package com.leetcode.year_2020.string;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/swap-adjacent-in-lr-string/
 * <p>
 * Some cars, road weird question. You are given starting state of cars.
 * something like R _ _ L. Now, this example has 4 lanes.
 * R means this car has put the indicator on to move R, this car can only move right or stay where it wants to be.
 * and the car blinking L can choose to stay there or move left only. but cars can't collide.
 * You are given a end state and you have to tell if that state is possible.
 * <p>
 * One more example can _ R can not have R _ as the final state.
 * <p>
 * Now with this example it's pretty clear, A car giving left indicator can either remain in the same lane
 * or can just go to left, and vice-versa for Right Indicator.
 * <p>
 * So what does it mean, that if a car was giving Left Indicator in the start state
 * there is no possible way that the same car can move into right in the end state.
 * <p>
 * And Also for a car giving Right indicator can only move in right and there is
 * no possible way that the same car can move into left direction in the end state.
 * <p>
 * So we have to just compare L and R both position in the start to the end State
 * and validate (for all L of start) should not be less than (for all L of End)
 * similarly (for all R of start) should not be greater than (for all R of End).
 * <p>
 * If it's happening we will bound to have collision, else car can move freely.
 * <p>
 * |  |   |   |   |
 * |  |   |   |   |
 * | X| L | X | R |  ====> X means empty lane
 * ------------
 * <p>
 * So final valid answers are L|X|X|R or |X|L|X|R
 * but invalid ones are |X|X|L|R
 */
public class SwapAdjacentInLRString {
    public static void main(String[] args) {
        System.out.println(canTransform("RXXLRXRXL", "XRLXXRRLX"));
        System.out.println(canTransform("X", "L"));
        System.out.println(canTransform("LLR", "RRL"));
        System.out.println(canTransform("XL", "LX"));
        System.out.println(canTransform("XLLR", "LXLX"));
        System.out.println(canTransform("XXXXXLXXXX", "LXXXXXXXXX"));
        System.out.println(canTransform("LX", "XL"));
    }

    public static boolean canTransform(String start, String end) {
        // XL ===> LX and RX ====> XR
        /**
         * Key observations:
         * There are three kinds of characters, ‘L’, ‘R’, ‘X’.
         * Replacing XL with LX = move L to the left by one
         * Replacing RX with XR = move R to the right by one
         * If we remove all the X in both strings, the resulting strings should be the same.
         *
         *              RXXLRXRXL     XRLXXRRLX
         * Removing X-> R  LR R L      RL  RRL (Both should be same)
         */
        if (!start.replaceAll("X", "").equals(end.replaceAll("X", ""))) return false;

        List<Integer> startL = new ArrayList<>();
        List<Integer> endL = new ArrayList<>();
        List<Integer> startR = new ArrayList<>();
        List<Integer> endR = new ArrayList<>();

        /**
         * We know that L will be moving only left and R will be moving only right
         * So if just in case "L" from the start occurs before "L" from End, that's wrong, we won't be able to transform
         * and if just in case "R" from the start occurs after "R" from End, that's wrong, we won't be able to transform
         *
         * https://algomonster.medium.com/leetcode-777-swap-adjacent-in-lr-string-google-interview-question-3574d2c77d19
         * https://leetcode.com/problems/swap-adjacent-in-lr-string/discuss/873004/Easy-to-understand-explanation-with-PICTURE
         */
        for (int i = 0; i < start.length(); i++) {
            if (start.charAt(i) == 'L') {
                startL.add(i);
            } else if (start.charAt(i) == 'R') {
                startR.add(i);
            }
        }

        for (int i = 0; i < end.length(); i++) {
            if (end.charAt(i) == 'L') {
                endL.add(i);
            } else if (end.charAt(i) == 'R') {
                endR.add(i);
            }
        }

        for (int i = 0; i < startL.size(); i++) {
            if (startL.get(i) < endL.get(i)) {
                return false;
            }
        }

        for (int i = 0; i < endR.size(); i++) {
            if (startR.get(i) > endR.get(i)) {
                return false;
            }
        }
        return true;
    }
}
