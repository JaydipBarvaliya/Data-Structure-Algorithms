package com.leetcode.year_2020.graph.disjoint_set_union_find;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class OptimizeWaterDistribution {

    public static void main(String[] args) {
        System.out.println(minCostToSupplyWater(3, new int[]{1, 2, 2}, new int[][]{{1, 2, 1}, {2, 3, 1}}));
    }

    static int[] ranks;

    static void merge(int a, int b) {
        ranks[find(a)] = find(b); // Marking parent of A as the parent of B.
    }

    public static int find(int a) {
        if (ranks[a] != a) {
            ranks[a] = find(ranks[a]);
        }
        return ranks[a];
    }

    public static int minCostToSupplyWater(int n, int[] wells, int[][] pipes) {
        /**
         * So basically in the worst case, all cities can have the wells dig in.
         * Now we need to find out what all cities are connected or belongs to same clan and what better way to do this,
         * Union Find right...... Do a merge operation and find parent using path compression.
         */
        ranks = new int[n + 1];
        // Initially all cities are their own parent
        for (int i = 0; i <= n; i++) {
            ranks[i] = i;
        }

        // Let's put all cities with well digging cost
        final Map<Integer, Integer> costDetector = new HashMap<>();
        int counter = 0;
        for (int wellDiggingCost : wells) {
            costDetector.put(++counter, wellDiggingCost);
        }

        // And we should start with the minimum pipe laying cost, so let's sort
        Arrays.sort(pipes, Comparator.comparingInt(p -> p[2]));

        int totalCost = 0;
        for (int[] pipe : pipes) {
            int houseA = pipe[0];
            int houseB = pipe[1];
            int costOfLayingPipe = pipe[2];

            if (find(houseA) != find(houseB)) { // They do not belong to the same clan
                int minWell = Math.min(costDetector.get(find(houseA)), costDetector.get(find(houseB)));
                int costOfSeparateWell = costDetector.get(find(houseA)) + costDetector.get(find(houseB));

                if (costOfSeparateWell > (minWell + costOfLayingPipe)) {
                    // Why minWell + costOfLayingPipe, Since pipes are bi-directional, so we are just figuring out
                    // which house to dig(basically the house which has minimum digging cost) plus the cost of laying pipe-line
                    merge(houseA, houseB);
                    costDetector.put(find(houseA), minWell);
                    totalCost += costOfLayingPipe;
                }
            }
        }
        // Now let's collect cost of those cities as well which are not merged
        final Set<Integer> visited = new HashSet<>();
        for (int i = 1; i <= n; i++) {
            if (!visited.contains(find(i))) {
                totalCost += costDetector.get(find(i));
                visited.add(find(i));
            }
        }
        return totalCost;
    }
}
