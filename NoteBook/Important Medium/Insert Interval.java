/*
Interval will not be overlapped
case 1 : when interval[end] < newInterval[start],ex-newInterval = [4,8], interval = [1,2]
case 2 : when interval[start] > newInterval[end],ex-newInterval = [4,8], interval = [12,16]
case 3 : when new interval is already added in output list

remaining all cases they will be overlapped
*/
class Solution {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        
        List<int[]> res = new ArrayList<>();
        for(int[] interval: intervals){
            // case3 and case1
            if(newInterval==null || interval[1]<newInterval[0]){
                res.add(interval);
            }else if(interval[0]>newInterval[1]){ // case 2
                res.add(newInterval);
                res.add(interval);
                newInterval = null;
            }else{ // overlapping
                newInterval[0] = Math.min(newInterval[0], interval[0]);
                newInterval[1] = Math.max(newInterval[1], interval[1]);
            }
        }
        if(newInterval!=null) res.add(newInterval);
        return res.toArray(new int[res.size()][]);
    }
}
