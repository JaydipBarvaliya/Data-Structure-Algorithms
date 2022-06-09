import java.io.*;
import java.util.*; 
class GFG{
    public static void main(String args[]) throws IOException { 
        BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(read.readLine());
        
        while(t-- > 0){
            int n = Integer.parseInt(read.readLine());
            String str = read.readLine().strip();
            Solution ob = new Solution();
            long ans = ob.minDeletions(str, n); 
            System.out.println(ans);
        }
    } 
}

class Solution { 
    int minDeletions(String str, int lenOfStr){
        
        StringBuilder reverseStr = new StringBuilder();
        reverseStr.append(str).reverse();
        
        int lenOfPalindromic = LCS(str.toCharArray(), reverseStr.toString().toCharArray(), str.length(), reverseStr.length() );
        
        return lenOfStr - lenOfPalindromic;
    }
    
    static int LCS(char[] X, char[] Y, int m, int n ){
      
        int matrix[][] = new int[ m+1 ][ n+1 ];
    
        for(int i=1; i<m+1; i++){
            
            for(int j=1; j<n+1; j++){
                
                if (X[i-1] == Y[j-1]){
            	    matrix[i][j] =  1 + matrix[i-1][j-1];  
            	}else {
            	    matrix[i][j] =  Math.max( matrix[i][j-1], matrix[i-1][j] );
            	}
            }
        }
    	return matrix[m][n];
      }
      
      
} 
