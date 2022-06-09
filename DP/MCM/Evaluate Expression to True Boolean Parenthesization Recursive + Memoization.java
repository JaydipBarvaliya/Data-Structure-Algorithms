class Solution{
    
    static int countWays(int N, String str){
        return solve(str.toCharArray(), 0, N-1, true);
    }
    
    static Map<String, Integer> map = new HashMap<String, Integer>();
    
    static int solve(char[] str, int i, int j, boolean isTrue){
        
        if(i>j) return 0;
        
        if (i == j){
            if (isTrue == true){
                return (str[i] == 'T') ? 1 : 0;
            }else if(isTrue == false){
                return (str[i] == 'F') ? 1 : 0;
            }
        }
        
        String tempStr = new StringBuilder().append(str[i]).append(str[j]).append(isTrue).toString();
        if(map.containsKey(tempStr)) return map.get(tempStr);
        
        int ans = 0;
        for(int k=i+1; k<=j-1; k=k+2){
            
            int leftTrue = solve(str, i, k-1, true);
            int leftFalse = solve(str, i, k-1, false);
            
            int rightTrue = solve(str, k+1, j, true);
            int rightFalse = solve(str, k+1, j, false);
            
            if(str[k] == '&'){
                if(isTrue == true){
                    ans = ans + (leftTrue*rightTrue);   
                }else{
                    ans = ans + (leftFalse*rightFalse) + (leftTrue*rightFalse) + (leftFalse*rightTrue);
                }
            }else if(str[k] == '|'){
                if(isTrue == true){
                    ans = ans + (leftTrue*rightTrue) + (leftTrue*rightFalse) + (leftFalse*rightTrue);   
                }else{
                    ans = ans + (leftFalse*rightFalse);
                }
            }else if(str[k] == '^'){
                if(isTrue == true){
                    ans = ans + (leftTrue*rightFalse) + (leftFalse*rightTrue); 
                }else{
                    ans = ans + (leftTrue*rightTrue) + (leftFalse*rightFalse);
                }
            }
        }
        
        map.put(tempStr, ans);
        return ans;
    }
}
