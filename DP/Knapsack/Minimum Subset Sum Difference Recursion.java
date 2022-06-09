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
                    int A[] = new int[n];
                    for(int i = 0;i<n;i++)
                        A[i] = sc.nextInt();
                    Solution ob = new Solution();
                    System.out.println(ob.minDifference(A,n));
                }
        }
}    // } Driver Code Ends



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
