class Solution{


    static Boolean isSubsetSum(int N, int arr[], int sum){

        boolean[][] matrix = new boolean[N+1][sum+1];
        
        for(int j=0; j<sum+1; j++)
		
		//Make sure you mark first row as FALSE and then mark first column as TRUE so matrx[0][0] will stay true
		//Because all in all no matter we do not have any item but still we can make sum=0 by not counting any of the items which means {}
            matrix[0][j] = false; 
            
            
        for(int i=0; i<N+1; i++)
            matrix[i][0] = true;
        
			
        for(int i=1; i<N+1; i++){
            
            for(int j=1; j<sum+1; j++){
                
                if(arr[i-1] <= j){
                    matrix[i][j] = matrix[i-1][j-arr[i-1]] || matrix[i-1][j];
                }else{
                    matrix[i][j] = matrix[i-1][j];
                }
            }   
        }
        
    return matrix[N][sum];        
    }
}