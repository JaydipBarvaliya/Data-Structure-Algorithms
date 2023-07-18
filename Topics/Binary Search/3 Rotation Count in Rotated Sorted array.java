class Solution {
    int findKRotation(int arr[], int len) {
        
        int start = 0;
        int end = len-1;
        
        while(start<=end){
            
            //There is just a small bit of logic which needs to be added
            //to make it sustainable for all cases.
            //Given we are choosing the unsorted array every time. 
            //It might happen we find a array sorted on both the sides 
            //of the middle.(array with 3 elements [1 2 3] ) 
            //For eg. in [4,5,6,7,0,1,2] 
            //After the first iteration the arr[middle] comes out as 1.
            //Now the array [0,1,2] is sorted on both sides. To deal with
            //this add another condition if (arr[first] < a[last]) return arr[first]; 
            
            if (arr[start]<=arr[end]) return start;
            
            int mid = start + (end-start/2);
            
            int pre =  (mid - 1  + len) % len;
            int next = (mid + 1 ) % len;
            
            if(arr[mid]<=arr[pre] && arr[mid]<=arr[next]){
                return mid;
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
