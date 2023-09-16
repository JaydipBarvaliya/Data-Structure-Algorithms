//{ Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;

class GFG{
    public static void main(String args[])throws IOException
    {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(in.readLine());
        while(t-- > 0){
            String str = in.readLine();
            Solution ob = new Solution();
            System.out.println(ob.palindromicPartition(str));
        }
    }
}
// } Driver Code Ends


class Solution{
    static int palindromicPartition(String str){
        
        int[][] matrix = new int[str.length()][ str.length()];
        
        for(int[] row : matrix)
                Arrays.fill(row, -1);
        
        return solve(str, 0, str.length()-1, matrix);
    }
    
    static Map<String, Integer> map = new HashMap<>();
    
    static int solve(String str, int i, int j, int[][] matrix){

        if( i>=j || isPalindrom(str, i, j)){
            matrix[i][j] = 0;
            return 0;
        }

        if(matrix[i][j] != -1) return matrix[i][j];
        
        int min = Integer.MAX_VALUE;        
        
        for( int k=i ; k<j; k++){
  
            int leftMin  = 0;
            int rightMin = 0;
            
            if(matrix[i][k] !=-1){
                leftMin = matrix[i][k];
            }else{
                leftMin = solve(str, i, k, matrix);
                matrix[i][k] = leftMin;
            }
            
            
            if(matrix[k+1][j] !=-1){
                rightMin = matrix[k+1][j];
            }else{
                rightMin = solve(str, k+1, j, matrix);
                matrix[k+1][j] = rightMin;
            }
            
            int tempAns = 1 + leftMin + rightMin;
            min = Math.min(min, tempAns);
        }
        
        matrix[i][j] = min;
        return min;
    }
    
    static boolean isPalindrom(String str, int i, int j){
        
        while(i<=j){
            
            if(str.charAt(i) == str.charAt(j)){
                i++;
                j--;
            }else{
                return false;
            }
        }
        return true;
    }
}