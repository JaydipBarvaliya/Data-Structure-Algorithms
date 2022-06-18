class Solution {
    static ArrayList<Integer> max_of_subarrays(int arr[], int n, int k) {
        
        
        ArrayList<Integer> result  = new ArrayList<Integer>();
        if(k==1){
            
            for(Integer ele : arr) result.add(ele);
            return result;        
        } 
        
        int i=0;
        int j=0;
        int max = Integer.MIN_VALUE;
        
        Deque<Integer> q = new LinkedList<>();
        
        
        while(j<n){
            
            
            if(q.size()==0){
                    q.add( arr[j]);
                }else{
                    
                    while(q.size() > 0 && q.peekLast() < arr[j]){
                        q.removeLast();
                    }
                    q.add(arr[j]);
                        
                        //so here we are removiug smallest element from the back and till the point where we hit the bigger element..
                        //which means we are formulating the Deque in descending order
                }
                
            
            if(j-i+1 < k){
                j++;
            }else{
                
                result.add(q.peek());
                
                if(arr[i] == q.peek()){
                    q.removeFirst();
                }
                
                i++;
                j++;
            }
        }
        return result;
    }
}
