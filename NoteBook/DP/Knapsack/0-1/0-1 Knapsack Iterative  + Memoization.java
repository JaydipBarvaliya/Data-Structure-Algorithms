class Solution { 
    
    static int knapSack(int W, int wt[], int val[], int n){ 
        
        int matrix[][] = new int[ val.length+1 ][ W+1 ];
        
        
        
        //In this approach  doesn't require to fill up the entire matrix with any value with -1 like below commented block..because 0 there by default
        // for (int[] row : matrix)
        //     Arrays.fill(row, -1);
         
         
         //This approch not include below base cases.....as process will stop by for loop
        //  if(W == 0 || n==0) return 0;
        // if(matrix[n][W] != -1) return matrix[n][W];
        
        for(int i=1; i<val.length+1; i++){
            
            int currentWeight  =  wt[i-1];
            int currentValue   = val[i-1];
            
            
            for(int currCapacity=1; currCapacity<W+1; currCapacity++){
                
                if(currentWeight <= currCapacity){
                   matrix[i][currCapacity] = Math.max( matrix[i-1][currCapacity] ,
                                            matrix[i-1][currCapacity-currentWeight] + currentValue );
                }else{
                   matrix[i][currCapacity] =  matrix[i-1][currCapacity];
                }
            }
        }
        return matrix[val.length][W];   
    } 
}


