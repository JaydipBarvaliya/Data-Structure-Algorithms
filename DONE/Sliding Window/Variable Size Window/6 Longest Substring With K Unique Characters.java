class Solution {
    public int longestkSubstr(String str, int k) {
        
    int i = 0;
    int j = 0;
    int n = str.length();
    int longest =-1;

    Map<Character, Integer> map = new HashMap<>();
    
    while(j<n){
        
         // We are pushing data into the map
         map.put(str.charAt(j), map.getOrDefault(str.charAt(j), 0) + 1);
        

        if(map.size() < k){
            j++;
            
        }else if(map.size() == k){
            longest = Math.max(longest, j-i+1);
            j++;

        }else if(map.size() > k){

            //If map has more unique element then remove it from left side in string and
            //parallely also decrease the frequency in the map and when any frequency becomes the 
            // 0 then remove it from the map itself.
            while(i<=j && map.size() > k){
                char currChar = str.charAt(i);
                map.put(currChar, map.get(currChar) - 1);
                if(map.get(currChar) == 0) map.remove(currChar);
                i++;
            }
          j++;
        }
    }
    return longest;
    }
}