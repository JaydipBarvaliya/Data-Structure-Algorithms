package com.leetcode.problems.medium;

public class RobotBoundedInCircle {

    static Point[] directions = new Point[]{
            new Point(Direction.NORTH, 0, 1),
            new Point(Direction.EAST, 1, 0),
            new Point(Direction.SOUTH, 0, -1),
            new Point(Direction.WEST, -1, 0),
    };

    enum Direction {
        NORTH, EAST, SOUTH, WEST
    }

    public static void main(String[] args) {
        System.out.println(isRobotBounded("GGLLGG"));
        System.out.println(isRobotBounded("GG"));
        System.out.println(isRobotBounded("GL"));
        System.out.println(isRobotBounded("GRG"));
    }

    //
    //         North
    //           |
    //.  West----|-----East
    //           |
    //           |
    //         South
    //
    public static boolean isRobotBounded(String instructions) {
        int currentDirectionIndex = 0;
        int currX = 0, currY = 0; // Initially robot is at origin
        Direction facingToward = Direction.NORTH;
        for (char instruction : instructions.toCharArray()) {
            if (instruction == 'G') {
                currX += directions[currentDirectionIndex].incrementInX;
                currY += directions[currentDirectionIndex].incrementInY;
            } else if (instruction == 'L') {
                // Rotate left 90Degrees
                // So whenever we have to increment in Circular array
                // we do (index + 1) % length of Array, to round off back to 0 index in case we overflow
                // Similarly whenever we have to decrement
                // we do (index + length(arr)) - 1 % length(arr) to do the round off in case we go in negative index.
                currentDirectionIndex = ((currentDirectionIndex + directions.length) - 1) % directions.length;
                facingToward = directions[currentDirectionIndex].direction;
            } else if (instruction == 'R') {
                currentDirectionIndex = (currentDirectionIndex + +1) % directions.length;
                facingToward = directions[currentDirectionIndex].direction;
            }
        }
        return currX == 0 && currY == 0 || facingToward != Direction.NORTH;
    }

    static class Point {
        Direction direction;
        int incrementInX;
        int incrementInY;

        public Point(final Direction direction, final int incrementInX, final int incrementInY) {
            this.direction = direction;
            this.incrementInX = incrementInX;
            this.incrementInY = incrementInY;
        }
    }
}
