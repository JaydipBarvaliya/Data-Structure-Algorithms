class Solution {

        int climbStairs(int n) {
            
        //Space optimization
        //  .     .      .    .   .   .   .
        //prev2  prev   curr
        //      prev2   prev  curr
            
        int prev = 1;
        int prev2 = 0;
        for(int i = 2 ; i <= n + 1 ; i++){
            int curr = prev + prev2;
            prev2 = prev;
            prev = curr;
        }
        return prev;   
        }
};
