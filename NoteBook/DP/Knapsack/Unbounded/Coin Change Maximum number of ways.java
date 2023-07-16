class Solution {
    public long count(int coin[], int size, int sum) {
        
        long matrix[][] = new long[ coin.length+1 ][ sum+1 ];
        
        
        for(int row=1; row<coin.length+1; row++)
            matrix[row][0] = 1;
        
        for(int col=1; col<sum+1; col++)
            matrix[0][col] = 0;
                

        for(int i=1; i<coin.length+1; i++){
            
            for(int j=1; j<sum+1; j++){
                
                if(coin[i-1] <= j){
                   matrix[i][j] =   matrix[i-1][j] +  matrix[i][j-coin[i-1]];
                }else{
                   matrix[i][j] =   matrix[i-1][j];
                }
            }
        }
        return matrix[coin.length][sum];
    }
}
