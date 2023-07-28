import java.util.ArrayList;

class Solution {
    
    public boolean isPossible(int V, int P, int[][] prerequisites){
        
        boolean[] visited = new boolean[V];
        boolean[] pathVisited = new boolean[V];
        //if it is visited in current cycle then there is cycle otherwise not
        
        ArrayList<Integer>[] adj = new ArrayList[V];
        
        for(int i=0; i<V; i++){
            adj[i] = new ArrayList<>();
        }
        
        
        for(int[] task : prerequisites){
            adj[task[0]].add(task[1]);
        }

                
        for (int i = 0; i < V; i++) {
            if (visited[i] == false) {
                if (detectCycle(adj, i, visited, pathVisited) == true)
                    return false;
            }
        }

        return true;
    }
    
    static boolean detectCycle(ArrayList<Integer>[] adj, int node, boolean[] visited, boolean[] pathVisited) {
        visited[node] = true;
        pathVisited[node] = true;

        for (Integer adjNode: adj[node]) {
            if (visited[adjNode] == false) {
                if (detectCycle(adj, adjNode, visited, pathVisited)) return true;
            } else if (pathVisited[adjNode] == true){
                return true;
            }
                
                
        }
        pathVisited[node] = false;
        return false;
    }
    
}