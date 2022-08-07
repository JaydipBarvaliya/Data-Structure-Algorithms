class Solution{
    static int equalPartition(int len , int items[]){
        
        int sum=0;
        
        for(int i: items)sum = sum + i; 

        if(sum % 2 != 0) return 0;
        
        return isSubsetSum(items, sum/2) ? 1 : 0;
    }
    
    static boolean isSubsetSum(int items[], int totalSum){
		
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
		for (int[] row : matrix)
		    System.out.println(Arrays.toString(row));
		return matrix[items.length][totalSum];
	}
}
