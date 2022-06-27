class Solution {
    public int numIslands(char[][] matrix){
        int count=0;
        for(int i=0; i<matrix.length; i++){
          for(int j=0; j<matrix[0].length; j++){
            if(matrix[i][j] == '0') continue;
               count++;
               dfs(matrix, i, j); 
          }
        }

        return count;
    }

    public void dfs(char[][] matrix, int i, int j){

        if(i<0 || j<0 || i == matrix.length || j == matrix[0].length) return;
        if(matrix[i][j] == '0') return;
        
        matrix[i][j] = '0';

        dfs(matrix, i,   j+1);
        dfs(matrix, i+1, j);
        dfs(matrix, i,   j-1);
        dfs(matrix, i-1, j);

        return;
  }
                          
                          
}
