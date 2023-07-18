class Solution {
    
        int[] dp = new int[46];
    
        int climbStairs(int n) {
            Arrays.fill(dp, -1);
            return solve(n);
        }
        
    
        int solve(int n){
            if(n < 2) return 1;
            if(dp[n] != -1) return dp[n];
            dp[n] = solve(n - 1) + solve(n-2);
            return dp[n];
        }
};
