class Solution{
    static int matrixMultiplication(int len, int arr[]){
        
        int[][] matrix = new int[len][len];
        
        for(int[] row: matrix)
            Arrays.fill(row, -1);
            
        return solve(arr, 1, arr.length-1, matrix);
    }
    
    static int solve(int arr[], int i, int j, int[][] matrix ){
        
        if(i>=j) return 0;
        
        if(matrix[i][j] != -1) return matrix[i][j];

        int min = Integer.MAX_VALUE;

        for(int k=i; k<j; k++){
            int tempAns = solve(arr, i, k, matrix) +  solve(arr, k+1, j, matrix) + (arr[i-1] * arr[k] * arr[j ]);
            min  = Math.min(tempAns, min);
        }
        matrix[i][j] = min;
        return min;
    }
}