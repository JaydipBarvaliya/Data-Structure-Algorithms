package com.leetcode.year_2020.graph.dfs;

import java.util.HashSet;
import java.util.Set;

/**
 * You can only run this in online IDE and not here, since {@link Robot} implementation is not really present with us.
 * https://leetcode.com/problems/robot-room-cleaner/
 * https://www.lintcode.com/problem/1514/
 * https://www.youtube.com/watch?v=-1P3VP7LH0I
 */
public class RobotRoomCleaner {

    public static void main(String[] args) {

    }

    /**
     * // This is the robot's control interface.
     * // You should not implement it, or speculate about its implementation
     * class Robot {
     * public:
     * // Returns true if the cell in front is open and robot moves into the cell.
     * // Returns false if the cell in front is blocked and robot stays in the current cell.
     * bool move();
     * <p>
     * // Robot will stay in the same cell after calling turnLeft/turnRight.
     * // Each turn will be 90 degrees.
     * void turnLeft();
     * void turnRight();
     * <p>
     * // Clean the current cell.
     * void clean();
     * };
     */
    public static class Solution {
        public void cleanRoom(Robot robot) {
            final Set<String> seen = new HashSet<>();
            seen.add("0-0");
            dfs(robot, 0, 0, 0, seen);
        }

        private static int[][] directions = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};

        public void dfs(Robot robot, int x, int y, int direction, final Set<String> seen) {
            robot.clean();

            // Go in all 4 directions
            for (int i = 0; i < 4; i++) {
                int newDirection = (direction + i) % 4;
                int newX = x + directions[newDirection][0];
                int newY = y + directions[newDirection][1];

                if (!seen.contains(newX + "-" + newY) && robot.move()) {
                    seen.add(newX + "-" + newY);
                    dfs(robot, newX, newY, newDirection, seen);
                    goBack(robot); // Come back on same position
                }
                // Since we have explored one direction let's move to next
                robot.turnRight();
            }
        }

        /**
         * In normal DFS we don't have to backtrack manually that's taken care by the way we wrote the code, once
         * all the directions of a cell are explored we come back, but in this problem it has a concept of Direction.
         * Robot can only move in the direction it has and not randomly move to other cells, hence we have to bring it back
         * <p>
         * ________________________ (Hit Wall going up and now we have to backtrack)
         * /\
         * 00
         * ||
         * <p>
         * So we make a right turn
         * <p>
         * =========00>
         * <p>
         * ||
         * ||
         * 00
         * \/
         * <p>
         * Now move back to one direction where you came from and make to more right turn so to be on the original position seeing upwards
         * but in a cell below the current cell.
         *
         * @param robot
         */
        public void goBack(Robot robot) {
            robot.turnRight();
            robot.turnRight();
            robot.move();
            robot.turnRight();
            robot.turnRight();
        }
    }

    private class Robot {
        public boolean move() {
            return true;
        }

        public void turnRight() {
        }

        public void turnLeft() {
        }

        public void clean() {

        }
    }
}
