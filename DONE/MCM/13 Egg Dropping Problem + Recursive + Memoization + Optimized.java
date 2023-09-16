
class Solution {
    
    static int eggDrop(int e, int f){
        
        int[][] matrix = new int[e+1][f+1];
        for (int[] row : matrix)
            Arrays.fill(row, -1);   
        return solve(matrix, e, f);
        
    }
    static int solve(int[][] matrix, int e, int f){
        
        if(e==0 || f==0 || e==1) return f;
        
        if(matrix[e][f] != -1) return matrix[e][f];
        
        int min = Integer.MAX_VALUE;
        
        for(int k=1; k<=f; k++){
            
            int eggBreak = 0;
            int eggNotbreak = 0;
            int tempAns = 0;
            
            if(matrix[e-1][k-1] != -1) {
                eggBreak = matrix[e-1][k-1];
            }else{
                eggBreak = solve(matrix, e-1, k-1);    
            }
            
            
            if(matrix[e][f-k] != -1) {
                eggNotbreak = matrix[e][f-k];
            }else{
                eggNotbreak = solve(matrix, e, f-k);    
            }
            
            
            
            tempAns = 1 + Math.max(eggBreak, eggNotbreak);
            min = Math.min(min, tempAns);
        }
        matrix[e][f] = min;
        return min;
	}
}
