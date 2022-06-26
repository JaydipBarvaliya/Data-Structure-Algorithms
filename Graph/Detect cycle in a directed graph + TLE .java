class Solution {
    public boolean isCyclic(int V, ArrayList<ArrayList<Integer>> list) {
        
            int i = 0;
            Map<Integer, List<Integer>> map = new HashMap<>();
            
            for(ArrayList<Integer> subList :list){
              
              map.put(i, subList);
        
              for(int ele : subList){
                  
                if(map.containsKey(ele)){

                  boolean cycleExist = dfs (map, map.get(ele), i) ;
                  if(cycleExist){
                    return true;  
                  } 
                }
              }
              i++;
            }
            return false;
    }
    
      public boolean dfs(Map<Integer, List<Integer>> map, List<Integer> subList, int target) {
        
        if(subList.contains(target)) return true;
  
        for(int ele : subList){
                if(map.containsKey(ele)){
                   boolean ans = dfs(map, map.get(ele), target);
                   if(ans==true) return true;
                }
            }
        return false;
      }
      
}
