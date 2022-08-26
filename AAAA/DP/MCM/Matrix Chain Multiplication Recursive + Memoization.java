class Solution{
    static int matrixMultiplication(int len, int arr[]){
        int[][] matrix = new int[len][len];
        for (int[] row : matrix)
            Arrays.fill(row, -1);
        return solve(matrix, arr, 1, len-1);
    }

    static int solve(int[][] matrix, int arr[], int i, int j){
        
        if(i>=j) return 0;
        
        if(matrix[i][j] != -1) return matrix[i][j];
        
        int min = Integer.MAX_VALUE;
        for(int k=i; k<=j-1; k++){
            int tempAns = solve(matrix, arr, i, k) + solve(matrix, arr, k+1, j) + (arr[i-1] * arr[j] * arr[k]);
            min  = Math.min(tempAns, min);
        }
        
        matrix[i][j] = min;
        return min;
    }
}
