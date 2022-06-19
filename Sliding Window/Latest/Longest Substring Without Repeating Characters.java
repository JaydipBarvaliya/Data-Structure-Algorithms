class Solution {
    public int lengthOfLongestSubstring(String str) {
        
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int n= str.length();
        int i=0;
        int j=0;
        int max=0;
        
        while(j<n){
            
            //If value is exist then fetch + 1 and update into map
            //if value is not exist then take default value as 0 + 1 and add into map

            map.put(str.charAt(j), map.getOrDefault(str.charAt(j), 0) + 1);
            
            if(map.size() > j-i+1){
                j++;
                
                //In reality this condition will never
                //gonna be hit because of the reason that...
                //map is defining the total number of unique 
                //character in window...and j-i+1 is showing 
                //all the character....so here we can definately say that
                //unique character will never be exceed the actual character
                //which are in windows
                
                
            }
            else if(map.size() == j-i+1){
                
                max = Math.max(max, j-i+1);
                j++;
                
            }else if(map.size() < j-i+1){ //maps has one duplicate character
                
                 while (map.size() < j-i+1) {
                     
                    map.put(str.charAt(i), map.get(str.charAt(i)) - 1);
                     
                    if(map.get(str.charAt(i)) == 0) map.remove(str.charAt(i));
                     
                    i++;
                }
                j++;
            }
        }
        return max;
    }
}
