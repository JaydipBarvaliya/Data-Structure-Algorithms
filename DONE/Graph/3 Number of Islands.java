// I think the time complexity of the DFS is O(N*M) ie. basically the no of 0's and 1's. --> please Correct me if i am wrong.

// Explaination :
// so if suppose all elements were 0's (ie. sea) then the dfs will not run so its just nm)
// AND suppose if all elements were 1's only (ie all land) then once the dfs starts it will visit all the nodes and will make all of them 0, AND there will be no nodes left for traversal by the outer loops, --> so again equal to no of elements ie. nm

// So We can say that all the elements will be visited only once, doesn't matter if it is visited using dfs or just loops.

// Time  : O(N*M)
// Space : O(1)

class Solution {
    public int numIslands(char[][] matrix) {

        int count=0;

        for(int i=0; i<matrix.length; i++){
            for(int j=0; j<matrix[0].length; j++){
                if(matrix[i][j] ==  '1'){
                    drown(i, j, matrix);
                    count++;
                }
            }
        }
        return count;
    }

    public void drown(int i, int j, char[][] matrix){

        if( i<0 || j<0 || i == matrix.length || j ==matrix[0].length || matrix[i][j] == '0' ) return;

        matrix[i][j] = '0';
        drown(i+1, j, matrix);
        drown(i-1, j, matrix);
        drown(i, j+1, matrix);
        drown(i, j-1, matrix);
    }
}



//DeltaRow Approach
public class Solution {

    int[] d = {0, 1, 0, -1, 0};

    public int numIslands(char[][] grid) {
        int islands = 0;
        for (int i=0; i<grid.length; i++)
            for (int j=0; j<grid[i].length; j++)
                islands += sink(grid, i, j);
        return islands;
    }
    int sink(char[][] grid, int i, int j) {
        if (i < 0 || i == grid.length || j < 0 || j == grid[i].length || grid[i][j] == '0')
            return 0;
        grid[i][j] = '0';
        for (int k=0; k<4; k++)
            sink(grid, i+d[k], j+d[k+1]);
        return 1;
    }
}