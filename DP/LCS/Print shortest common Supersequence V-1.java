import java.util.*;
import java.io.*;
import java.lang.*;

public class LongestCommonSubsequence{

int lcs( char[] X, char[] Y, int m, int n ){
  
    int matrix[][] = new int[ m+1 ][ n+1 ];

    for(int i=1; i<m+1; i++){
        
        for(int j=1; j<n+1; j++){
            
            if (X[i-1] == Y[j-1]){
        	    matrix[i][j] =  1 + matrix[i-1][j-1];  
        	}else {
        	    matrix[i][j] = Math.max( matrix[i][j-1], matrix[i-1][j] );
        	}
        }
    }
    
    // for (int[] row : matrix)
		  //  System.out.println(Arrays.toString(row));
    
    m = matrix.length-1;
    n = matrix[0].length-1;
    List<Character> list = new ArrayList<Character>();
    
    while(m>0 && n>0){
        
        if(X[m-1] == Y[n-1]){
            list.add(0, X[m-1]);
            m--;
            n--;
            
        }else if (matrix[m-1][n] > matrix[m][n-1]){
            list.add(0, X[m-1]);
            m--;
            
        }else if(matrix[m][n-1] > matrix[m-1][n] ){
            list.add(0, Y[n-1]);
            n--;
        }
    }
    
    while(m>0){
        list.add(0, X[m-1]);
        m--;
    }
    
    while(n>0){
        list.add(0, Y[n-1]);
        n--;
    }
    
    for(Character ele : list){
        System.out.print(ele);
    }
    
	return 0; 
}



public static void main(String[] args){
	LongestCommonSubsequence lcs = new LongestCommonSubsequence();
	String s1 = "acbcf";
	String s2 = "abcdaf";

	char[] X=s1.toCharArray();
	char[] Y=s2.toCharArray();
	int m = X.length;
	int n = Y.length;
	lcs.lcs(X, Y, m, n );
// 	System.out.println("Length of LCS is" + " " + );
}

}
