import java.util.*;
import java.util.stream.Collectors;


class Program {

  public boolean cycleInGraph(int[][] edges) {

    int i = 0;
    Map<Integer, List<Integer>> map = new HashMap<>();

    while(i < edges.length){
      
      List<Integer> subList =  Arrays.stream(edges[i]).boxed().collect(Collectors.toList());
      map.put(i, subList);

      for(int ele : subList){
        
        if(map.containsKey(ele)){
          boolean cycleExist = dfs (map, map.get(ele), i) ;
          if(cycleExist) return true;
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
