// This approach is correct however, as per the GFG there is tons of constraints has been given so we need to mold this code as per those constraints
// Use 3D array instead of map as even an unordered map takes logn time to find the key and when the key is string the the time complexity will be O(s.length()logn) so it'll give tle.

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

class Solution{
    
    static int countWays(int N, String str){
        
        int dp[][][] = new int[N + 1][N + 1][2];

        for (int row[][] : dp)
            for (int col[] : row)
                Arrays.fill(col, -1);

        return solve(str.toCharArray(), 0, N-1, 1, dp);
    }
    
    public static int solve(char[] str, int i, int j, int isTrue, int[][][] dp){
        
        if(i>j) return 0;
        
        if (i == j){
            if (isTrue == 1){
                return (str[i] == 'T') ? 1 : 0;
            }else if(isTrue == 0){
                return (str[i] == 'F') ? 1 : 0;
            }
        }

        if(dp[i][j][isTrue] != -1) return dp[i][j][isTrue];
        
        int ans = 0;
        int leftTrue   = 0;
        int leftFalse  = 0;
        int rightTrue  = 0;
        int rightFalse = 0;
            
        for(int k=i+1; k<=j-1; k=k+2){
            
            if(dp[i][k-1][1] != -1){
                 leftTrue = dp[i][k-1][1];
            }else{
                 leftTrue =  solve(str, i, k-1, 1, dp);
            }
            
            if(dp[i][k-1][0] != -1){
                 leftFalse = dp[i][k-1][0];
            }else{
                 leftFalse =  solve(str, i, k-1, 0, dp);
            }

            if(dp[k+1][j][1] != -1){
                 rightTrue = dp[k+1][j][1];
            }else{
                 rightTrue =  solve(str, k+1, j, 1, dp);

             if(dp[k+1][j][0] != -1){
                 rightFalse = dp[k+1][j][0];
            }else{
                 rightFalse =  solve(str, k+1, j, 0, dp);
            }
            
            if(str[k] == '&'){
                if(isTrue == 1){
                    ans = ans + (leftTrue*rightTrue);   
                }else{
                    ans = ans + (leftFalse*rightFalse) + (leftTrue*rightFalse) + (leftFalse*rightTrue);
                }
            }else if(str[k] == '|'){
                if(isTrue == 1){
                    ans = ans + (leftTrue*rightTrue) + (leftTrue*rightFalse) + (leftFalse*rightTrue);   
                }else{
                    ans = ans + (leftFalse*rightFalse);
                }
            }else if(str[k] == '^'){
                if(isTrue == 1){
                    ans = ans + (leftTrue*rightFalse) + (leftFalse*rightTrue); 
                }else{
                    ans = ans + (leftTrue*rightTrue) + (leftFalse*rightFalse);
                }
            }
        
        
            dp[i][j][isTrue] = ans;
        }
        }
        dp[i][j][isTrue] = ans;
        return ans;
    }
}
