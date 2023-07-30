class Solution {

    int search(String pattern, String str) {

        int answer = 0 ;
        int k = pattern.length();
        int n = str.length();
        Map<Character, Integer> map = new HashMap<>();


        

        for(int index=0; index<k; index++){
            Character ch = pattern.charAt(index);
            if(map.containsKey(ch)){
                map.put(ch, map.get(ch) +1);
            }else{
                map.put(ch, 1);
            }
        }
        
        int requiredChars = map.size();
        
        int i = 0;
        int j = 0;


        while(j<n){

            char firstChar = str.charAt(j);

            if(map.containsKey(firstChar)){
                map.put(firstChar, map.get(firstChar) - 1);
                if(map.get(firstChar) == 0) requiredChars--;
            }

            if(j-i+1 < k){
                j++;

            }else{
                
                if(requiredChars == 0) answer++;
             
             
                char lastChar = str.charAt(i);

                if(map.containsKey(lastChar) && map.get(lastChar) == 0){
                    requiredChars++;
                }

                if(map.containsKey(lastChar)){
                    map.put(lastChar, map.get(lastChar) + 1);
                }
                i++;
        j++;
            }
        }
        

        return answer;
    }
}