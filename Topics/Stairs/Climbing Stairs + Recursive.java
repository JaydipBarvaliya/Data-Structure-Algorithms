class Solution {
    
        int climbStairs(int n) {
            return solve(n);
        }
           
        int solve(int n){
            if(n < 2) return 1;
            return solve(n - 1) + solve(n-2);
        }
};
