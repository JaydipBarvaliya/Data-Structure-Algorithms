//https://leetcode.com/problems/is-subsequence/

class Solution {
    public boolean isSubsequence(String s, String t) {
        
        char[] X = s.toCharArray();
        char[] Y = t.toCharArray();
        int m = X.length;
        int n = Y.length;
        
        if(m == 0) return true;

        if(m != 0 && n ==0 ) return false;
        
        int matrix[][] = new int[ m+1 ][ n+1 ];
        
        for(int i=1; i<m+1; i++){
        
            for(int j=1; j<n+1; j++){
                if (X[i-1] == Y[j-1]){
                    matrix[i][j] =  1 + matrix[i-1][j-1];  
                }else {
                    matrix[i][j] =  Math.max( matrix[i][j-1], matrix[i-1][j] );
                }
            }
        }
        return matrix[m][n] == Math.min(m, n);
            
    }
}
