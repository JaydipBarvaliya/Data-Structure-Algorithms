class Solution {
    public boolean isPossible(int V,int P, int[][] prerequisites){
        
        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();
        
        for(int i=0; i<V; i++){
            adj.add(new ArrayList<Integer>());
        }
        
        for(int i=0; i<P; i++){
            adj.get(prerequisites[i][0]).add(prerequisites[i][1]);
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

        
        List<Integer> topo = new ArrayList<Integer>();
        int i = 0;

        while(!queue.isEmpty()){

            int node = queue.poll();
            topo.add(node);

            for(int neigh: adj.get(node)){
                indegree[neigh]--;
                if(indegree[neigh] == 0){
                    queue.add(neigh);
                }
            }
        }
        
        if(topo.size()==V) return true;
        return false;
        
    }
}