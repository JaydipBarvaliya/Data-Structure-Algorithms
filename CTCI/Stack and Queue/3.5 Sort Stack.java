class GfG{
	public Stack<Integer> sort(Stack<Integer> s1)
	{
	    
        Stack<Integer> s2 = new Stack<Integer>();
        
        while(!s1.isEmpty()){
            
            if(s2.isEmpty()){
                
                s2.push(s1.pop());
            }else{
                
                int temp = s1.pop();
                if(temp > s2.peek()){
                    s2.push(temp);
                }else{
                    int count1 = 0;
                    while(!s2.isEmpty() && (temp < s2.peek()) ){
                        s1.push(s2.pop());
                        count1++;
                    }
                    
                    
                    s2.push(temp);
                    
                    
                    int count2 = 0;
                    while(count1 != count2){
                        s2.push(s1.pop());
                        count2++;
                    }
                }
            }
        }
    return s2;
	}
}


--------------------------------------------------------------------


class GfG{
    	public Stack<Integer> sort(Stack<Integer> s1)
	{
	    
        Stack<Integer> s2 = new Stack<Integer>();
        
        while(!s1.isEmpty()){
            
            int temp = s1.pop();
            
            while(!s2.isEmpty() && temp < s2.peek() ){
                s1.push(s2.pop());
            }
            s2.push(temp);
        }
    return s2;
	}
}


------------------------------------------------------------------------
