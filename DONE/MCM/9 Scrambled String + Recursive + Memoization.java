class Solution{
    static boolean isScramble(String s1, String s2){
        return solve(s1, s2) ? true : false;
    }
    
    static Map<String, Boolean> map = new HashMap<>();


    static boolean solve(String a, String b){

        if(a.equals(b)) return true;
        if(a.length()<=1) return false;

        String tempStr = new StringBuilder().append(a).append(b).toString();
        if(map.containsKey(tempStr)) return map.get(tempStr);

        boolean flag=false;

        for(int k=1; k<=a.length()-1 ;k++){
            
            if(solve(a.substring(0,k), b.substring(a.length()-k))==true && solve(a.substring(k), b.substring(0, a.length()-k))==true){
                flag=true;
                break;
                
            }else if(solve(a.substring(0,k),b.substring(0,k))==true && solve(a.substring(k),b.substring(k))==true) {
                flag=true;
                break;
            }

        }
        map.put(tempStr, flag);
        return flag;
    }
}


