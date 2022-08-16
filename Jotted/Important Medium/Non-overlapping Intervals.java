class Solution {
    public int eraseOverlapIntervals(int[][] intervals) {
        
        Arrays.sort(intervals,(a,b)->Integer.compare(a[1],b[1]));
        
        int[] curr = intervals[0];    
        int count = 0;
        
        for(int i=1;i<intervals.length;i++){
            
            if (curr[1] > intervals[i][0])
                count++;
            else
                curr = intervals[i];
        }
        
        return count;
        
    }
}