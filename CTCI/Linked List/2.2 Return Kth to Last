int brute(Node head, int index)
    {
    	int length = 1;
        Node runner = head; 
        while(runner.next!=null){
            runner = runner.next;
            length++;
        }
       
        if(index>length){
         
            return -1;
        }
        Node cn = head;
        int count = 1;
        int position = length - index + 1;
        
        while(count != position){
            cn = cn.next;
            count++;
        }
        return cn.data;
    }
	
	
	
	
    int twoPointer(Node head, int index){
    	
    	Node slow = head;
    	Node fast = head;
    	int count = 1;
    	
    	while(count != index){
    	    fast = fast.next;
    	    if(fast == null){
    	        return -1;
    	    }
    	    count++;
    	}
    	
    	while(fast.next != null){
    	    fast = fast.next;
    	    slow = slow.next;
    	}

        return slow.data;    	
    }
	
	
	
	
	
	int recursion(Node head, int k)
    {
    	
        if (head == null) { 
        	return 0; 
          }
          
          int index= recursion(head.next, k) +  1; 
          
          if (index== k) { 
        	System.out.println(k + "th to last node is ::"+ head.data); 
          } 
          
        return index; 
    }
