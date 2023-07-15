class Solution { 
    
    static int knapSack(int W, int wt[], int val[], int n){ 
        
        int matrix[][] = new int[ val.length+1 ][ W+1 ];

        for(int i=1; i<=val.length; i++){

            for(int j=1; j<=W; j++){
                
                if(wt[i-1] <= j){
                   matrix[i][j] = Math.max( val[i-1]  + matrix[i-1][j-wt[i-1]],
                                                        matrix[i-1][j]);
                }else{
                   matrix[i][j] =  matrix[i-1][j];
                }
            }
        }
        return matrix[val.length][W];   
    } 
}


