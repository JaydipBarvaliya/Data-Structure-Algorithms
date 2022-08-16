// Brute
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // put all words into a hashset
        Set<String> set = new HashSet<>(wordDict);
        return wb(s, set);
    }
    private boolean wb(String s, Set<String> set) {
        int len = s.length();
        if (len == 0) {
            return true;
        }
        for (int i = 1; i <= len; ++i) {
            if (set.contains(s.substring(0, i)) && wb(s.substring(i), set)) {
                return true;
            }
        }
        return false;
    }
}





// Recursion + Memo

public class Solution {
    public boolean wordBreak(String str, List<String> wordDict) {
        
        HashMap<String, Boolean> map= new HashMap<>();
        
        return canConstruct(str, new HashSet<>(wordDict), map);
    }

    
private boolean canConstruct(String str, Set<String> wordDict, HashMap<String,Boolean> map) {
        
        if(str.isEmpty()) return true;
        if(map.containsKey(str)) return map.get(str);
			
        
        for(String word : wordDict){
            
            if(str.startsWith(word)){
                
             boolean canConstruct =  canConstruct(str.substring(word.length()), wordDict, map);
                
                if(canConstruct){
                    map.put(str, true);
                    return true;
                }
            }
        }
        
        map.put(str, false);
        return false;
    }
}









// DP

class Solution {
    public boolean wordBreak(String str, List<String> wordDict) {
        
        
        int len = str.length();
        boolean dp[] = new boolean[len+1];
        dp[len] = true; 
        
        for(int i=len-1; i>=0; i--){
            
            for(String word : wordDict){
                
                if( i + word.length() <= len && str.substring(i, i+ word.length()).equals(word)){
                    
                    dp[i] = dp[i + word.length()];
                    
                    if(dp[i] == true) break;
                }
            }
        }
        
        return dp[0];
    }
}


