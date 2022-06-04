package com.leetcode.problems.hard;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/frog-jump/
 * <p>
 * A frog is crossing a river. The river is divided into x units and at each unit there may or may not exist a stone. The frog can jump on a stone, but it must not jump into the water.
 * <p>
 * Given a list of stones' positions (in units) in sorted ascending order, determine if the frog is able to cross the river by landing on the last stone. Initially, the frog is on the first stone and assume the first jump must be 1 unit.
 * <p>
 * If the frog's last jump was k units, then its next jump must be either k - 1, k, or k + 1 units. Note that the frog can only jump in the forward direction.
 * <p>
 * Note:
 * <p>
 * The number of stones is ≥ 2 and is < 1,100.
 * Each stone's position will be a non-negative integer < 231.
 * The first stone's position is always 0.
 * Example 1:
 * <p>
 * [0,1,3,5,6,8,12,17]
 * <p>
 * There are a total of 8 stones.
 * The first stone at the 0th unit, second stone at the 1st unit,
 * third stone at the 3rd unit, and so on...
 * The last stone at the 17th unit.
 * <p>
 * Return true. The frog can jump to the last stone by jumping
 * 1 unit to the 2nd stone, then 2 units to the 3rd stone, then
 * 2 units to the 4th stone, then 3 units to the 6th stone,
 * 4 units to the 7th stone, and 5 units to the 8th stone.
 * Example 2:
 * <p>
 * [0,1,2,3,4,8,9,11]
 * <p>
 * Return false. There is no way to jump to the last stone as
 * the gap between the 5th and 6th stone is too large.
 *
 * @author neeraj on 2019-08-05
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FrogJump_403 {

    public static void main(String[] args) {
        System.out.println(canCrossSimplerVersion(new int[]{0, 1, 3, 5, 6, 8, 12, 17}));
        System.out.println(canCrossSimplerVersion(new int[]{0, 1, 2, 3, 4, 8, 9, 11}));
        System.out.println(canCrossSimplerVersion(new int[]{0, 2}));
        System.out.println(canCrossSimplerVersion(new int[]{0, 1, 3, 6, 10, 13, 15, 16, 19, 21, 25}));
        System.out.println(canCrossSimplerVersion(new int[]{0, 1, 3, 6, 10, 13, 15, 18}));
    }

    public static boolean canCrossSimplerVersion(int[] stones) {
        HashSet<Integer> stoneSet = new HashSet<>();
        for (int stone : stones) {
            stoneSet.add(stone);
        }

        Stack<Integer> positions = new Stack<>();
        Stack<Integer> jumps = new Stack<>();
        int lastStone = stones[stones.length - 1];
        int position = 0;
        int jumpDistance = 0;

        positions.add(stones[0]);
        jumps.add(0);

        while (!positions.isEmpty()) {
            position = positions.pop();
            jumpDistance = jumps.pop();

            for (int i = jumpDistance - 1; i <= jumpDistance + 1; i++) {
                if (i <= 0) {
                    continue;
                }
                int nextPosition = position + i;
                if (nextPosition == lastStone) {
                    return true;
                } else if (stoneSet.contains(nextPosition)) {
                    positions.add(nextPosition);
                    jumps.add(i);
                }
            }
        }
        return false;
    }


    static class Decision {
        int node;
        // 1 for k+1
        // -1 for k-1
        int direction;

        public Decision(int node, int direction) {
            this.node = node;
            this.direction = direction;
        }

    }

    public static boolean canCross(int[] stones) {
        Decision poppedNode = null;
        Stack<Decision> decisionWhichCanBeQuestioned = new Stack<>();
        List<Integer> stonesList = Arrays.stream(stones).mapToObj(i -> i).collect(Collectors.toList());
        int k = 1;
        int L = 0;
        while (L < stones.length && stones[L] != stones[stones.length - 1]) {
            // Choices available

            // First Jump will always be of length k=1;
            if (poppedNode == null && L > 0 && stonesList.contains(stones[L] + (k + 1))) {

                poppedNode = null;
                decisionWhichCanBeQuestioned.add(new Decision(L, 1));
                L = stonesList.indexOf(stones[L] + (k + 1));
                k = k + 1;

            } else if (stonesList.contains(stones[L] + k)) {
                // Do Nothing just move
                poppedNode = null;
                L = stonesList.indexOf(stones[L] + (k));

            } else if (canJumpAtLocation(poppedNode, 1) && L > 0 && k > 1 && stonesList.contains(stones[L] + (k - 1))) {
                poppedNode = null;
                decisionWhichCanBeQuestioned.add(new Decision(L, -1));
                L = stonesList.indexOf(stones[L] + (k - 1));
                k = k - 1;

            } else {
                if (decisionWhichCanBeQuestioned.isEmpty()) {
                    return false;
                } else {
                    poppedNode = decisionWhichCanBeQuestioned.pop();
                    L = poppedNode.node;
                    k = k - (poppedNode.direction);
                }
            }
            // K-1, K , K+1
        }
        return true;
    }

    private static boolean canJumpAtLocation(Decision poppedNode, int direction) {
        return poppedNode == null || poppedNode.direction == direction;
    }
}
