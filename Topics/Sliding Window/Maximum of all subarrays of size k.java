class Solution {
    static ArrayList<Integer> max_of_subarrays(int arr[], int n, int k) {
        
        
        ArrayList<Integer> result  = new ArrayList<Integer>();
        if(k==1){
            for(Integer ele : arr) result.add(ele);
            return result;        
        } 
        
        int i=0;
        int j=0;
        Deque<Integer> queue = new LinkedList<>();
        
        while(j<n){
            
            
            if(queue.size()==0){
                    queue.add( arr[j]);
            }else{
                
                while(queue.size() > 0 && queue.peekLast() < arr[j]){
                    queue.removeLast();
                }
                queue.add(arr[j]);
                    
                    //so here we are removiug smallest element from the back and till the point where we hit the bigger element..
                    //which means we are formulating the Deque in descending order
            }
                
            
            if(j-i+1 < k){
                j++;
            }else{
                
                result.add(queue.peek());
                
                if(arr[i] == queue.peek()){
                    queue.removeFirst();
                }
                
                i++;
                j++;
            }
        }
        return result;
    }
}
