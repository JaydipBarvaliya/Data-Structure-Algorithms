class Solution {
    public int change(int totalAmount, int[] coins) {

        int[][] matrix = new int[ coins.length+1 ][ totalAmount+1 ];
        
        for(int row=1; row<coins.length+1; row++)
            matrix[row][0] = 1;
        
        for(int col=1; col<totalAmount+1; col++)
            matrix[0][col] = 0;
                

        for(int i=1; i<coins.length+1; i++){
            
            for(int j=1; j<totalAmount+1; j++){
                
                if(coins[i-1] <= j){
                   matrix[i][j] =   matrix[i][j-coins[i-1]] + matrix[i-1][j];
                }else{
                   matrix[i][j] =   matrix[i-1][j];
                }
            }
        }
        return matrix[coins.length][totalAmount];
    }
}