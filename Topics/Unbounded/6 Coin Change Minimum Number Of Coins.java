// one reason I think that is possible to initialize the matrix my +ve 
// infinity is because we need to find the minimum number of coins. 
// If we put 0 which Aditya Bhaiya pointed out in the video, 
// then calculating bigger problems by using smaller sub-problems will always give 0 because 0 is going to be the minimum in the matrix.
// That is why we don't take 0 although it seems to be there

class Solution{

	public int minCoins(int coins[], int len, int sum){ 
	    
	    int matrix[][] = new int[ coins.length+1 ][ sum+1 ];

        //Best thing of this question is you have to initialise DP
        //table with INT_MAX-1 instead of INT_MAX. Reason: if we take INT_MAX as intialisation,
        //then in dp[i][j] = min((dp[i][j-a[i-1]]+1), dp[i-1][j]),
        //(dp[i][j-a[i-1]]+1) ==> this part become INT_MIN if case is not valid and
        //min(INT_MIN, INT_MAX) = INT_MIN which affects our answer.        
        
        for(int col=0; col<sum+1; col++)
            matrix[0][col] = Integer.MAX_VALUE-1;
            
        for(int row=1; row<coins.length+1; row++)
            matrix[row][0] = 0;
            
        for(int col=1; col<sum+1; col++){
            
            if(col % coins[0] == 0 ){
                matrix[1][col] = col / coins[0];
            }
            else{
                matrix[1][col] = Integer.MAX_VALUE-1;
            }
        }
        
        for(int i=2; i<coins.length+1; i++){
            
            for(int j=1; j<sum+1; j++){
                
                if(ValuOfCurrCoin <= j){
                   matrix[i][j] =   Math.min( 1 + matrix[i][j-coins[i-1]],
                                                  matrix[i-1][j] );
                }else{
                   matrix[i][j] =   matrix[i-1][j];
                }
            }
        }
        // for (int[] row : matrix)
        //     System.out.println(Arrays.toString(row));
        
        if(matrix[coins.length][sum] > sum) return -1;
            
        return matrix[coins.length][sum];
	} 
}
