class Solution {
    public int findPeakElement(int[] arr) {
        
        int len = arr.length;        
	    int start  = 0;
	    int end    = len-1;
	    int result = 1;
        
        if(len==1) return 0;
	    
	    while(start <= end){
           
           int mid = start + (end-start)/2;
           
           if(mid>0 && mid<len-1){

                if(arr[mid] > arr[mid+1] && arr[mid] > arr[mid-1]) return mid;
                else if( arr[mid] < arr[mid+1]) start = mid+1;
                else  end = mid-1;
              

           }else if(mid == 0){
               return arr[0] > arr[1] ? 0 : 1;
               
           }else if(mid == len-1) {
               return arr[len-1] > arr[len-2] ? len-1 : len-2;
           }
	    }
	    return result;
    }
}
