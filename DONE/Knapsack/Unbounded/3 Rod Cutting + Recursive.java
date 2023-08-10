class Solution {

	static int cutRod(int price[], int idx, int n){
	    
	    
		if (idx == 0)  return n * price[0];

		int rodLength = idx + 1;

		if (rodLength <= n){
		    return Math.max( price[idx] + cutRod(price, idx, n - rodLength),
		                                    cutRod(price, idx - 1, n));
		}
		
		return cutRod(price, idx - 1, n);
	}
}
