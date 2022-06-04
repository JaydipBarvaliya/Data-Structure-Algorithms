package com.leetcode.year_2020.graph.bfs;

/**
 * https://leetcode.com/discuss/interview-question/882062/Google-or-Onsite-or-Phone-Interviews
 * https://csacademy.com/contest/archive/task/move-the-bishop/statement/
 */
public class BishopShortestMoves {

    public static void main(String[] args) {
        System.out.println(findMinimumMoves(new int[]{5, 3}, new int[]{1, 1}));
        System.out.println(findMinimumMoves(new int[]{5, 3}, new int[]{2, 6}));
        System.out.println(findMinimumMoves(new int[]{2, 2}, new int[]{5, 6}));
    }

    /**
     * This is not a BFS problem, since Bishop is Camel(ऊंट) and it moves in only diagonal
     * So this problem is simple, if the destination is in the same diagonal then we can reach there
     * in just "1" move, if it's not in the same diagonal and if it's in the same color cell of source cell
     * then we can reach in "2" moves, else we can never reach
     * <p>
     * 8*8 Board
     * <p>
     * *      0       1       2       3       4       5       6       7
     * * 0 | BLACK | WHITE | BLACK | WHITE | BLACK | WHITE | BLACK | WHITE |
     * * ---------------------------------------------------------------
     * * 1 | WHITE |(BLACK)| WHITE | BLACK | WHITE | BLACK | WHITE | BLACK |
     * * ---------------------------------------------------------------
     * * 2 | BLACK | WHITE | BLACK | WHITE | BLACK | WHITE | BLACK | WHITE |
     * * ---------------------------------------------------------------
     * * 3 | WHITE | BLACK | WHITE | BLACK | WHITE | BLACK | WHITE | BLACK |
     * * ---------------------------------------------------------------
     * * 4 | BLACK | WHITE | BLACK | WHITE | BLACK | WHITE | BLACK | WHITE |
     * * ---------------------------------------------------------------
     * * 5 | WHITE | BLACK | WHITE |(BLACK) | WHITE | BLACK | WHITE | BLACK |
     * * ---------------------------------------------------------------
     * * 6 | BLACK | WHITE | BLACK | WHITE | BLACK | WHITE | BLACK | WHITE |
     * * ---------------------------------------------------------------
     * * 7 | WHITE | BLACK | WHITE | BLACK | WHITE | BLACK | WHITE | BLACK |
     * * ---------------------------------------------------------------
     * <p>
     * Now you can visualize if I am standing on (5,3[Black]), I can move same diagonal in just 1 move
     * and if it's not on the diagonal but of same color[Assume[1,1[Black]].
     * <p>
     * I can move in just 2 Move, [5,3 ---> 2,0 ----> 1,1]
     */
    public static int findMinimumMoves(int[] source, int[] destination) {
        if (isSameDiagonal(source, destination)) {
            return 1;
        }
        if (isSameColorCell(source, destination)) {
            return 2;
        }
        return -1;
    }

    private static boolean isSameColorCell(final int[] source, final int[] destination) {
        // Now each cell can either be divisible by 2 or not,
        // So assume Black for divisible by 2 and White for not
        // hence we just sum the coordinates and take mod 2.
        return (source[0] + source[1]) % 2 == (destination[0] + destination[1]) % 2;
    }

    private static boolean isSameDiagonal(final int[] source, final int[] destination) {
        //if Absolute difference between source and destination cell's row and column is same
        // then it's on same diagonal
        return Math.abs(source[0] - destination[0]) == Math.abs(source[1] - destination[1]);
    }
}
