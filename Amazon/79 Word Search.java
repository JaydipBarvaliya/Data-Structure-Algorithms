class Solution {
    public boolean exist(char[][] matrix, String word) {
        
        int count=0;
        for(int i=0; i<matrix.length; i++){
          for(int j=0; j<matrix[0].length; j++){
            if(matrix[i][j] != word.charAt(0)) continue;
               boolean isExist = dfs(matrix, i, j, word, 0);
               if(isExist==true) return true;
          }
        }

        return false;
    }
    
    public boolean dfs(char[][] matrix, int i, int j, String word, int idx){
        
        if(idx==word.length()) return true;

        if(i<0 || j<0 || i == matrix.length || j == matrix[0].length || matrix[i][j] != word.charAt(idx)) return false;
        
        matrix[i][j] = '#';
        
        //Remeber the concept of short circuit applies here since we'll still recurse all those different options, even
        //when we have already found a valid path. 
        boolean ret =  dfs(matrix, i,   j+1, word, idx+1) ||
                       dfs(matrix, i+1, j,   word, idx+1) ||
                       dfs(matrix, i,   j-1, word, idx+1) ||
                       dfs(matrix, i-1, j,   word, idx+1);
        //resetting the matrix, because who know ans may lie further/the elements which we have explored in the path
        matrix[i][j] = word.charAt(idx);

        return ret;
  }
}
