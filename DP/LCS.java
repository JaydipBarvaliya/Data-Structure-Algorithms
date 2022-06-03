// { Driver Code Starts
//Initial Template for Java

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
            String input[] = read.readLine().trim().split(" ");
            int n = Integer.parseInt(input[0]);
            int m = Integer.parseInt(input[1]);
            
            String S1 = read.readLine().trim();
            String S2 = read.readLine().trim();

            Solution ob = new Solution();
            System.out.println(ob.longestCommonSubstr(S1, S2, n, m));
        }
    }
}// } Driver Code Ends


class Solution{
    int longestCommonSubstr(String s1, String s2, int n, int m){

        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();

        return LCS(ch1, ch2, n, m);
    }
    
    int LCS(char[] s1, char[] s2, int n, int m){
        
        if(n == 0 || m == 0) return 0;
        
        if( s1[n-1] == s2[m-1] ){
            return 1 + LCS(s1, s2, n-1, m-1);
        }else{
            return Math.max(LCS(s1, s2, n-1, m),
                            LCS(s1, s2, n, m-1));
        }
    }
    
    
    
}

    // if (m == 0 || n == 0) return 0;
    // if (X[m-1] == Y[n-1])
    // return 1 + lcs(X, Y, m-1, n-1);
    // else
    // return max(lcs(X, Y, m, n-1), lcs(X, Y, m-1, n));
    
