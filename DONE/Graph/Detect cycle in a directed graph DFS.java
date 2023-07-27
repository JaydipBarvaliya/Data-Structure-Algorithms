class Solution {
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {
        
      boolean[] visited = new boolean[V];
      boolean[] pathVisited = new boolean[V];
      
      for(int i=0; i<V; i++){
          if(visited[i] == false){
              if(detectCycle(i, adj, visited, pathVisited) == true)
                  return true;
          }
      }
      return false;
        
    }
    
    public static boolean detectCycle(int node, ArrayList<ArrayList<Integer>> adj, boolean[] visited, boolean[] pathVisited){

         visited[node] = true;
         pathVisited[node] = true;
        
        for(Integer neigh : adj.get(node)){
            //not visited
            if(visited[neigh] == false){
                if(detectCycle(neigh, adj, visited, pathVisited) == true) return true;
            }else if(pathVisited[neigh] == true){
                return true;
            }
        }
        
        //once we visit the node and that is the end of the chain then we DFS will move back. At that time we need unvisit all the path and mark it as a FALSE which states that this has never been visited
        //The reason is that it will help us in the future, where one has been connected by two incoming directing...instead of one incoming and one outgoing...
        pathVisited[node] = false;
        return false;
   }
}