import java.util.Arrays;

class Solution{
	
    static Boolean isSubsetSum(int N, int arr[], int totalSum){

		boolean[][] matrix;
		matrix = new boolean[arr.length + 1][totalSum + 1];

		for (int col=0;  col<=totalSum;  col++)
			matrix[0][col] = false;

		for (int row=0;  row<arr.length+1;  row++)
			matrix[row][0] = true;


		for (int i = 1; i <= arr.length; i++) {
			
			for (int j = 1; j <= totalSum; j++) {
			    if(arr[i-1] <= j){
			        matrix[i][j] = matrix[i-1][j] || matrix[i-1] [j - arr[i-1]];
			    }else{
			    	matrix[i][j] = matrix[i-1][j];			        
			    }
            }
		}
		for (boolean[] row : matrix)
            		System.out.println(Arrays.toString(row));
		return matrix[arr.length][totalSum];
    }
}
