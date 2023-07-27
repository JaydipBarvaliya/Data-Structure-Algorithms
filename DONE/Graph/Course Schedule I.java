class Solution {
    public boolean isPossible(int totalTasks,int totalPairs, int[][] prerequisites){

        boolean[] visited = new boolean[totalTasks];
        boolean[] pathVisited = new boolean[totalTasks];


        for(int i=0; i<totalTasks; i++){
            if(visited[i]==false){
                if(detectCycle()) return false;
            }
        }
        return true;
    }
    public boolean detectCycle(int totalTasks,int totalPairs, int[][] prerequisites){

    }
}
