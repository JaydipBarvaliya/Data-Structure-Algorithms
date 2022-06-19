class Solution {
    public String minWindow(String str, String t) {
        
		
		HashMap<Character,Integer> map = new HashMap<>();
        
		for(int k=0 ; k<t.length(); k++) 
            map.put(t.charAt(k), map.getOrDefault(t.charAt(k), 0) + 1);
		        

		int count = map.size();
		int min = Integer.MAX_VALUE;
		String ans = "";

		int i=0;
        int j=0;
        int n=str.length();

		while( j<n) {
            
			char ch=str.charAt(j);
            
			if(map.containsKey(ch)) {
			
                map.put(ch, map.get(ch) - 1 );
                
				if(map.get(ch) == 0){
					count-- ; 
				}			
			}
            
            
			if(count == 0 ) {
                
				//calculate ans
                if(j-i+1 < min) {
					ans = str.substring(i,j+1);
					min =  j-i+1;
				}
                
                //reducing the window from left till we are stick with our answer count=0
				while(count == 0 && i<=j) {
                    
					char c = str.charAt(i);
                    
					if(map.containsKey(c)) {
						if(map.get(c)==0) {
							count++;
						}
						map.put(c, map.get(c)+1);
					}
                    
					i++;  // Here after processing the current variable using above block we are increasing the value of I.
                // Sp afterwards we can move to next reduced window and we can check that ...without doing process on it.. is it eligible for candidate windows.
                // and that checking we are doing in below block.
					if(count == 0 && min>j-i+1) {
						ans = str.substring(i,j+1);
						min =  j-i+1;
					}
				}
			}
            j++;
		}
		return ans;
    }
}
