// Time Complexity: O(N+E) - Total Nodes N and Directed edges E
// Space complexity: O(N)+O(N) -- Indegree & Queue

class Solution{
    
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) {
            
        int[] indegree = new int[V];

        for(int i=0; i<V; i++){
            for(int neigh : adj.get(i)){
                indegree[neigh]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        for(int i=0; i<V; i++){
            if(indegree[i] == 0){
                queue.add(i);
            }
        }

        int[] topo = new int[V];
        int counter = 0;

        while(!queue.isEmpty()){

                int curr = queue.poll();
                topo[counter++] = curr;

                for(int neigh: adj.get(curr)){
                    indegree[neigh]--;
                    if(indegree[neigh] == 0){
                        queue.add(neigh);
                    }
                }
        }
        
        if(counter==V) return topo;
        return new int[]{};
    }
}