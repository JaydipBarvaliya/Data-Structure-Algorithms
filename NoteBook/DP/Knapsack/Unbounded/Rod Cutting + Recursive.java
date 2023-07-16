// Java recursive solution for Rod cutting problem
class GFG {

	static int cutRod(int price[], int idx, int n){
	    
	    
		if (idx == 0)  return n * price[0];
		

		int rodLlength = idx + 1;

		if (rodLlength <= n){
		    return Math.max( price[idx] + cutRod(price, idx, n - rodLlength),
		                                    cutRod(price, idx - 1, n));
		}
		
		return cutRod(price, idx - 1, n);
	}


	public static void main(String args[]){
		int arr[] = { 1, 5, 8, 9, 10, 17, 17, 20 };
		int size = arr.length;
		System.out.println("Maximum Obtainable Value is " + cutRod(arr, size - 1, size));
	}
}
