class Solution{

	public int minDifference(int arr[], int n){ 
        
        int totalSum = 0;
        for(int summer: arr) totalSum += summer;
        
        List<Integer> list  = isSubsetSum(arr, totalSum);
        
        int minDiff = Integer.MAX_VALUE;
        for(int sum1: list)
            if( (totalSum - (2*sum1)) < minDiff) minDiff = totalSum - (2*sum1);
        
        return minDiff;
	} 

	
	
	
	static List<Integer> isSubsetSum(int items[], int totalSum){
		
		boolean matrix[][] = new boolean[items.length + 1][totalSum + 1];



		
		for (int col=0;  col<=totalSum;  col++)
			matrix[0][col] = false;
		for (int row=0;  row<items.length+1;  row++)
			matrix[row][0] = true;




		for (int i = 1; i <= items.length; i++) {
			
			int currentItem  = items[i-1];
			for (int j = 1; j <= totalSum; j++) {
			    if(currentItem <= j){
			        matrix[i][j] = matrix[i-1][j] || matrix[i-1] [j - currentItem];
			    }else{
			    	matrix[i][j] = matrix[i-1][j];			        
			    }
            }
		}
		
		List<Integer> list = new ArrayList<Integer>();
		
		boolean lastRow[] = matrix[items.length];

		for(int sum=0; sum<=(totalSum)/2; sum++){
		       if(lastRow[sum]) list.add(sum);
		}
		
		return list;
	}

}
