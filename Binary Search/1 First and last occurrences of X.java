class Solution{
    public ArrayList<Integer> firstAndLast(int arr[], int len, int key){
        
        ArrayList<Integer> list = new ArrayList<Integer>();
        
        int FO = firstOccurance(arr, key, 0, len-1);
        
        if(FO!=-1){
            list.add(FO);
            int LO = lastOccurance(arr, key, 0, len-1);
            list.add(LO);
        }else{
            list.add(-1);
        }
        return list;
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
