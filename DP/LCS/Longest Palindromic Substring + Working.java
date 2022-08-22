class Solution {
    public String longestPalindrome(String str) {
        
        StringBuilder reverseStr = new StringBuilder();

        reverseStr.append(str).reverse();

        return lcs(str.toCharArray(), reverseStr.toString().toCharArray(), str.length(), reverseStr.length() );
        
    }
    
    
    
    
String lcs( char[] X, char[] Y, int m, int n ){
  
    
    int matrix[][] = new int[ m+1 ][ n+1 ];

    //Here we don't require to fill the matrix with 0 (for first row and first column) because int matrix is
    // by default inililize with 0.
    
    for(int i=1; i<m+1; i++){
        
        for(int j=1; j<n+1; j++){
            
            if (X[i-1] == Y[j-1]){
                
        	    matrix[i][j] =  1 + matrix[i-1][j-1];  
                
        	}else {
                
        	    matrix[i][j] =  0;
                
        	}
                    
        }
    }
    
    
    StringBuilder builder = new StringBuilder();
    
    m=1; 
    n=1;
    while(m<matrix.length && n<matrix[0].length){
        
        if(X[m-1] == Y[n-1]) builder.append(X[m-1]);
        m++;
        n++;
    }
    
    if(builder.length()==0 && X.length==2) return Character.toString(X[0]);
	return builder.toString(); 
}
}