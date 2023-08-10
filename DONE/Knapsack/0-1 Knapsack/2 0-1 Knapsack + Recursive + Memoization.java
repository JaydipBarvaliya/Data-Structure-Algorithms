class Solution { 
    //Function to return max value that can be put in knapsack of capacity W.
    
    static int knapSack(int W, int wt[], int val[], int n){ 
        
        int matrix[][] = new int[ val.length+1 ][ W+1 ];
        
        for (int[] row : matrix)
            Arrays.fill(row, -1);

         return knapSackWithMemo(matrix, val, wt, W, n);
         
    } 
    
    static int knapSackWithMemo(int matrix[][], int val[], int wt[], int W, int n){ 
        
        if(W == 0 || n==0) return 0;
        
        if(matrix[n][W] != -1) return matrix[n][W];
        
         if(wt[n-1] <= W){
           matrix[n][W] = Math.max(val[n-1] + knapSackWithMemo(matrix, val, wt, W-wt[n-1], n-1 ),
                                      knapSackWithMemo(matrix, val, wt, W, n-1));
        }else{
            matrix[n][W] =  knapSackWithMemo(matrix, val, wt, W, n-1);
        }
        return matrix[n][W]; 
    }
}


