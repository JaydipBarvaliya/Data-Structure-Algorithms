class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        
        boolean[] visited = new boolean[numCourses];
        boolean[] dfsvisited = new boolean[numCourses];
        //it depecits that if a node visit in current cycle or not 
        //if it is visited in current cycle then there is cycle otherwise not
        
        
        Queue<Integer> queue = new LinkedList<>();
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
                if (detectCycle(adj, i, visited, dfsvisited, queue) == true){
                    return new int[0];
                }
            }
        }
        
        int i = 0;
        int[] result = new int[numCourses];
        while (!queue.isEmpty()) {
            result[i++] = queue.poll();
        }
        return result;
    }
    static boolean detectCycle(ArrayList<Integer>[] adj, int node, boolean[] visited, boolean[] dfsvisited, Queue<Integer> queue) {
        visited[node] = true;
        dfsvisited[node] = true;

        for (Integer adjNode: adj[node]) {
            if (visited[adjNode] == false) {
                if (detectCycle(adj, adjNode, visited, dfsvisited, queue) == true)
                    return true;
            } else if (dfsvisited[adjNode] == true)
                return true;
        }
        dfsvisited[node] = false;
        queue.add(node);
        return false;
    }
    
}
