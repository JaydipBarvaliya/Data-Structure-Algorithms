package com.geeksforgeeks.stack;

import java.util.Stack;

public class CelebrityProblem {
    private static boolean[][] connections = {
            {false, false, true, false},
            {false, false, true, false},
            {false, false, false, false},
            {false, false, true, false}};

    private static boolean knows(int A, int B) {
        return connections[A][B];
    }

    /**
     * 1) Push All guest in the stack
     * 2) Pop 2 guest at a time and check whether A knows B
     * 3) if Yes then throw A and push B back else push A and throw B
     * 4) Repeat 2-3 till there is only 1 entry in the stack
     * 5) Check the Acquaintance of this guest with others if no acquaintances found, Voila this is our celebrity.
     *
     * @param guestsInTheParty
     */
    public static void findCelebrity(int guestsInTheParty) {
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < guestsInTheParty; i++) {
            stack.push(i);
        }

        while (stack.size() > 1) {
            int A = stack.pop();
            int B = stack.pop();

            if (knows(A, B)) {
                stack.push(B);
            } else {
                stack.push(A);
            }
        }

        int celebrity = stack.pop();
        boolean celebrityFound = true;
        for (int i = 0; i < guestsInTheParty; i++) {
            if (i != celebrity) {
                if (knows(celebrity, i)) {
                    celebrityFound = false;
                    break;
                }
            }
        }

        System.out.println(celebrityFound ? "Celebrity is " + (celebrity + 1) : "Celebrity not present in the party");
    }

    public static void main(String[] args) {
        int guestsInTheParty = 4;
        findCelebrity(guestsInTheParty);
    }
}
