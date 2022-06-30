class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n + 1][2][3];
        
        
        for (int ind = n - 1; ind >= 0; ind--) {
            for (int buy = 0; buy <= 1; buy++) {
                for (int cap = 1; cap <= 2; cap++) {
                    
                    int profit = Integer.MIN_VALUE;
                    if (buy == 1) {
                        // let's buy or don't want to buy
                        int take = -prices[ind] + dp[ind + 1][0][cap];
                        int notTake = 0 + dp[ind + 1][1][cap];

                        profit = Math.max(take, notTake);
                    }
                    else {
                        // let's sell or don't want to sell
                        int sell = prices[ind] + dp[ind + 1][1][cap - 1];
                        int notSell = 0 + dp[ind + 1][0][cap];

                        profit = Math.max(sell, notSell);
                    }

                    dp[ind][buy][cap] = profit;
                }
            }
        }
        
        return dp[0][1][2];
    }
}
