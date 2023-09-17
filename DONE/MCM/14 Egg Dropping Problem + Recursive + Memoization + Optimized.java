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
        int low = 0;
        int high = f;

        while(low <= high){

            int mid = (low + high)/2;
            int lowerFloors  = 0;
            int higherFloors = 0;
            if(matrix[e-1][mid-1] != -1){
                lowerFloors = matrix[e-1][mid-1];
            }else{
                lowerFloors =  solve(e-1, mid-1, matrix);
                matrix[e-1][mid-1] = lowerFloors;
            }

            if(matrix[e][f-mid] != -1){
                higherFloors = matrix[e][f-mid];
            }else{
                higherFloors =  solve(e, f-mid, matrix);
                matrix[e][f-mid]   = higherFloors;
            }

            int temp = 1 + Math.max(lowerFloors, higherFloors);


            //We are dividing the building with mid to upper floors and mid to lowerfloors. and then our solve function find out the
            //attempts both sides (upper & lower). Now out of both the answers we need to stick with the worst case which means the whichever
            //has higher value that part has maximum numbers of attempts done and based on that we are considering either upper floors or lower
            // floor for next calulcation and based on that we are moving our low and high.
            if(lowerFloors < higherFloors){
                low = mid+1;
            }else{
                high = mid-1;
            }
            min = Math.min(min, temp);
        }

        matrix[e][f] = min;
        return min;
    }
}


// Time Complexity : N^3 since its a variation of MCM but it can be further optimized 
// to N^2logN by using binary search instead of linear search for  k loop and that what we did here