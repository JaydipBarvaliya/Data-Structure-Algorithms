class Solution{
    static int countWays(int N, String str){
        return solve(str.toCharArray(), 0, N-1, true);
    }
    
    static int solve(char[] str, int i, int j, boolean isTrue){
        
        if(i>j) return 0;
        
        if(i==j && str[i] == 'T' && isTrue == true)  return 1;
        if(i==j && str[i] == 'F' && isTrue == false) return 1;
        
        int ans = 0;
        for(int k=i+1; k<=j-1; k=k+2){
            
            int lt = solve(str, i, k-1, true);
            int lf = solve(str, i, k-1, false);
            
            int rt = solve(str, k+1, j, true);
            int rf = solve(str, k+1, j, false);
            
            if(str[k] == '&'){
                if(isTrue == true){
                    ans = ans + (lt*rt);   
                }else{
                    ans = ans + (lf*rf) + (lt*rf) + (lf*rt);
                }
            }else if(str[k] == '|'){
                if(isTrue == true){
                    ans = ans + (lt*rt) + (lt*rf) + (lf*rt);   
                }else{
                    ans = ans + (lf*rf);
                }
            }else if(str[k] == '^'){
                if(isTrue == true){
                    ans = ans + (lt*rf) + (lf*rt); 
                }else{
                    ans = ans + (lt*rt) + (lf*rf);
                }
            }
        }
        
        return ans;
    }
    
    
}
