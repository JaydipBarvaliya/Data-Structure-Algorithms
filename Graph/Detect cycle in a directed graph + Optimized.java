class Solution {
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> list) {
        
      boolean[] visited = new boolean[V];
      boolean[] dfsvisited = new boolean[V];
      //it depecits that if a node visit in current cycle or not 
      //if it is visited in current cycle then there is cycle otherwise not
      //
      
      
      Arrays.fill(visited, false);
      Arrays.fill(dfsvisited, false);
      
      for(int i =0;i<V;i++){
          if(visited[i] == false){
              if(detectCycle(list, i, visited, dfsvisited) == true)
                  return true;
          }
      }
      
      return false;
  }
    
    static boolean detectCycle(ArrayList<ArrayList<Integer>> list, int node, boolean[] visited, boolean[] dfsvisited){
        visited[node] = true;
        dfsvisited[node] = true;
        
        for(Integer neigh : list.get(node)){
            if(visited[neigh] == false){
                if(detectCycle(list, neigh, visited, dfsvisited) == true)
                    return true;
            }
            else if(dfsvisited[neigh] == true)
                 return true;
        }
        dfsvisited[node] = false;
        return false;
   }
    
}
