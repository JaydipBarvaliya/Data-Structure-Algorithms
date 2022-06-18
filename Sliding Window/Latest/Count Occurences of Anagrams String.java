class Solution {

    int search(String pat, String str) {
        
        int i=0;
        int j=0;
        int ans=0;
        int n = str.length();
        int k = pat.length();
        Map<Character, Integer> map = new HashMap<Character, Integer>(); 
        
        //popluate all "patt" into map
        
        for(int index=0; index<k; index++){
            
            Character curr = pat.charAt(index);
            
            if(!map.containsKey(curr)){
                map.put(curr, 1);
            }else{
                map.put(curr, map.get(curr)+1);
            }
        }
        
        
        int count = map.size();
        
        while(j<n){
            
            Character lastChar = str.charAt(j);
            
            if(map.containsKey(lastChar)){
                map.put(lastChar, map.get(lastChar)-1);
            }
            
            if(map.get(lastChar) !=null && map.get(lastChar) == 0){
                count--;   
            }
            
            
            if(j-i+1 < k){
                j++;
            }else{
                
                if(count==0) ans++;
                
                Character firstChar = str.charAt(i);
               
               if( map.containsKey(firstChar) && map.get(firstChar) == 0){
                    count++;
                }
                
                //above and below if statement can't be merger because of the 
                //scenario where character is present into the map but the 
                //occurance is more than 1 ...then we can't increase the value of 
                //count and even we have to just increase it's frequency by 1 only.
                
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
