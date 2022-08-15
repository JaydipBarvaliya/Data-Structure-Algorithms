class Solution {
    public boolean canAttendMeetings(int[][] intervals) {
        
         int[][] sorted = intervals.clone();
		 Arrays.sort(sorted, (a,b) -> Integer.compare(a[0], b[0]));
        
        for(int i=0; i<sorted.length-1; i++){
            
            if(sorted[i][1] > sorted[i+1][0]){
                return false;
            }
		}
		return true;
    }
}
