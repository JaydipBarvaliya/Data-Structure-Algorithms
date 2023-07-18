class Solution {
    
    TreeSet<String> set = new TreeSet<String>();
    
    public List<String> letterCasePermutation(String str) {
        
        String op = "";
        solve(str, op);
        return new ArrayList<String>(set);
    }
    
        void solve(String ip, String op){
        
        if(ip.length() == 0){
            set.add(op);
            return;
        }
            
        if(Character.isDigit(ip.charAt(0))){
        solve(ip.substring(1), op + ip.charAt(0));    
        }else{
        solve(ip.substring(1), op + Character.toUpperCase(ip.charAt(0)));
        solve(ip.substring(1), op + Character.toLowerCase(ip.charAt(0)));
        }
        
        //Must remeber that ip.substring(1) must execute after ip.charAt(0) 
        //otherwise null pointer can happen for charAt Method
}
}
