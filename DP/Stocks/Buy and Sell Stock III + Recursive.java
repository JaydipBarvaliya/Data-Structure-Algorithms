class Solution {
    public int maxProfit(int[] prices) {
        int n = prices.length;
        
        return f(0, 1, 2, prices, n);
    }
    
    private int f(int index, int buy, int cap, int[] prices, int n) {
        
        // Base Case
        if (cap == 0) {
            return 0;
        }
        
        if (index == n) {
            return 0;
        }
        
        
        int profit = Integer.MIN_VALUE;
        if (buy == 1) {
            // let's buy or don't want to buy
            int take = -prices[index] + f(index + 1, 0, cap, prices, n);
            int notTake = 0 + f(index + 1, 1, cap, prices, n);
            
            profit = Math.max(take, notTake);
        }
        else {
            // let's sell or don't want to sell
            int sell = prices[index] + f(index + 1, 1, cap - 1, prices, n);
            int notSell = 0 + f(index + 1, 0, cap, prices, n);
            
            profit = Math.max(sell, notSell);
        }
        
        return profit;
    }
}
