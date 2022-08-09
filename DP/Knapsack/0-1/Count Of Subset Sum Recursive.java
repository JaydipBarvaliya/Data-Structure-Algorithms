class Solution{

	public int perfectSum(int arr[],int n, int sum) { 
	      return subsetSum(arr, n, sum, 0, 0);
	} 
	
	static int subsetSum(int arr[], int n, int targetSum, int subsetSum, int subsetCount){
	
		if (n == 0) {
		    if (subsetSum == targetSum) subsetCount++;
	     	    return subsetCount;
		}

		subsetCount = subsetSum(arr, n - 1, targetSum, subsetSum, subsetCount);
		subsetCount = subsetSum(arr, n - 1, targetSum, subsetSum + arr[n - 1], subsetCount);
		return subsetCount;
	}
	
}
