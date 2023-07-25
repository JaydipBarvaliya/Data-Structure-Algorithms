import java.util.LinkedList;
import java.util.Queue;




class Pair{
    int row;
    int col;
    int unit;

    public Pair(int row, int col, int unit){
        this.row  = row;
        this.col  = col;
        this.unit = unit;
    }
}

class Solution{
    
    public int orangesRotting(int[][] grid){
        
        
        int n = grid.length;
        int m = grid[0].length;

        int[][] visited = new int[n][m];
        Queue<Pair> queue = new LinkedList<>();
        
        int freshCount = 0;

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){

                if(grid[i][j]==2){
                    queue.add(new Pair(i, j, 0));
                    visited[i][j] = 2;
                }else{
                    visited[i][j] = 0;
                }

                if(grid[i][j]==1) freshCount++;
            }   
        }

        int maxUnit = 0;
        int rottenCount = 0;
        int deltaRow[] = {-1, 0, +1, 0};
        int deltaCol[] = {0,  1, 0, -1};

        while (!queue.isEmpty()) {
            
            int currRow  = queue.peek().row;
            int currCol  = queue.peek().col;
            int currUnit = queue.peek().unit;
            queue.remove();
            
            maxUnit = Math.max(maxUnit, currUnit);
            for(int i=0;i<4;i++){

                int nextRow = currRow  + deltaRow[i];
                int nextCol = currCol  + deltaCol[i];

                if(nextRow >= 0 && nextRow<n && nextCol>=0 && nextCol<m && grid[nextRow][nextCol]==1  && visited[nextRow][nextCol]==0){
                    queue.add(new Pair(nextRow, nextCol, currUnit+1));
                    visited[nextRow][nextCol] = 2;
                    rottenCount++;
                }
            }
        }

        if(freshCount!=rottenCount) return -1;
        return maxUnit;
    }
}