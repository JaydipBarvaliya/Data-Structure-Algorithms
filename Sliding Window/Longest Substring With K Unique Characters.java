class Solution {
    public int longestkSubstr(String str, int k) {
        
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        int n= str.length();
        int i=0;
        int j=0;
        int max=-1;
        
        while(j<n){
            
            //If value is exist then fetch + 1 and update into map
            //if value is not exist then take default value as 0 + 1 and add into map

            map.put(str.charAt(j), map.getOrDefault(str.charAt(j), 0) + 1);
            
            if(map.size() < k){
                j++;
                
                
            }else if(map.size() == k){
                
                max = Math.max(max, j-i+1);
                j++;
                
            }else if(map.size() > k){
                
                 while (map.size() > k) {
                     
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
