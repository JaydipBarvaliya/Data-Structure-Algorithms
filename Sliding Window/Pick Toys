public class Main {
    public static void main(String[] args) {
        
         String str = "abaccab";
         int ans = 0;
         char[] arr = str.toCharArray();
         Map<Character, Integer> map = new HashMap();
         StringBuilder sb = new StringBuilder();
         int i = 0;
         int j = 0;
        
        while(j<str.length()){
        
            if(map.get(arr[j]) == null){
                map.put(arr[j], 1);
            }else{
                map.put(arr[j], map.get(arr[j])+1);
            }
            
            if(map.size()>2){
                if(j-i>ans){
                    ans = j-i;
                    sb.replace(0, sb.length(), str.substring(i,j));
                }
                while(map.size()!=2){
                    map.put(arr[i], map.get(arr[i])-1);
                    if(map.get(arr[i]) == 0){
                        map.remove(arr[i]);
                    }
                    i++;
                }
            }
            
            j++;
            
        }
        if(j-i>ans){
            ans = j-i;
            sb.replace(0, sb.length(), str.substring(i,j));
        }
     
        System.out.println(ans);
        System.out.println(sb);
        
    }
}
