// Time Complexity: O(N + E) + O(N), Where N = Nodes, 2E is for total degrees as we traverse all adjacent nodes. In the case of connected components of a graph, it will take another O(N) time.
// Space Complexity: O(N) + O(N) ~ O(N), Space for recursive stack space and visited array.

class Solution {
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> list) {
        
      boolean[] visited = new boolean[V];
      
      for(int i=0; i<V; i++){
          if(visited[i] == false){
              if(detectCycle(i, -1, list, visited) == true)
                  return true;
          }
      }
      return false;
  }
    
    static boolean detectCycle(int node, int parent, ArrayList<ArrayList<Integer>> list, boolean[] visited){

         visited[node] = true;
        
        for(Integer neigh : list.get(node)){
            
            //not visited
            if(visited[neigh] == false){
                
                if(detectCycle(neigh, node, list, visited) == true){
                     return true;
                }
            }else if(neigh != parent){
                return true;
            }
            //visited because above condition false
            //if any node is visited and it is not your parent and some one visit this node earlier which means there is a cycle.
                
        }
        return false;
   }
}