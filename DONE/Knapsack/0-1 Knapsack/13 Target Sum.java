class Solution {
    
    public int findTargetSumWays(int[] items, int diff) {
        
        int totalSum = 0;
        
        for(int ele : items)
             totalSum = totalSum +  ele;

        if(totalSum < Math.abs(diff) || (diff + totalSum) %2 != 0) 
            return 0;

        int s1 = (diff + totalSum)/2;
        
        return subsetSum(items, s1);
    }
    
    int subsetSum(int arr[], int sum){
    
        int n = arr.length;
        int[][] matrix = new int[n + 1][sum + 1];
        
        
        for (int col=1;  col<sum+1; col++)
			matrix[0][col] = 0;
        

        matrix[0][0] = 1;
        
        int zeroCount = 0;
        
		for (int row=1;  row<n+1;  row++){
	        if(arr[row-1] == 0) { 
	            zeroCount++;
	            matrix[row][0] = (int)Math.pow(2, zeroCount);
	        }else{
	            matrix[row][0] = (int)Math.pow(2, zeroCount);
	        }
    	}
        
 
        for (int i = 1; i <n+1; i++){
            for (int j = 1; j <sum+1; j++){

                if (arr[i - 1] <= j)
                    matrix[i][j] = matrix[i - 1][j] + matrix[i - 1][j - arr[i - 1]]; 
                else
                    matrix[i][j] = matrix[i - 1][j];
                }
            }

        return matrix[n][sum];
    }
}
