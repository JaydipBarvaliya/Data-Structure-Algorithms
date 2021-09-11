

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


//adding data in Hashset and then check if element is exist or not and based on that iterate entire Linked List--> GFG website
class Solution
{
    
 HashSet<Integer> visited = new HashSet<Integer>();

    public Node removeDuplicates(Node head) 
    {
     
     Node pre  = head;
     Node curr = head;
     
     while(curr != null){
         if(visited.contains(curr.data)){
             pre.next = curr.next;
         }else{
             visited.add(curr.data);
             pre = curr;
         }
         curr = curr.next;
     }
     return head;
    }
}
