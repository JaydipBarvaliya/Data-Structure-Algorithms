class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        int[][][] dp = new int[n][2][3];
        
        for (int[][] secondLayer : dp)
            for (int[] thirdLayer : secondLayer)
                Arrays.fill(thirdLayer, -1);
        
        
        return f(0, 1, 2, prices, n, dp);
    }
    
    private int f(int ind, int buy, int cap, int[] prices, int n, int[][][] dp) {
        
        // Base Case
        if (cap == 0) return 0;
        
        
        if (ind == n) return 0;
        
        if (dp[ind][buy][cap] != -1) {
            return dp[ind][buy][cap];
        }
        
        int profit = Integer.MIN_VALUE;
        
        if (buy == 1) {
            // let's buy or don't want to buy
            int take = -prices[ind] + f(ind + 1, 0, cap, prices, n, dp);
            int notTake = 0 + f(ind + 1, 1, cap, prices, n, dp);
            
            profit = Math.max(take, notTake);
        }
        else {
            // let's sell or don't want to sell
            int sell = prices[ind] + f(ind + 1, 1, cap - 1, prices, n, dp);
            int notSell = 0 + f(ind + 1, 0, cap, prices, n, dp);
            
            profit = Math.max(sell, notSell);
        }
        
        return dp[ind][buy][cap] = profit;
    }
}
