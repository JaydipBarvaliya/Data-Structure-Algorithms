class Solution{

    public int minDifference(int arr[], int len) {

	    int totalSum = 0;

	    for(int summer : arr)
                totalSum+=  summer;

	    return recur(arr, len, 0, totalSum);
	}

	public int recur(int arr[], int len, int sumTill, int totalSum){
	
	    if(len == 0) return Math.abs(sumTill - (totalSum-sumTill) );
	    
	    return Math.min(recur(arr, len-1, arr[len-1]+sumTill, totalSum),
	                    recur(arr, len-1, sumTill, totalSum));

	}
}
