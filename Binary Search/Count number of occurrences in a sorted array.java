class Solution {
    int count(int[] arr, int len, int key) {

        int firstOccurance = firstOccurance(arr, key, 0, len-1);
        
        if(firstOccurance!=-1){
                
                int lastOccurance = lastOccurance(arr, key, 0, len-1);
                return lastOccurance - firstOccurance + 1;
        }else{
            return 0;
        }
    }
    
    int firstOccurance(int arr[], int key, int start, int end){
        
        int result = -1;
        
        while(start <= end){
            
            int mid = start + (end-start)/2;
            
            if(arr[mid]==key){
                result = mid;
                end = mid-1;
            }
            else if(key<arr[mid])   end   = mid-1;
            else if(key > arr[mid]) start = mid+1;
        }
        
        return result;
    }
    
    int lastOccurance(int arr[], int key, int start, int end){
        
        int result = -1;
        
        while(start <= end){
            
            int mid = start + (end-start)/2;
        
            if(arr[mid]==key){
                result = mid;
                start= mid + 1;
            }
            
            else if(key<arr[mid])  end   = mid-1;
                
            else if(key>arr[mid])  start = mid+1;
            
        }
        return result;
    }
}
