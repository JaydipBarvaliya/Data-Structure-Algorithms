class Solution {
    private Map<Integer, List<Integer>> graph = new HashMap<>();

    public List<List<Integer>> getCatagories(List<int[]> productlist){

        for(int[] prods : productlist){
            graph.putIfAbsent(prods[0], new ArrayList<>());
            graph.putIfAbsent(prods[1], new ArrayList<>());

            graph.get(prods[0]).add(prods[1]);
            graph.get(prods[1]).add(prods[0]);
        }

        Set<Integer> visited = new HashSet<>();
        List<List<Integer>> result = new ArrayList();

        for(int key: graph.keySet()){
            if(!visited.contains(key)){
                List<Integer> group = new ArrayList();
                dfs(key, group, visited);
                result.add(group);
            }
        }

        return result;

    }

    private List<Integer> dfs(int node, List<Integer> group, Set<Integer> visited){
        if(!graph.containsKey(node) || visited.contains(node)) return group;

        visited.add(node);
        group.add(node);
        List<Integer> children = graph.get(node);
        for(Integer child: children){
            if(!visited.contains(child)){
                dfs(child, group, visited);
            }
        }
        return group;
    }
}
