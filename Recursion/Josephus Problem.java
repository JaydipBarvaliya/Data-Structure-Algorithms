class Solution {
    
    List<Integer> list = new ArrayList<Integer>();
    
    int safePos(int total, int kth) {
        
        for(int i = 1; i <= total; i++)
            list.add(i);

        return solve(0, total, kth);
    }
    
    int solve(int start, int total, int kth) {

        if(total==1) return list.get(0);
        
        int exec_pos = (start + kth-1) % total;
        list.remove(exec_pos);
        
        int next_pos = exec_pos % total;
        
        return solve(next_pos, total-1, kth);
    }
};
