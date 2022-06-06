class Solution{
    public int LongestRepeatingSubsequence(String str){
        
	char[] X = str.toCharArray();
	char[] Y = str.toCharArray();
	int m = X.length;
	int n = Y.length;
        
    int matrix[][] = new int[ m+1 ][ n+1 ];

    for(int i=1; i<m+1; i++){
        
        for(int j=1; j<n+1; j++){
            
            if (X[i-1] == Y[j-1] && i!=j){
        	    matrix[i][j] =  1 + matrix[i-1][j-1];  
        	}else {
        	    matrix[i][j] =  Math.max( matrix[i][j-1], matrix[i-1][j] );
        	}
                    
        }
    }
	return matrix[m][n];
    }
}
