class Solution {
    
    private int len;

    public int findInMountainArray(int target, MountainArray mountainArr) {
        
        this.len = mountainArr.length();

        int peakEleIndex = findPeakEleIndex(mountainArr);
        int ans = binSearchAscend(0, peakEleIndex, mountainArr, target);
        
        return ans == -1 ? binSearchDescend(peakEleIndex+1, len-1, mountainArr, target) : ans;

    }

    int findPeakEleIndex(MountainArray mountainArr) {
             int start = 0;
             int end   = len - 1;
        
            while(start < end){

             int mid = start + (end-start)/2;
                
             int cur = mountainArr.get(mid);
             int next = mountainArr.get(mid+1);
             
             if (next > cur) { // increasing
                 start = mid+1;
             }else {
                 end = mid;
             }
         }

         return start;
    }
    
        int binSearchAscend(int start, int end, MountainArray mountainArr, int target) {
            
            while(start<=end){
                
                int mid = start + (end-start)/2;
                int midEle = mountainArr.get(mid);
                
                 if(midEle == target) return mid;  
                 else if (target < midEle) end   = mid - 1;  
                 else  start = mid + 1; //if (target > midEle ) 
            }
            return -1;
        }
    
        int binSearchDescend(int start, int end, MountainArray mountainArr, int target) {

                while(start<=end){

                    int mid = start + (end-start)/2;
                    int midEle = mountainArr.get(mid);
                    
                    if(midEle == target) return mid;  
                    else if (target<midEle) start   = mid + 1;  
                    else  end = mid - 1; //if (target > midEle )
                }
                return -1;
            }
}
