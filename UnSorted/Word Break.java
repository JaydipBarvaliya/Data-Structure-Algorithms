// Brute
class Solution {
    public boolean wordBreak(String str, List<String> dictonary) {

        // put all words into a hashset In order to get rid of duplicates

        Set<String> wordDict = new HashSet<>(dictonary);
        return recurse(str, wordDict);
    }
    private boolean recurse(String str, Set<String> wordDict) {
        if (str.length() == 0) {
            return true;
        }
        for (int i = 1; i <= str.length(); ++i) {
            if (wordDict.contains(str.substring(0, i)) && recurse(str.substring(i), wordDict)) {
                return true;
            }
        }
        return false;
    }
}





// Recursion + Memo
// In this approach we do reverse engineering..rather then checking every letter & substring of string into word dictionary, we can check if string is 
// starting with the word of word dictionary --> which means start iterating word dictionary instead of string 

public class Solution {
    public boolean wordBreak(String str, List<String> wordDict) {
        
        HashMap<String, Boolean> map = new HashMap<>();
        
        return canConstruct(str, new HashSet<>(wordDict), map);
    }

    
private boolean canConstruct(String str, Set<String> wordDict, HashMap<String,Boolean> map) {
        
        if(str.isEmpty()) return true;

        //this is specially for the case where same str already exist in the map
        //str = leetcodeleet
        //dic [leet, code, leet]
        // map [leet: true,
        //      code: true
        // ]

        //canConstruct("leet",  wordDict, map) --> now in this case "leet" is already present in the map so we do not need to check further....even we do
        //not need to break the "leet" and checking it further....everything will be the same if we proceed

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









// DP -- Bottom up -- reverse for loop  

class Solution {
    public boolean wordBreak(String str, List<String> wordDict) {
        
        
        int len = str.length();
        boolean dp[] = new boolean[len+1];

        //len means we are considering entire string 
        dp[len] = true; 
        
        for(int i=len-1; i>=0; i--){
            
            for(String word : wordDict){
                
                if( i + word.length() <= len && str.substring(i, i+word.length()).equals(word)){
                    
                    dp[i] = dp[i + word.length()];
                    
                    //at every index we are checking every word...but if any word is fitting then do not need to check any further word
                    if(dp[i] == true) break;
                }
            }
        }
        
        return dp[0];
    }
}


