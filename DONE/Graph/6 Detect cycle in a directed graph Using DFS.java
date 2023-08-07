// Time Complexity: O(N + 2E) + O(N), Where N = Nodes, 2E is for total degrees as we traverse all adjacent nodes. In the case of connected components of a graph, it will take another O(N) time.
// Space Complexity: O(N) + O(N) ~ O(N), Space for recursive stack space and visited array.


class Solution {
    
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        
        boolean[] visited = new boolean[V];
        boolean[] pathVisited = new boolean[V];
        
        for(int i=0; i<V; i++){
            
            if(visited[i] == false){
                if(dfs(i, adj, visited, pathVisited)) return true;
            }
            
        }
    return false;
    }
    
    public boolean dfs(int curr, ArrayList<ArrayList<Integer>> adj, boolean[] visited, boolean[] pathVisited){
        
        visited[curr] = true;
        pathVisited[curr] = true;
        
        for(int neigh : adj.get(curr)){
            
            //Not Visited
            if(visited[neigh] == false){
                if(dfs(neigh, adj, visited, pathVisited)) return true;
            }
            //Visited
            else if (pathVisited[neigh] == true){
                return true;
            }
        }
        //once we visit the node and that is the end of the chain then we DFS will move back. At that time we need unvisit all the path and mark it as a FALSE which states that this has never been visited
        //The reason is that it will help us in the future, where one has been connected by two incoming directing...instead of one incoming and one outgoing...
        pathVisited[curr] = false;
        return false;
    }
}