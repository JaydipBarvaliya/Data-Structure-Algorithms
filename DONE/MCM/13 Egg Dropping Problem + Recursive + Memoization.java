import java.util.Arrays;



class Solution {
    public int superEggDrop(int e, int f) {

        int[][] matrix = new int[e+1][f+1];
        for(int[] row : matrix)
            Arrays.fill(row, -1);

        return solve(e, f, matrix);
    }

    public int solve(int e, int f, int[][] matrix){

        if(e==1) return f;
        if(f==0) return 0;
        if(f==1) return 1;

        if(matrix[e][f] != -1) return matrix[e][f];

        int min = Integer.MAX_VALUE;


        //Remember this is Linear Solution where we are going from left to right, next solution will be the binary one, where
        // we break the middle part and then either left or right. So it will help us to store maximum answer in minimum attempt in matrix
        // If we can quickly fill the matrix then we do not need to go deeper as most of the answers are available in matrix and binary search will do the same
        
        for(int k=1; k<=f; k++){
                
                
                int eggBreak=0;
                if(matrix[e-1][k-1] != -1){
                    eggBreak = matrix[e-1][k-1];
                }else{
                    eggBreak = solve(e-1, k-1, matrix);
                }


                int eggDontBreak=0;
                if(matrix[e-1][k-1] != -1){
                    eggDontBreak = matrix[e][f-k];
                }else{
                    eggDontBreak = solve(e, f-k, matrix);
                }

                int maxAttemptsInWorstCase = 1 + Math.max(eggBreak, eggDontBreak);
                min = Math.min(min, maxAttemptsInWorstCase);
        }

        matrix[e][f] = min;
        return min;
    }
}


// Time Complexity : N^3 since its a variation of MCM but it can be further optimized 
// to N^2logN by using binary search instead of linear search for  k loop 