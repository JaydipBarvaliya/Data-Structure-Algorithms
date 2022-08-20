class Solution {
    public int search(int[] nums, int key) {
        
        int minIndex = minEleIndex(nums);
        
        int left = search(nums, 0, minIndex-1, key);
        
        if(left==-1){
            int right = search(nums, minIndex, nums.length-1, key);
            
            if(right==-1){
              return -1;  
            } else{
                return right;
            }
        }else{
            return left;
        }
    }
    
    
    int search(int[] arr, int start, int end, int key){
        
        while(start <= end){
            
            int mid = start + (end-start)/2;
            
            if(arr[mid]==key){
                return mid;
                
            }else if(key < arr[mid]){
                end = mid-1;
                
            }else if(key > arr[mid]){
                start = mid+1;  
            }
        }
        return -1;
    }
    
    
    int minEleIndex(int[] arr){
        
        int len = arr.length;
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
