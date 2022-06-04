package com.leetcode.year_2020;

import com.geeksforgeeks.array.ArrayRotation;
import com.util.LogUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author neeraj on 21/03/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class AsteroidCollision {

    public static void main(String[] args) {
        ArrayRotation.printArray(asteroidCollisionOptimized(new int[]{5, 10, -5}));
        ArrayRotation.printArray(asteroidCollisionOptimized(new int[]{8, -8}));
        ArrayRotation.printArray(asteroidCollisionOptimized(new int[]{10, -2, 5}));
        ArrayRotation.printArray(asteroidCollisionOptimized(new int[]{-10, 2, -5, 4, -3}));
        ArrayRotation.printArray(asteroidCollisionOptimized(new int[]{-2, -1, 1, 2}));
        ArrayRotation.printArray(asteroidCollisionOptimized(new int[]{-2, -2, 1, -2}));
        ArrayRotation.printArray(asteroidCollisionOptimized(new int[]{-2, 1, -2, -1}));
        ArrayRotation.printArray(asteroidCollisionOptimized(new int[]{1, -2, -2, -2}));
        ArrayRotation.printArray(asteroidCollisionOptimized(new int[]{1, -1, -2, -2}));
        ArrayRotation.printArray(asteroidCollisionOptimized(new int[]{-2, -2, 1, -1}));

        System.out.println("========================");

        ArrayRotation.printArray(asteroidCollisionOld(new int[]{5, 10, -5}));
        ArrayRotation.printArray(asteroidCollisionOld(new int[]{8, -8}));
        ArrayRotation.printArray(asteroidCollisionOld(new int[]{10, -2, 5}));
        ArrayRotation.printArray(asteroidCollisionOld(new int[]{-10, 2, -5, 4, -3}));
        ArrayRotation.printArray(asteroidCollisionOld(new int[]{-2, -1, 1, 2}));
        ArrayRotation.printArray(asteroidCollisionOld(new int[]{-2, -2, 1, -2}));
        ArrayRotation.printArray(asteroidCollisionOld(new int[]{-2, 1, -2, -1}));
        ArrayRotation.printArray(asteroidCollisionOld(new int[]{1, -2, -2, -2}));
        ArrayRotation.printArray(asteroidCollisionOld(new int[]{1, -1, -2, -2}));
        ArrayRotation.printArray(asteroidCollisionOld(new int[]{-2, -2, 1, -1}));
    }

    public static int[] asteroidCollisionOptimized(int[] asteroids) {
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        while (i < asteroids.length) {
            if (asteroids[i] > 0) {
                stack.push(asteroids[i]);
            } else {
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() < Math.abs(asteroids[i])) {
                    stack.pop();
                }
                if (stack.isEmpty() || stack.peek() < 0) {
                    stack.push(asteroids[i]);
                } else if (stack.peek() == Math.abs(asteroids[i])) {
                    stack.pop();
                }
            }
            i++;
        }
        int[] result = new int[stack.size()];
        int count = result.length - 1;
        while (!stack.isEmpty()) {
            result[count--] = stack.pop();
        }
        return result;
    }

    public static int[] asteroidCollision(int[] asteroids) {
        System.out.println("Answer for : " + LogUtil.getArrayAsString(asteroids));
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < asteroids.length; i++) {
            stack.push(i);
            if (asteroids[i] > 0) break;
        }

        if (!stack.isEmpty()) {
            int item = stack.peek();
            for (int i = item + 1; i < asteroids.length; i++) {
                if (asteroids[i] > 0 || stack.isEmpty()) {
                    stack.push(i);
                } else { // Now we can have a collision
                    while (true && !stack.isEmpty()) {
                        item = stack.peek();
                        if (asteroids[item] < 0) {
                            stack.push(i);
                            break;
                        }
                        if (asteroids[item] > Math.abs(asteroids[i])) {
                            break;
                        } else if (asteroids[item] < Math.abs(asteroids[i])) {
                            stack.pop();
                            if (stack.isEmpty() || asteroids[stack.peek()] < 0) {
                                stack.push(i);
                                break;
                            }
                        } else {
                            stack.pop();
                            break;
                        }
                    }
                }
            }
        }
        int[] result = new int[stack.size()];
        int count = result.length - 1;
        while (!stack.isEmpty()) {
            result[count--] = asteroids[stack.pop()];
        }
        return result;
    }

    public static int[] asteroidCollisionOld(int[] asteroids) {
        boolean anyCollision;
        Integer last_positive_index;
        Map<Integer, Integer> wasteAsteroidTank = new HashMap<>();
        int iterations = 0;
        do {
            iterations++;
            anyCollision = false;
            last_positive_index = null;
            for (int i = 0; i < asteroids.length; i++) {
                if (wasteAsteroidTank.containsKey(i)) continue;
                if (asteroids[i] > 0) {
                    last_positive_index = i;
                } else {
                    if (last_positive_index == null) continue;
                    // Add Collision Result

                    if (asteroids[last_positive_index] > Math.abs(asteroids[i])) {
                        wasteAsteroidTank.put(i, asteroids[i]);
                    } else if (asteroids[last_positive_index] < Math.abs(asteroids[i])) {
                        wasteAsteroidTank.put(last_positive_index, asteroids[last_positive_index]);
                        last_positive_index = null;
                    } else {
                        wasteAsteroidTank.put(i, asteroids[i]);
                        wasteAsteroidTank.put(last_positive_index, asteroids[last_positive_index]);
                        last_positive_index = null;
                    }
                    anyCollision = true;
                }
            }
        } while (anyCollision);

        int[] result = new int[asteroids.length - wasteAsteroidTank.size()];
        int count = 0;
        for (int i = 0; i < asteroids.length; i++) {
            if (!wasteAsteroidTank.containsKey(i)) {

                result[count++] = asteroids[i];
            }
        }
        return result;
    }
}
