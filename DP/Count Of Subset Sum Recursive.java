// { Driver Code Starts
//Initial Template for Java

import java.io.*;
import java.util.*;
class GfG
{
    public static void main(String args[])
        {
            Scanner sc = new Scanner(System.in);
            int t = sc.nextInt();
            while(t-->0)
                {
                    int n = sc.nextInt();
                    int sum = sc.nextInt();
                    int arr[] = new int[n];
                    for(int i = 0;i<n;i++)
                    arr[i] = sc.nextInt();
                    
                    Solution ob = new Solution();
                    System.out.println(ob.perfectSum(arr,n,sum));
                }
        }
}    // } Driver Code Ends


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
