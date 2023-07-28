import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution{
    static int[] topoSort(int V, ArrayList<ArrayList<Integer>> adj) {


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

        while(!queue.isEmpty()){

            int node = queue.poll();
            topo[i++] = node;

            for(int neigh: adj.get(node)){
                indegree[neigh]--;
                if(indegree[neigh] == 0){
                    queue.add(neigh);
                }
            }
        }
        return topo;
    }
}