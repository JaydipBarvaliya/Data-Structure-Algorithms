class Solution {
    public int findMin(int[] arr) {
        
        int len = arr.length;
        int start = 0;
        int end = len-1;
        
        while(start<=end){

            
            if (arr[start]<=arr[end]) return arr[start];
            
            int mid = start + (end-start/2);
            
            int pre =  (mid - 1  + len) % len;
            int next = (mid + 1 ) % len;
            
            if(arr[mid]<=arr[pre] && arr[mid]<=arr[next]){
                return arr[mid];
            }
            
            if(arr[start] <= arr[mid]){
                start = mid + 1;
            }else {
                end = mid - 1;
            }
        }
            
     return -1 ; 
        
        
    }
}