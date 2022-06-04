package com.leetcode.year_2020.graph.disjoint_set_union_find;

import java.util.HashMap;
import java.util.Map;

/**
 * @author neeraj on 30/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SatisfiabilityofEqualityEquations {

    public static void main(String[] args) {
        System.out.println(equationsPossible(new String[]{
                "a==b", "b!=a"
        }));
        System.out.println(equationsPossible(new String[]{
                "b==a", "a==b"
        }));
        System.out.println(equationsPossible(new String[]{
                "a==b", "b==c", "a==c"
        }));
        System.out.println(equationsPossible(new String[]{
                "a==b", "b!=c", "c==a"
        }));
        System.out.println(equationsPossible(new String[]{
                "c==c", "b==d", "x!=z"
        }));
        System.out.println(equationsPossible(new String[]{
                "a==b", "e==c", "b==c", "a!=e"
        }));
        System.out.println(equationsPossible(new String[]{
                "a==b", "c==d", "a==c", "a!=d"
        }));

        /**
         * false
         * true
         * true
         * false
         * true
         * false
         * false
         */
    }

    public static boolean equationsPossible(String[] equations) {
        Map<Character, Character> leaders = new HashMap<>();
        Map<Character, Integer> rank = new HashMap<>();
        for (String eq : equations) {
            // equation is in form
            // a == b | a != b
            // So we are interested in just 2 operands in this. 0th index and 3rd index.
            final char first = eq.charAt(0);
            final char last = eq.charAt(3);
            if (!leaders.containsKey(first)) {
                leaders.put(first, first);
                rank.put(first, 0);
            }
            if (!leaders.containsKey(last)) {
                leaders.put(last, last);
                rank.put(last, 0);
            }

            // we are just focusing on == equations
            if (eq.charAt(1) == '=') {
                Character leaderOfA = findLeader(first, leaders);
                Character leaderOfB = findLeader(last, leaders);

                if (leaderOfA == leaderOfB) continue;

                if (rank.get(leaderOfA) >= rank.get(leaderOfB)) {
                    leaders.put(leaderOfB, leaderOfA);

                    if (rank.get(leaderOfA) == rank.get(leaderOfB)) {
                        rank.put(leaderOfA, rank.get(leaderOfA) + 1);
                    }
                } else {
                    leaders.put(leaderOfA, leaderOfB);
                }

            }
        }

        // Now we will focus on just != equations
        for (String eq : equations) {
            if (eq.charAt(1) == '!') {
                Character leaderOfA = findLeader(eq.charAt(0), leaders);
                Character leaderOfB = findLeader(eq.charAt(3), leaders);

                if (leaderOfA == leaderOfB) return false;
            }
        }
        return true;
    }

    private static Character findLeader(Character operand,
                                        Map<Character, Character> leaders) {
        Character leader = leaders.get(operand);
        if (leader != operand) {
            // Path compression.
            leaders.put(operand, findLeader(leader, leaders));
            leader = leaders.get(operand);
        }
        return leader;
    }
}
