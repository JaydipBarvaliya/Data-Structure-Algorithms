class Solution {
    public boolean canFinish(int V, int[][] arr) {

        ArrayList<ArrayList<Integer>> adj = new ArrayList<>();

        for(int i=0; i<V; i++){
            adj.add(new ArrayList<>());
        }

        for(int i=0; i<arr.length; i++){
            adj.get(arr[i][1]).add(arr[i][0]);
        }

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

        if(counter == V ) return true; // which means no cycle is present...no interdependency of the task in the schedule
        
        return false;
    }
}