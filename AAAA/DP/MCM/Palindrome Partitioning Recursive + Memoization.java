class Solution{
    static int palindromicPartition(String str){
        
        int[][] matrix = new int[str.length() + 1 ][str.length() + 1];
        for (int[] row : matrix)
            Arrays.fill(row, -1);
        
        return solve(matrix, str, 0, str.length()-1);
    }
    
    static int solve(int[][] matrix, String str, int i, int j){
        
        
        if( i>=j || isPalindrome(str, i, j) ) return 0;
        if(matrix[i][j] != -1) return matrix[i][j];

        
        
        int min = Integer.MAX_VALUE;
        for(int k=i; k<j; k++){
            int tempAns = 1 + solve(matrix, str, i, k) + solve(matrix, str, k+1, j);
            min  = Math.min(min, tempAns);
        }
        
        
        matrix[i][j] = min;
        return min;
    }
    
     static boolean isPalindrome(String str, int i, int j){
        
        while(i < j){
          if(str.charAt(i) != str.charAt(j))
            return false; 
          i++;
          j--;
        }
        
        return true;
      }
}
