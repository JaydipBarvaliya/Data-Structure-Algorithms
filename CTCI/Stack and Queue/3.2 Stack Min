class GfG{
    
    Node head;
    int count = 0;
    
	public void push(int val, Stack<Integer> s){
	    
	    s.push(val);
	    
	    if(head == null){
	        head = new Node(val, null);
	    }
	    
	    if(head != null && val<head.val){
            head = new Node(val, head);
        }
        
        count++;
	    
	}
	
	
	public int min(Stack<Integer> s){
        return head.val;
    }
    
    
    
	
	public int pop(Stack<Integer> s){
	    
	    int temp = s.pop();
	    
	    if(temp == head.val){
	        head = head.next;
	    }
	    
	    count--;
        return temp;                         
	}
	
	
	public boolean isFull(Stack<Integer> stack, int size){
        
        return size == count ? true : false;
	}
	
	
	
	public boolean isEmpty(Stack<Integer>s){
        return s.isEmpty();                                          
	}
	
	private class Node{
	    
	    int val;
	    Node next;
	    
	    
	    private Node(int val, Node current){
	        
	        this.val = val;
	        this.next = current;
	        
	    }
	}
	
	
}
