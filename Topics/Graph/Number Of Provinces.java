import java.io.*;
import java.util.*;

class Solution {

    static void dfs(int curr, ArrayList<ArrayList<Integer>> adj, boolean[] visited){

        visited[curr] = true;

        for(int neigh : adj.get(curr)){

            if(visited[neigh] == false){
                dfs(neigh, adj, visited);
            }
        }
    }

    static int numProvinces(ArrayList<ArrayList<Integer>> adj, int V) {

        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();

        for(int i=0; i<V; i++){
            list.add(new ArrayList<Integer>());
        }

        for(int i=0; i<V; i++){
            for(int j=0; j<V; j++){

                if(adj.get(i).get(j) == 1 && i!=j){
                    list.get(i).add(j);
                    list.get(j).add(i);
                }
            }
        }
        boolean[] visited = new boolean[V];
        int count = 0;

        for(int i=0; i<V; i++){

            if(visited[i] == false){
                dfs(i, list, visited);
                count++;
            }
        }

        return count;
    }


};