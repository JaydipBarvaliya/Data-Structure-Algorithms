class Solution {

    private void dfs(int curr, boolean[] vis, ArrayList<Integer> ans, ArrayList<ArrayList<Integer>> adj) {


        // marking vertex as visited and adding it to output list.
        vis[curr] = true;
        ans.add(curr);

        // iterating over connected components of the vertex and if any
        // of them is not visited then calling the function recursively.
        for( int neigh : adj.get(curr) ){
            if (!vis[neigh]){
                dfs(neigh, vis, ans, adj);
            }
        }
    }

    // Function to return a list containing the DFS traversal of the graph.
    public ArrayList<Integer> dfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        // using a boolean list to mark all the vertices as not visited.
        boolean[] vis = new boolean[V];

        ArrayList<Integer> ans = new ArrayList<Integer>();

        dfs(0, vis, ans, adj);

        // returning the output list which is contains the elements in DFS order.
        return ans;
    }
}