class Solution{
    static int knapSack(int N, int W, int val[], int wt[]){
        
        int matrix[][] = new int[ val.length+1 ][ W+1 ];

        for(int i=1; i<val.length+1; i++){
            
            int currentWeight  =  wt[i-1];
            int currentValue   = val[i-1];
            
            for(int c=1; c<W+1; c++){
                
                if(currentWeight <= c){
                   matrix[i][c] = Math.max( matrix[i-1][c] ,
                                            matrix[i][c-currentWeight] + currentValue );
                }else{
                   matrix[i][c] =  matrix[i-1][c];
                }
            }
        }
        return matrix[val.length][W];
    }
}
