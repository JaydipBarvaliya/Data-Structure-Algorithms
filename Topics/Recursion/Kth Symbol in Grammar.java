class Solution {
    public int kthGrammar(int n, int k) {
        
        if(k==1 || n==1) return 0;

        int mid = (int) Math.pow(2, n-1)/2;
        
        if(k<=mid){
             return kthGrammar(n-1, k);
        }else{
             return kthGrammar(n-1, k-mid) ^1;    // Here we use ^ to inverse the bit..          
        }
    }
}

