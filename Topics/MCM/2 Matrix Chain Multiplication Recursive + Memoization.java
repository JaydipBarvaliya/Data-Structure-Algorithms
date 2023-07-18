class Solution{
    static int matrixMultiplication(int len, int arr[]){
        int[][] matrix = new int[len][len];
        for (int[] row : matrix)
            Arrays.fill(row, -1);
        return solve(matrix, arr, 1, len-1);
    }

    static int solve(int[][] matrix, int arr[], int i, int j){
        
        if(i>=j) return 0;
        
        if(matrix[i][j] != -1) return matrix[i][j];
        
        int min = Integer.MAX_VALUE;
        for(int k=i; k<=j-1; k++){
            int tempAns = solve(matrix, arr, i, k) + solve(matrix, arr, k+1, j) + (arr[i-1] * arr[j] * arr[k]);
            min  = Math.min(tempAns, min);
        }
        
        matrix[i][j] = min;
        return min;
    }
}


//{ Driver Code Starts
// Initial Template for Java
import java.util.*;
        import java.lang.*;
        import java.io.*;
class GFG {
    public static void main(String[] args) throws IOException {
        BufferedReader br =
                new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        while (T-- > 0) {
            String[] s = br.readLine().trim().split(" ");
            int V = Integer.parseInt(s[0]);
            int E = Integer.parseInt(s[1]);
            ArrayList<ArrayList<Integer>> adj =
                    new ArrayList<ArrayList<Integer>>();
            for (int i = 0; i < V; i++) adj.add(new ArrayList<Integer>());
            for (int i = 0; i < E; i++) {
                String[] S = br.readLine().trim().split(" ");
                int u = Integer.parseInt(S[0]);
                int v = Integer.parseInt(S[1]);
                adj.get(u).add(v);
                adj.get(v).add(u);
            }
            Solution obj = new Solution();
            ArrayList<Integer> ans = obj.dfsOfGraph(V, adj);
            for (int i = 0; i < ans.size(); i++)
                System.out.print(ans.get(i) + " ");
            System.out.println();
        }
    }
}