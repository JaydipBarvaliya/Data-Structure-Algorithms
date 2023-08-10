
public class LongestCommonSubsequence{

int lcs( char[] X, char[] Y, int m, int n ){
  
    int matrix[][] = new int[ m+1 ][ n+1 ];

    //Here we don't require to fill the matrix with 0 (for first row and first column) because int matrix is
    // by default inililize with 0.
    
    // for(int col=0; col<n+1; col++)
    //     matrix[0][col] = 0;
      
    // for(int row=1; row<m+1; row++)
    //     matrix[row][0] = 0;
    
    
    // for (int[] row : matrix)
		  //  System.out.println(Arrays.toString(row));

    for(int i=1; i<m+1; i++){
        
        for(int j=1; j<n+1; j++){
            
            if (X[i-1] == Y[j-1]){
        	    matrix[i][j] =  1 + matrix[i-1][j-1];  
        	}else {
        	    matrix[i][j] =  Math.max( matrix[i][j-1], matrix[i-1][j] );
        	}
                    
        }
    }
	return matrix[m][n];
	}
}
