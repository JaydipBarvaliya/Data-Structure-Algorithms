// { Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;
class GfG
{
    public static void main(String args[])
        {
            Scanner sc = new Scanner(System.in);
            int t = sc.nextInt();
            while(t-->0)
                {
                    int n = sc.nextInt();
                    int sum = sc.nextInt();
                    int arr[] = new int[n];
                    for(int i = 0;i<n;i++)
                    arr[i] = sc.nextInt();
                    
                    Solution ob = new Solution();
                    System.out.println(ob.perfectSum(arr,n,sum));
                }
        }
}    // } Driver Code Ends


class Solution{

	public int perfectSum(int items[],int n, int totalSum) { 
	    
	    int matrix[][] = new int[items.length + 1][totalSum + 1];

		for (int col=1;  col<=totalSum;  col++)
			matrix[0][col] = 0;
        

        matrix[0][0] = 1;
        int zeroCount = 0;
        
		for (int row=1;  row<items.length+1;  row++){
	        if(items[row-1] == 0) { 
	            zeroCount++;
	            matrix[row][0] = (int)Math.pow(2, zeroCount);
	        }else{
	            matrix[row][0] = (int)Math.pow(2, zeroCount);
	        }
    	}
    			 

		for (int i = 1; i <= items.length; i++) {
			
			int currentItem  = items[i-1];
			
			for (int j = 1; j <= totalSum; j++) {
			    if(currentItem <= j){
			        matrix[i][j] = matrix[i-1][j] + matrix[i-1] [j - currentItem];
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
