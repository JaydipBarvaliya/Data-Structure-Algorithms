{          
		  if(head == null){
             return head;
            }
            
        Node slow = head;
        Node fast = head.next;
        
        while(fast!=null){
            
            if(slow.data == fast.data){
                slow.next = fast.next;
                fast= fast.next;
            }else{
                slow = slow.next;
                fast= fast.next;
            }
        }
        return head;
}
