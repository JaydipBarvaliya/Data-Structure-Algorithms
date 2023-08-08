class Solution {

    public int perfectSum(int items[], int n, int totalSum) {

        int matrix[][] = new int[items.length + 1][totalSum + 1];

        for (int col = 1; col <= totalSum; col++)
            matrix[0][col] = 0;


        matrix[0][0] = 1;
        int zeroCount = 0;

        for (int row = 1; row < items.length + 1; row++) {
            if (items[row - 1] == 0) {
                zeroCount++;
                matrix[row][0] = (int) Math.pow(2, zeroCount);
            } else {
                matrix[row][0] = (int) Math.pow(2, zeroCount);
            }
        }


        for (int i = 1; i <= items.length; i++) {

            for (int j = 1; j <= totalSum; j++) {
                if (items[i - 1] <= j) {
                    matrix[i][j] = matrix[i - 1][j] + matrix[i - 1][j - items[i - 1]];
                } else {
                    matrix[i][j] = matrix[i - 1][j];
                }
            }
        }


        for (int[] row: matrix)
            System.out.println(Arrays.toString(row));

        return matrix[items.length][totalSum];
    }
}
