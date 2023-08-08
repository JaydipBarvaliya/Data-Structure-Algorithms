class Solution{

	public int perfectSum(int arr[],int idx, int sum) {
	      return subsetSum(arr, idx, sum, 0, 0);
	} 
	
	static int subsetSum(int arr[], int idx, int targetSum, int subsetSum, int subsetCount){
	
		if (idx == 0) {
			if (subsetSum == targetSum){
				 subsetCount++;
	     	    return subsetCount;
		}

		subsetCount = subsetSum(arr, idx - 1, targetSum, subsetSum, subsetCount);
		subsetCount = subsetSum(arr, idx - 1, targetSum, subsetSum + arr[idx - 1], subsetCount);
		return subsetCount;
	}
	
}
}