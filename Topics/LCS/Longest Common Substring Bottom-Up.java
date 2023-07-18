public class LongestCommonSubsequence{

int lcs( char[] X, char[] Y, int m, int n ){
  
    int matrix[][] = new int[ m+1 ][ n+1 ];
    
    int MAX=0;
    
    for(int i=1; i<m+1; i++){
        
        for(int j=1; j<n+1; j++){
            
            if (X[i-1] == Y[j-1]){
        	    matrix[i][j] =  1 + matrix[i-1][j-1];  
				MAX = Math.max(matrix[i][j], MAX);
        	}else {
        	    matrix[i][j] =  0;
        	}
                    
        }
    }
	return MAX;
}
}
