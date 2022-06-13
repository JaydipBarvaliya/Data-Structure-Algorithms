class Solution {
    
    
    TreeSet<String> set = new TreeSet<String>();

    public List<String> find_permutation(String str) {
        
        permute(str, 0, str.length()-1);
        
        return new ArrayList<String>(set);
    }
    
    void permute(String str, int l, int r){
        
        if(l==r){
            set.add(str);
            return;
        }
        
        for(int i=l; i<=r; i++){
            str = swap(str, l, i);
                  permute(str, l+1, r);
            str = swap(str, l, i);
        }
    }
    
    String swap(String str, int i, int j ){
        
        char[] charArray = str.toCharArray(); 
        
        char temp = charArray[i] ; 
        charArray[i] = charArray[j]; 
        charArray[j] = temp; 
        
        return String.valueOf(charArray);
    }
}
