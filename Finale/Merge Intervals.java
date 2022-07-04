class Solution {
    public int[][] merge(int[][] intervals) {
        
        int[][] sorted = intervals.clone();
		Arrays.sort(sorted, (a,b) -> Integer.compare(a[0], b[0]));
		
		List<int[]> list = new ArrayList<int[]>();
		int[] curr = sorted[0];
		list.add(curr);
		
		for(int[] running : sorted){
			if(curr[1] >= running[0]){
				curr[1] = Math.max(curr[1], running[1]);
			}else{
				curr = running;
				list.add(curr);
			}
		}
        int[][] resArr = new int[list.size()][];
		return list.toArray(resArr);
    }
}
