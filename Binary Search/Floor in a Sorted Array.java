class Solution{
    static int findFloor(long arr[], int len, long x){
        
        int[] intArray = Arrays.stream(arr).mapToInt(i -> (int) i).toArray();
        int key = (int)x;
        int start = 0;
        int end = len-1;
        int maxFloor = -1;   
        
        while(start<=end){
           
           int mid = start + (end-start)/2;
           
           if(intArray[mid] == key) return mid;
           else if(intArray[mid] > key) end = mid - 1;  
           else if(intArray[mid] < key){
               maxFloor = mid;
               start = mid + 1;
           }
       }
       return maxFloor;
    }
}
