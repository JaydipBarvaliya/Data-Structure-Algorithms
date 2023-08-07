//Step 1 :  First fill out Indegree 
//Step 2 :  Add elements into the Queue which indegree is 0
//Step 3 :  Iterate Queue until it become empty
//Step 4 :  (A) pop elem from queue and run through it's neighs
//          (B) If Indegree of Curr is 0 then add it to the ans
//          (C) reduce indegree of neighs
//          (D) If Indegree of neigh is 0 then add it it in Queue

// CRUX 1 : Indegree of queue elem is 0 then that is ans
// CRUX 2 : Indegree of neigh elem is 0 then add it in the queue


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