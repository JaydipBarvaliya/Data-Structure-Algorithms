class Solution{
    static int knapSack(int N, int W, int val[], int wt[]){
        
        int matrix[][] = new int[ val.length+1 ][ W+1 ];

        for(int i=1; i<val.length+1; i++){
            
            int currentWeight  =  wt[i-1];
            int currentValue   = val[i-1];
            
            for(int j=1; j<W+1; j++){
                
                if(currentWeight <= j){
                   matrix[i][j] = Math.max( matrix[i-1][j] ,
                                            matrix[i][j-currentWeight] + currentValue );
                }else{
                   matrix[i][j] =  matrix[i-1][j];
                }
            }
        }
        return matrix[val.length][W];
    }
}
