class Solution {
    public ListNode removeNthFromEnd(ListNode head, int kth) {
        Package packaging =  fun(head, kth);
		return packaging.list;
    }
    
    public static Package fun(ListNode head, int kth){
		
		if(head == null) return new Package();
		
		Package packaging = fun(head.next, kth);
		packaging.level += 1;
		
		if(packaging.level == kth){
			return packaging;
		}else{
			ListNode currentNode = new ListNode(head.val);
			currentNode.next = packaging.list;
			packaging.list = currentNode;
			return packaging;
		}
	}
	

	static class Package {
		int level;
		ListNode list;
	}
    
    
    
}





public ListNode removeNthFromEnd(ListNode head, int n) {
    
    ListNode start = new ListNode(0);
    ListNode slow = start, fast = start;
    start.next = head;
    
    
    for(int i=1; i<=n+1; i++)   {
        fast = fast.next;
    }
    
    while(fast != null) {
        slow = slow.next;
        fast = fast.next;
    }

    slow.next = slow.next.next;
    return start.next;
}