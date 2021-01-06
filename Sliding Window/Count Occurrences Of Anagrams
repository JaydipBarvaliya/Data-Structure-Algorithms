public class Main {
    public static void main(String[] args) {

        String str = "aabaabaa";
        String pattern = "aaba";
        System.out.println(approach1(str, pattern));
        System.out.println(approach2(str, pattern));
    }
    
    static int approach1(String str, String pattern) {
        
        char[] arr = str.toCharArray();
        int i = 0;
        int j = 0;
        Map<Character, Integer> map = new HashMap();
        
        for(char ch : pattern.toCharArray()) {
            if(map.get(ch) == null){
                map.put(ch, 1);
            }else{
            map.put(ch, map.get(ch)+1);
            }
        }
        
        int count = map.size();
        int ans = 0;
        int windowSize = pattern.length();
        
        
        while(j<str.length()){
            
            if(map.get(arr[j]) != null){
                map.put(arr[j],  map.get(arr[j])-1);
            }
            
            if(map.get(arr[j]) != null && map.get(arr[j]) == 0){
                count--;
            }
            
            if(count == 0){
                ans++;
            }
            
            if(j-i+1 == windowSize){
                
                if(map.get(arr[i]) != null){
                    map.put(arr[i],  map.get(arr[i])+1);
                }
                if(map.get(arr[i]) != null &&  map.get(arr[i]) == 1){
                    count++;
                }
            i++;    
            }
            
           j++; 
        }
        
        return ans;
    }
    
    static int approach2(String str, String pattern) {
   
        int i, j, count;
        i=j=count=0;

        char[] arr = str.toCharArray();
        int[] sourceMap = new int[128];
        int[] patternMap = new int[128];

        for (int c: pattern.toCharArray()) {
            patternMap[c]++;
        }
        
        while (j<str.length()) {
            sourceMap[arr[j]]++;

            if (j-i+1 == pattern.length()) {
                if (Arrays.equals(patternMap, sourceMap)) {
                    count++;
                }
                sourceMap[arr[i]]--;
                i++;
            }
            j++;
        }
        return count;
    }
}
