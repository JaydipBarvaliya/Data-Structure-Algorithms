class Solution {
    public List<String> wordBreak(String str, List<String> wordDict) {
        
        
        
        int len = str.length();
        
        ArrayList<String>[] dp = new ArrayList[len+1];
        
        for(int i=0; i<=len; i++){
            dp[i] = new ArrayList<String>();
        }
        
        for(int i=len-1; i>=0; i--){
            
            for(String word : wordDict){
                
                if( i + word.length() <= len && str.substring(i, i+ word.length()).equals(word)){
                    
                    ArrayList<String> furtherList = new ArrayList<String>(dp[i + word.length()]);
                    
                    if(furtherList.isEmpty()){
                        
                        // System.out.println("Blank at " + i +  " Further Index  " + (i + word.length()));
                        
                        
                        ArrayList<String> existingList = new ArrayList<String>(dp[i]);
                        existingList.add(word);
                        
                        dp[i] = existingList;
                        
                    }else{
                        
                        ArrayList<String> newList = new ArrayList<String>();
                        
                        for(String candidate: furtherList){
                            String newStr = word.concat(" ").concat(candidate);
                            newList.add(newStr.trim());
                        }
                        
                        ArrayList<String> existingList = new ArrayList<String>(dp[i]);
                        existingList.addAll(newList);
                        
                        dp[i] = existingList;
                        
                    }
                    
                    
                    // System.out.println("DP at " + i +" "+ dp[i].toString());
                }
            }
        }
        
        ArrayList<String> finale = new ArrayList<String>();
        
        for(String candidate :dp[0]){
            if(candidate.replaceAll("\\s", "").equals(str)) finale.add(candidate);
        }
        
        return finale;
        
    }
}
