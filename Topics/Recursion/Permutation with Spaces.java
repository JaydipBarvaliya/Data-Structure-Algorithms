class Solution{
    
    
    TreeSet<String> set = new TreeSet<String>();
    
    ArrayList<String> permutation(String str){
        
        String op = "";
        
        op  = op + str.charAt(0);
        str = str.substring(1);
        
        solve(str, op);
        
        return new ArrayList<String>(set);
    }
    
    void solve(String ip, String op){
        
        if(ip.length() == 0){
            set.add(op);
            return;
        }
        
        solve(ip.substring(1), op + ip.charAt(0));
        solve(ip.substring(1), op + " " + ip.charAt(0));
        
        //Must remeber that ip.substring(1) must execute after ip.charAt(0) 
        //otherwise null pointer can happen for charAt Method
}
    
}
