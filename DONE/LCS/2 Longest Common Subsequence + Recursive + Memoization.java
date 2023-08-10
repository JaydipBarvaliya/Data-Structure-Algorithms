class Solution {
    int lcs( int matrix[][], char[] X, char[] Y, int m, int n ){
  
  
	if (m == 0 || n == 0) return 0;
	
	if(matrix[m][n] != -1) return matrix[m][n];
	
	if (X[m-1] == Y[n-1]){
	    matrix[m][n] =  1 + lcs(matrix, X, Y, m-1, n-1);  
	}else {
	    matrix[m][n] =  Math.max( lcs(matrix, X, Y, m, n-1), lcs(matrix, X, Y, m-1, n) );
	}
	
	return matrix[m][n];
}
    
}