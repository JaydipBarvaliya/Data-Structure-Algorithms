class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        
        int L = beginWord.length();
        
        
        Map<String, List<String>> map = new HashMap<>();
        
        wordList.forEach(word-> {
            
            for(int i=0; i<L; i++){
                
                String pattern = word.substring(0, i)  +"*" +  word.substring(i+1, L);
                
                List<String> candidates = map.getOrDefault(pattern, new ArrayList<String>());
                
                candidates.add(word);
                map.put(pattern, candidates);
            }
        });
        
        
        
        Queue<Pair<String, Integer>> queue = new LinkedList<>();
        Map<String, Boolean> visited = new HashMap<>();
        
        queue.add( new Pair(beginWord, 1));

        
        while(!queue.isEmpty()){
            
            Pair<String, Integer> node = queue.remove();
            
            String word  = node.getKey();
            int level = node.getValue();
            
            
            for(int i=0; i<L; i++){
                
                String pattern = word.substring(0, i)  + "*" +  word.substring(i+1, L);
                
                for(String candidate : map.getOrDefault(pattern, new ArrayList<>())){
                    
                    if(candidate.equals(endWord)){
                        return level+1;
                    }
                    
                    if(!visited.containsKey(candidate)){
                        visited.put(candidate, true);
                        queue.add( new Pair(candidate, level+1));
                    }
                }
            }
        }
        return 0;
    }
}
