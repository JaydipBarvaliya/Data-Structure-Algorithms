class Solution{


    static Boolean isSubsetSum(int n, int arr[], int sum){
        
        if (sum == 0) return true;
        if (n   == 0) return false;
        
        // If last element is greater than sum, then ignore it

        if (arr[n - 1] <= sum){
            
            /* Check if sum can be obtained by any of the following
            (a) including the last element
            (b) excluding the last element */
            return isSubsetSum(n-1, arr, sum-arr[n-1]) || isSubsetSum(n-1, arr, sum);
        }
        else{
            return isSubsetSum(n-1, arr, sum);
        }
            
    }
}
