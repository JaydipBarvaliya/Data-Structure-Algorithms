class Solution{
	
	public int peakElement(int[] arr, int len){
	    
	    if(len==1) return 1;
	    int start  = 0;
	    int end    = len-1;
	    int result = 0;
	    
	    while(start<=end){
           
           int mid = start + (end-start)/2;
           
           if(mid>0 && mid<len-1){

               if(arr[mid] > arr[mid+1] && arr[mid] > arr[mid-1]){
                   return mid;
               }else if(arr[mid+1]> arr[mid]){
                   start = mid+1;
               }else if(arr[mid-1] > arr[mid]){
                   end = mid-1;
               }

           }else if(mid == 0){

               if(arr[0] > arr[1]){
                   return 0;
               }else{
                   return 1;
               }

           }else if(mid == len-1) {
               if(arr[len-1] > arr[len-2]){
                   return len-1;
               }else{
                   return len-2;
               }
           }
	    }
	    
	    return result;
    }
}
