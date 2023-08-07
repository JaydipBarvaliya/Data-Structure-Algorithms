// Time Complexity: O(N) + O(E), Where N = Nodes, E is for total degrees as we traverse all adjacent nodes. [ For undirected it is O(2E) ]
// Space Complexity: O(3N) ~ O(N), Space for queue data structure visited array and an adjacency list


class Solution {
    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        
        ArrayList<Integer> result = new ArrayList<>();
        boolean[] visited = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();
        
        queue.add(0);
        visited[0] = true;
        
        while(!queue.isEmpty()){
            
            int curr = queue.poll();
            result.add(curr);
            
            for(int neigh : adj.get(curr)){
                
                if(!visited[neigh]){
                    visited[neigh] = true;
                    queue.add(neigh);
                }
            }
        }
        return result;
    }
}