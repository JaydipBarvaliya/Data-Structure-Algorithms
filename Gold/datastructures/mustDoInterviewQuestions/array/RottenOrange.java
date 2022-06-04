package com.datastructures.mustDoInterviewQuestions.array;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by jaine03 on 27/07/17.
 * <p>
 * Inside Lot :
 * <p>
 * 0 ---> Empty Cell
 * 1 ---> Fresh Orange
 * 2 ---> Rotten Orange
 */
public class RottenOrange {

    static class TotalPassRequired {
        int val;

        TotalPassRequired(int val) {
            this.val = val;
        }
    }

    static class Orange {
        int x;
        int y;

        public Orange(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static int R = 3;
    public static int C = 5;

    /**
     * @param lot
     * @return
     */
    public static Boolean anyFreshOrangeInLot(int[][] lot) {
        for (int i = 0; i < lot.length; i++)
            for (int j = 0; j < lot.length; j++)
                if (lot[i][j] == 1)
                    return true;
        return false;
    }

    private static boolean isSafeToRot(int[][] lot, int x, int y) {
        return x >= 0 && x < lot.length &&
                y >= 0 && y < lot[0].length;
    }

    /**
     * Check if We can rot all oranges or not
     *
     * @param lot
     * @return
     */
    public static int rotOranges(int[][] lot) {
        TotalPassRequired totalPassRequired = new TotalPassRequired(0);
        Boolean flag = false; //hasThisOrangeAlreadyRottenAnyAdjacentOrange
        Queue<Orange> queue = new LinkedList<>();

        // Let's add all Rotten Oranges to the Queue
        for (int i = 0; i < lot.length; i++) {
            for (int j = 0; j < lot[i].length; j++) {
                if (lot[i][j] == 2) {
                    queue.add(new Orange(i, j));
                }
            }
        }

        // Separate the Rotten Orange with the (new fresh one's) these will destroy on each pass
        queue.add(null); // Using Null as delimiter

        while (!queue.isEmpty()) {

            flag = false;
            while (queue.peek() != null) {
                Orange rottenOrange = queue.peek();
                int X = rottenOrange.x;
                int Y = rottenOrange.y;

                // Check Top Adjacent Orange, if it is fresh
                flag = rotAdjacentOrange(lot, X - 1, Y, flag, totalPassRequired, queue);

                // Check Bottom Adjacent Orange, if it is fresh
                flag = rotAdjacentOrange(lot, X + 1, Y, flag, totalPassRequired, queue);

                // Check left Adjacent
                flag = rotAdjacentOrange(lot, X, Y - 1, flag, totalPassRequired, queue);

                // Check Right Adjacent
                flag = rotAdjacentOrange(lot, X, Y + 1, flag, totalPassRequired, queue);

                queue.remove();
            }
            queue.remove(); // Dequeue this delimiter

            if (!queue.isEmpty()) {
                queue.add(null); // Enqueue Another delimiter at the end of newly Added Rotten Oranges So that they can rot others
            }
        }
        if (!anyFreshOrangeInLot(lot)) {
            return totalPassRequired.val;
        }
        return -1;
    }

    public static boolean rotAdjacentOrange(int[][] lot, int X, int Y, Boolean hasThisOrangeAlreadyRottenAnyAdjacentOrange, TotalPassRequired totalPassRequired, Queue<Orange> queue) {
        // Check Top Adjacent Orange, if it is fresh
        if (isSafeToRot(lot, X, Y) && lot[X][Y] == 1) {
            if (!hasThisOrangeAlreadyRottenAnyAdjacentOrange) { // If Not then let's increment passRequired
                totalPassRequired.val += 1;
                hasThisOrangeAlreadyRottenAnyAdjacentOrange = true;
            }
            lot[X][Y] = 2;
            queue.add(new Orange(X, Y));
        }
        return hasThisOrangeAlreadyRottenAnyAdjacentOrange;
    }


    public static void main(String[] args) {
        int arr[][] = {{2, 1, 0, 2, 1},
                {1, 0, 1, 2, 1},
                {1, 0, 0, 2, 1}};
        int answer = rotOranges(arr);
        System.out.println(answer);
    }
}
