package com.company.amazon;

public class SolveNQueensProblem {

    static class QueenPosition {
        int row;
        int col;

        public QueenPosition(int x, int y) {
            this.row = x;
            this.col = y;
        }

        public String toString() {
            return "[" + this.row + "," + this.col + "]";
        }
    }

    public static void main(String[] args) {
        solveNQueenProblem(8);
    }

    public static void solveNQueenProblem(int noOfQueens) {
        QueenPosition[] positions = new QueenPosition[noOfQueens]; // This will tell where is each Queen Placed
        int currentRow = 0;
        if (solveNQueenProblem(noOfQueens, currentRow, positions)) {
            for (QueenPosition position : positions) {
                System.out.println(position);
            }
        }
    }

    private static boolean solveNQueenProblem(int noOfQueens, int currentRow, QueenPosition[] positions) {
        Boolean isSafePlaceFound = true;
        if (currentRow >= noOfQueens) { // We have covered all queens
            return true;
        }

        // We have to check on which column at each row we can place this new Queen
        for (int col = 0; col < noOfQueens; col++) {
            isSafePlaceFound = true;

            // Let's check if any previous Queen can fuck this new Queen
            for (int queen = 0; queen < currentRow; queen++) {

                // We have to check 3 things only (leaving the same row concept because loop runs only less that current row
                // 1) Same column new queen will get fucked
                // 2) Left diagonal too will get fucked
                // 3) Right diagonal one will definitely get fucked.....
                QueenPosition prevQueen = positions[queen];
                if (positions[queen].col == col ||
                        (prevQueen.row - prevQueen.col == currentRow - col) ||
                        (prevQueen.col + prevQueen.row == col + currentRow)) {
                    isSafePlaceFound = false;
                    break;
                }
            }

            if (isSafePlaceFound) {
                positions[currentRow] = new QueenPosition(currentRow, col);
                if (solveNQueenProblem(noOfQueens, currentRow + 1, positions)) {
                    return true;
                }
            }
        }
        return false;
    }
}
