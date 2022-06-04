package com.company.amazon;

import com.datastructures.linkedlist.DoublyCircularLinkedList;

/**
 * Check if a given sequence of moves for a robot is circular or not
 */
public class isRobotMovesCircular {

    public static void main(String[] args) {
        System.out.println("GLGLGLG :: is Robot Move Circular ? " + isRobotMovesCircular("GLGLGLG"));
        System.out.println("GLLG :: is Robot Move Circular ? " + isRobotMovesCircular("GLLG"));
        System.out.println("GLGLG :: is Robot Move Circular ? " + isRobotMovesCircular("GLGLG"));
    }

    public static boolean isRobotMovesCircular(String inputPath) {
        char[] path = inputPath.toCharArray();
        String directionArr = "SWNE";
        boolean result = false;
        int x = 0, y = 0;
        String direction = "N";
        for (char c : path) {
            if (c == 'G') {
                switch (direction) {
                    case "N":
                        y += 1;
                        break;
                    case "E":
                        x += 1;
                        break;
                    case "W":
                        x -= 1;
                        break;
                    case "S":
                        y -= 1;
                        break;
                }
            } else {
                int index = directionArr.indexOf(direction);
                char directionChar = 'N';
                if (c == 'L') {
                    directionChar = (index == 0) ? directionArr.charAt(directionArr.length() - 1) : directionArr.charAt(index - 1);
                } else {
                    directionChar = (index == 3) ? directionArr.charAt(0) : directionArr.charAt(index + 1);
                }
                direction = String.valueOf(directionChar);
            }
        }

        return x == 0 && y == 0;
    }
}
