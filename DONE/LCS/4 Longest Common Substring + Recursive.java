//Leetcode : 718. Maximum Length of Repeated Subarray

class Solution{
    
    int longestCommonSubstr(String s1, String s2, int p, int q){
        
        char[] X=s1.toCharArray();
    	char[] Y=s2.toCharArray();
    	int m = X.length;
    	int n = Y.length;
	
        return lcs(X, Y, m, n, 0);
    }

    
    static int lcs(char[] X, char[] Y, int i, int j, int count){

		if (i == 0 || j == 0) return count;
		

		if (X[i-1] == Y[j-1]){
			count = lcs(X, Y, i - 1, j - 1, count + 1);
		}
		
		//if chars are not matching that time we are comparing lcs(left and right) with the count that is derived from earlier...
		// as we can see that when chars do not match above condition got failed
		count = Math.max(count, Math.max(lcs(X, Y, i, j - 1, 0), lcs(X, Y, i - 1, j, 0)));
		
		return count;
	}
}