// { Driver Code Starts
import java.util.*;
import java.io.*;
import java.lang.*;

class gfg
{
    public static void main(String args[])throws IOException
    {
        //reading input using BufferedReader class
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        
        //reading total testcases
        int t = Integer.parseInt(read.readLine());
        
        while(t-- > 0)
        {
            //reading number of elements and weight
            int n = Integer.parseInt(read.readLine());
            int w = Integer.parseInt(read.readLine());
            
            int val[] = new int[n];
            int wt[] = new int[n];
            
            String st[] = read.readLine().trim().split("\\s+");
            
            //inserting the values
            for(int i = 0; i < n; i++)
              val[i] = Integer.parseInt(st[i]);
             
            String s[] = read.readLine().trim().split("\\s+"); 
            
            //inserting the weigths
            for(int i = 0; i < n; i++)
              wt[i] = Integer.parseInt(s[i]);
              
            //calling method knapSack() of class Knapsack
            System.out.println(new Solution().knapSack(w, wt, val, n));
        }
    }
}



// } Driver Code Ends


class Solution { 
    //Function to return max value that can be put in knapsack of capacity W.
    
    static int knapSack(int W, int wt[], int val[], int n){ 
        
        int matrix[][] = new int[ val.length+1 ][ W+1 ];
        
        for (int[] row : matrix)
            Arrays.fill(row, -1);

         return knapSackWithMemo(matrix, val, wt, W, n);
         
    } 
    
    static int knapSackWithMemo(int matrix[][], int val[], int wt[], int W, int n){ 
        
        if(W == 0 || n==0) return 0;
        
        if(matrix[n][W] != -1) return matrix[n][W];
        
         if(wt[n-1] <= W){
           matrix[n][W] = Math.max(val[n-1] + knapSackWithMemo(matrix, val, wt, W-wt[n-1], n-1 ),
                                      knapSackWithMemo(matrix, val, wt, W, n-1));
        }else{
            matrix[n][W] =  knapSackWithMemo(matrix, val, wt, W, n-1);
        }
        return matrix[n][W]; 
    }
}


