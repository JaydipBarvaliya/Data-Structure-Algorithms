

// With Buffer
{
	if(head == null){
             return head;
	}
         
         boolean[] arr = new boolean[65535];
         Node currentNode = head;
         arr[currentNode.data] = true;
         
         while(currentNode.next != null){
                 if(!arr[currentNode.next.data]){
                 arr[currentNode.next.data] = true;
                 currentNode = currentNode.next;    
             }else{
                 currentNode.next = currentNode.next.next;
             }
         }
 
 return head;
 }


// Without Buffer
{
 
	if(head == null){
             return head;
        }
        Node currentNode = head;
        
        while(currentNode != null){
            Node runner  = currentNode;
            while(runner.next != null){
                if(currentNode.data == runner.next.data){
                    runner.next = runner.next.next;
                }else{
                    runner = runner.next;
                }
            }
            currentNode = currentNode.next;
        }
	return head;
}	
