class Solution{
    static int[] findOrder(int V, int m, ArrayList<ArrayList<Integer>> prerequisites) {
        
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        
        for(int i=0; i<V; i++){
            adj.add(new ArrayList<Integer>());
        }
        
        for(int i=0; i<m; i++){
            adj.get(prerequisites.get(i).get(1)).add(prerequisites.get(i).get(0));
        }
        
        int[] indegree = new int[V];

        for(int i=0; i<V; i++){
            for (int ele : adj.get(i)) {
                indegree[ele]++;
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i=0; i<V; i++){
            if(indegree[i]==0){
                queue.add(i);
            }
        }

        
        int[] topo = new int[V];
        int counter = 0;

        while(!queue.isEmpty()){

            int node = queue.poll();
            topo[counter++] = node;

            for(int neigh: adj.get(node)){
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