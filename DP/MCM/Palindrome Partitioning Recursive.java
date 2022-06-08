class Solution{
    static int palindromicPartition(String str){
        
        return solve(str, 0, str.length()-1);
    }
    
    static int solve(String str, int i, int j){
        
        if( i>=j || isPalindrome(str, i, j) ) return 0;
        
        int min = Integer.MAX_VALUE;
        for(int k=i; k<j; k++){
            int tempAns = solve(str, i, k) + solve(str, k+1, j) + 1;
            min  = Math.min(min, tempAns);
        }
        return min;
    }
    
     static boolean isPalindrome(String str, int i, int j){
        
        while(i < j){
          if(str.charAt(i) != str.charAt(j))
            return false; 
          i++;
          j--;
        }
        
        return true;
      }
}
