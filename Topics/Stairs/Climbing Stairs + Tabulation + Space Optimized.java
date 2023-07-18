//The main reason for this optimization is that at every step we only look the 2 previous steps...so we do not require all the steps which is back of these two back steps.
//So we only try to track only two previous step using variable....


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
