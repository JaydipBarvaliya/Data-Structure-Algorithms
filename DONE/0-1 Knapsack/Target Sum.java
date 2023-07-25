class Solution {
    
    public int findTargetSumWays(int[] items, int diff) {
        
        int len = items.length;
        int totalSum=0;
        
        for(int summer : items)
             totalSum += summer;

        if(totalSum < Math.abs(diff) || (diff + totalSum) %2 != 0) return 0;

        int s1 = (diff + totalSum)/2;
        
        return subsetSum(items, len, s1);
    }
    
    int subsetSum(int a[], int n, int sum){
    
        int[][] matrix = new int[n + 1][sum + 1];
        
        
        for (int col=1;  col<=sum;  col++)
			matrix[0][col] = 0;
        

        matrix[0][0] = 1;
        
        int zeroCount = 0;
        
		for (int row=1;  row<a.length+1;  row++){
	        if(a[row-1] == 0) { 
	            zeroCount++;
	            matrix[row][0] = (int)Math.pow(2, zeroCount);
	        }else{
	            matrix[row][0] = (int)Math.pow(2, zeroCount);
	        }
    	}
        
 
        for (int i = 1; i <= n; i++){
            for (int j = 1; j <= sum; j++){

                if (a[i - 1] <= j)
                    matrix[i][j] = matrix[i - 1][j] + matrix[i - 1][j - a[i - 1]]; 
                else
                    matrix[i][j] = matrix[i - 1][j];
                }
            }
        return matrix[n][sum];
    }
}
