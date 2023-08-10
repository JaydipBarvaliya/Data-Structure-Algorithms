class Solution{

	public int minDifference(int arr[], int n){ 
	    
	    int totalSum=0;
	    
	    for(int ele: arr)
	        totalSum = totalSum + ele;
	        
	   ArrayList<Integer> list = findSubSetsList(arr, n, totalSum);
	   
	   int minDiff = Integer.MAX_VALUE;
	   for(int sum1 : list)
	        minDiff = Math.min(minDiff, totalSum - (2 * sum1));
	        
	   return minDiff;
	    
	} 
	
	
	public ArrayList<Integer> findSubSetsList(int arr[], int n, int sum){
	    
	    
	    boolean[][] matrix = new boolean[n+1][sum+1];
	    
	    
	    for(int j=0; j<sum+1; j++)
	        matrix[0][j] = false;
	        
        for(int i=0; i<n+1; i++)
	        matrix[i][0] = true;
	        
	       for(int i=1; i<n+1; i++){
	           
	           for(int j=1; j<sum+1; j++){
	               
	               if(arr[i-1] <= j ){
        	            matrix[i][j] = matrix[i-1][j-arr[i-1]] || matrix[i-1][j];
        	       }else{
        	            matrix[i][j] = matrix[i-1][j];
        	       }
	           }
	           
	       } 
	        
	        ArrayList<Integer> list = new ArrayList<>();
	        
	        
	        boolean[] lastRow = matrix[n];
	        
	        for( int possibleSum=0; possibleSum<=sum/2; possibleSum++)
	             if(lastRow[possibleSum] == true) list.add(possibleSum);
	    
	        return list;
	    
	}
}