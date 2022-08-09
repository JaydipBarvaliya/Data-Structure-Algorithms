class Solution{
    
    static int knapSack(int n, int W, int val[], int wt[]){
        
        if(W == 0 || n==0) return 0;
        
         if(wt[n-1] <= W){
           return Math.max(val[n-1] + knapSack(n, W-wt[n-1], val, wt),
                                      knapSack(n-1, W, val, wt));
        }else{
            return knapSack(n-1, W, val, wt);
        }
        
    }
    
}
