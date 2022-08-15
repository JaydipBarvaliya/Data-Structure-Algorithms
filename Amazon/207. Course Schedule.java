class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        
        
        boolean[] visited = new boolean[numCourses];
        boolean[] dfsvisited = new boolean[numCourses];
        //it depecits that if a node visit in current cycle or not 
        //if it is visited in current cycle then there is cycle otherwise not
        
        
        
        ArrayList<Integer>[] adj = new ArrayList[numCourses];
        
        
        for(int i=0; i<numCourses; i++){
            adj[i] = new ArrayList<>();
        }
        
        
        for(int[] pre : prerequisites){
            adj[pre[0]].add(pre[1]);
        }
                

        Arrays.fill(visited, false);
        Arrays.fill(dfsvisited, false);

        for (int i = 0; i < numCourses; i++) {

            if (visited[i] == false) {
                if (detectCycle(adj, i, visited, dfsvisited) == true)
                    return false;
            }
        }

        return true;
    }

    static boolean detectCycle(ArrayList<Integer>[] adj, int node, boolean[] visited, boolean[] dfsvisited) {
        visited[node] = true;
        dfsvisited[node] = true;

        for (Integer adjNode: adj[node]) {
            if (visited[adjNode] == false) {
                if (detectCycle(adj, adjNode, visited, dfsvisited) == true)
                    return true;
            } else if (dfsvisited[adjNode] == true)
                return true;
        }
        dfsvisited[node] = false;
        return false;
    }
}
