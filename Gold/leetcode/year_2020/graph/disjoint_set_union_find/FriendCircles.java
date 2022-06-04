package com.leetcode.year_2020.graph.disjoint_set_union_find;

/**
 * https://leetcode.com/problems/friend-circles/
 *
 * @author neeraj on 28/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FriendCircles {

    public static void main(String[] args) {
        System.out.println(findCircleNum(new int[][]{
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        }));
        System.out.println(findCircleNum(new int[][]{
                {1, 1, 0},
                {1, 1, 1},
                {0, 1, 1}
        }));
    }

    public static int findCircleNum(int[][] M) {
        /**
         * Okay so this problem can be easily solved using Union Find technique similar to {@link NumberofOperationstoMakeNetworkConnected}
         */
        int[] leaderInFriends = new int[M.length];

        // Initially every peron is it's own leader(hence form a group of just himself.)
        for (int i = 0; i < leaderInFriends.length; i++) leaderInFriends[i] = i;

        // Now let's find total groups
        for (int i = 0; i < M.length; i++) {
            for (int j = 0; j < M[i].length; j++) {
                if (M[i][j] == 1) { // There is a friendship between i and j
                    int leaderOfI = findLeader(leaderInFriends, i);
                    int leaderOfJ = findLeader(leaderInFriends, j);
                    if (leaderOfI == leaderOfJ) continue; // They already belong to same group
                    else leaderInFriends[leaderOfI] = leaderOfJ;
                }
            }
        }

        // Now let's count number of connected components
        int totalFriendCircle = 0;
        for (int i = 0; i < M.length; i++) {
            // Connected Components is represented by the leader only.
            // So all those nodes who are leaders will be the total number of connected components.
            if (leaderInFriends[i] == i) totalFriendCircle++;
        }
        return totalFriendCircle;
    }

    public static int findLeader(int[] leader, int person) {
        if (leader[person] == person) return person;

        leader[person] = findLeader(leader, leader[person]);
        return leader[person];
    }
}
