class Solution {
    public ArrayList<Integer> bfsOfGraph(int V, ArrayList<ArrayList<Integer>> adj) {
        
        ArrayList<Integer> result = new ArrayList<>();
        boolean visited[] = new boolean[V];
        Queue<Integer> queue = new LinkedList<>();

        queue.add(0);
        visited[0] = true;
        
        while(!queue.isEmpty()){
            
            Integer node = queue.poll();
            
            result.add(node);
            
            for(Integer neigh : adj.get(node)){
                if(visited[neigh] == false){
                    visited[neigh] = true;
                    queue.add(neigh);
                }
            }
        }
        return result;
    }
}