// { Driver Code Starts
import java.io.*;
import java.util.*;

class GFG
{
    public static void main(String args[])throws IOException
    {
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        while(t-- > 0)
        {
            int N = Integer.parseInt(read.readLine());
            String input_line[] = read.readLine().trim().split("\\s+");
            int arr[]= new int[N];
            for(int i = 0; i < N; i++)
                arr[i] = Integer.parseInt(input_line[i]);
            int sum = Integer.parseInt(read.readLine());

            Solution ob = new Solution();
            if(ob.isSubsetSum(N, arr, sum))
            System.out.println(1);
            else
            System.out.println(0);

            
        }
    }
}
// } Driver Code Ends


//User function Template for Java

class Solution{


    static Boolean isSubsetSum(int N, int items[], int totalSum){
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

		return matrix[items.length][totalSum];
    }
}
