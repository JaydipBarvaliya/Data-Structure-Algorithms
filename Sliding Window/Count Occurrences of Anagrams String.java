class Solution {

    int search(String pattern, String str) {
        
        int i=0;
        int j=0;
        int ans=0;
        int n = str.length();
        int k = pattern.length();
        Map<Character, Integer> map = new HashMap<Character, Integer>(); 
        
        //popluate all "pattern" into map
        
        for(int index=0; index<k; index++){
            
            Character curr = pattern.charAt(index);
            
            if(!map.containsKey(curr)){
                map.put(curr, 1);
            }else{
                map.put(curr, map.get(curr)+1);
            }
        }
        
        
        int requiredChars = map.size();
        
        while(j<n){
            
            Character lastChar = str.charAt(j);
            
            if(map.containsKey(lastChar)){
                map.put(lastChar, map.get(lastChar)-1);
            }
            
            if(map.get(lastChar) !=null && map.get(lastChar) == 0){
                requiredChars--;   
            }
            
            
            if(j-i+1 < k){
                j++;
            }else{
                
                if(requiredChars==0) ans++;
                
                Character firstChar = str.charAt(i);
               
               if( map.containsKey(firstChar) && map.get(firstChar) == 0){
                    requiredChars++;
                }
                
                //above and below if statement can't be merger because of the 
                //scenario where character is present into the map but the 
                //occurance is more than 1 ...then we can't increase the value of 
                //requiredChars and even we have to just increase it's frequency by 1 only.

                //understand the dif between freuency of characters in map & requiredChars variable
                
                if(map.containsKey(firstChar)){
                    map.put(firstChar, map.get(firstChar) + 1);
                }
                
                i++;
                j++;
            }
        }
        
        return ans;
    }
}
