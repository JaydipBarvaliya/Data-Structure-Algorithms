public class Main {
    public static void main(String[] args) {
        
        String str = "GEEKSFORGEEKS";
        char[] arr = str.toCharArray();
        int longest=0;
        int i=0;
        int j=0;
        
        Map<Character, Integer> map = new HashMap<Character, Integer>();
        
        while(j<arr.length) {
            
                if(map.get(arr[j]) == null){
                    map.put(arr[j], 1);
                }else{
                    if(j-i>longest){
                        longest = j-i;
                    }
                    
                    while(arr[i]!=arr[j]){
                        map.remove(arr[i]);
                        i++;
                    }
                    i++;
                }
            if(j-i+1>longest){
                longest = j-i+1;
            }
            j++;
        }
        System.out.println(longest);
    }
}
