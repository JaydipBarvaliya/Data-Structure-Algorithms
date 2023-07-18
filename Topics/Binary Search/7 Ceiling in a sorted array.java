class Main{
    
	static int ceilSearch(int arr[], int start, int end, int key){
	    
        int ceil = -1;   
        
        while(start<=end){
           
           int mid = start + (end-start)/2;
           
           if(arr[mid] == key) return mid;
           else if(arr[mid] > key){
               ceil = mid;
               end = mid - 1;
           }   
           else if(arr[mid] < key)start = mid + 1;
       }
       return ceil;
	}
}
