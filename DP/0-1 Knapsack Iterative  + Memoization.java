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
            
            
            for(int c=1; c<W+1; c++){
                
                if(currentWeight <= c){
                   matrix[i][c] = Math.max( matrix[i-1][c] ,
                                            matrix[i-1][c-currentWeight] + currentValue );
                }else{
                   matrix[i][c] =  matrix[i-1][c];
                }
            }
        }
        return matrix[val.length][W];   
    } 
}


