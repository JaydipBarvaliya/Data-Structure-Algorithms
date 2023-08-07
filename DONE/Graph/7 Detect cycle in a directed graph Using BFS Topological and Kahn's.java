class Solution {
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> adj) {

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
        int i = 0;
        int cnt = 0;
    
        while(!queue.isEmpty()){

            int node = queue.poll();
            topo[i++] = node;
            cnt++;

            for(int neigh: adj.get(node)){
                indegree[neigh]--;
                if(indegree[neigh] == 0){
                    queue.add(neigh);
                }
            }
        }
        //Remeber we need a counter(cnt) here to identify that how many nodes of graph have been process.
        //if we use topo.size() it will always gives us 5.
        if(cnt == V) return false;
        return true;
        
    }
}