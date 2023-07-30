class Solution {
    static ArrayList<Integer> max_of_subarrays(int arr[], int n, int k) {
        
        
        ArrayList<Integer> result  = new ArrayList<Integer>();

        //if window size is k then just add all of them in the list and return it as all of them are eligible candidates for window size 1
        if(k==1){
            for(Integer ele : arr) result.add(ele);
            return result;        
        } 
        
        int i=0;
        int j=0;
        Deque<Integer> deque = new LinkedList<>();
        
        while(j<n){
            
            
            if(deque.size()==0){
                    deque.add( arr[j]);
            }else{
                
                while(deque.size() > 0 && deque.peekLast() < arr[j]){
                    deque.removeLast();
                }
                deque.add(arr[j]);
                    
                    //so here we are removiug smallest element from the back and till the point where we hit the bigger element..
                    //which means we are formulating the Deque in descending order
            }
                
            
            if(j-i+1 < k){
                j++;
            }else{
                
                result.add(deque.peek());
                
                if(arr[i] == deque.peek()){
                    deque.removeFirst();
                }
                
                i++;
                j++;
            }
        }
        return result;
    }
}











class Solution {
    static ArrayList<Integer> max_of_subarrays(int arr[], int n, int k) {

        int i=0;
        int j=0;

        Deque<Integer> deque = new LinkedList<>();
        List<Integer> ans = new ArrayList<>();

        while(j<n){

            if(deque.size()==0){
                deque.add(arr[j]);
            }else{

                while(deque.size>0 && deque.peekLast<arr[j]) {
                    deque.removeLast();
                }
                deque.add(arr[j]);
            }

            if(j-i+1<k){
                j++;
            }else{
                
                ans.add(deque.peek());
                if( arr[i]  ==  deque.peek() ){
                    deque.removeFirst();
                }
                i++;
                j++;

            }
        }

        return ans;
    }
}



































