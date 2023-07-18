class Solution {

        int[] dp = new int[46];

        int climbStairs(int n) {
            
            dp[0] = 1; //If we have 0 steps given still we have 1 way to reach TOP...and we can consider ourself as we are standing at top  
            dp[1] = 1; //if we have 1 steps given then there is only 1 way to reach top ..which is only take 1 step....we can't take 2 otherwise we fall out of stair
            
            for(int i=2; i<=n; i++){
             dp[i] = dp[i-1] + dp[i-2];
            }
            
            return dp[n];
        }
};
