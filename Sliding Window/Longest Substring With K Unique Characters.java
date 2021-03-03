public class Main {
    public static void main(String[] args) {
        
        String str = "aabbcc";
        char[] arr = str.toCharArray();
        int longest=0;
        int i=0;
        int j=0;
        int k=3;
        
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        StringBuilder sb = new StringBuilder();
        
        while(j<arr.length) {
                if(map.get(arr[j]) == null){
                    map.put(arr[j], 1);
                }else{
                    map.put(arr[j], map.get(arr[j])+1);
                }
            
                if(map.size() == k){
                    if(j-i+1 > longest){
                        longest = j-i+1;
                        sb.replace(0, sb.length(), str.substring(i,j+1));
                    }
                }

                if(map.size() > k){
                     while(map.size() < k){
                         map.put(arr[i], map.get(arr[i])-1);
                         i++;
                         
                         if(map.get(arr[i]) == 0){
                             map.remove(map.get(arr[i]));
                         }
                     }
                }
            j++;
        }
        System.out.println(sb);
    }
}
