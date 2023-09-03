class Solution{
    static int matrixMultiplication(int N, int arr[]){
        return solve(arr, 1, arr.length-1);
    }
    
    static int solve(int arr[], int i, int j){
        
        if(i>=j) return 0;
        
        int min = Integer.MAX_VALUE;
        for(int k=i; k<j; k++){
            int tempAns = solve(arr, i, k) + solve(arr, k+1, j) + (arr[i-1] * arr[k] * arr[j]);
            min  = Math.min(tempAns, min);
        }
        
        return min;
    }
}