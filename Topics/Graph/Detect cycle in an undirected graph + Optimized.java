class Solution {
    public boolean isCycle(int V, ArrayList<ArrayList<Integer>> list) {
        
      boolean[] visited = new boolean[V];
      
      Arrays.fill(visited, false);
      
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
            
            if(visited[neigh] == false){
                if(detectCycle(neigh, node, list, visited) == true)
                    return true;
            }else if(neigh != parent)
                 return true;
        }

        return false;
   }
    
}
