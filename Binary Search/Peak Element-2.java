class Solution{
    
	public int peakElement(int[] arr,int n){
	    
     int start = 0;
     int end   = n-1;

     while(start<end){
         
         int mid=(start+end)/2;
         
         if(arr[mid] < arr[mid+1]){
             start = mid+1;
         }else {
             end = mid;
         }
     }
     
     return start;
    }
}
