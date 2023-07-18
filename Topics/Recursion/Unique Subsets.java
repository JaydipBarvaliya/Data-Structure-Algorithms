class Solution {
    
     List<List<Integer>> result = new ArrayList<>();
     //Don't include static before above statement, otherwise it sucks for no reason.
    
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        backtrack(nums, 0, new ArrayList<Integer>());
        return result;
    }
    
    public void backtrack(int[] nums, int i, ArrayList<Integer> list){
        
        if(i == nums.length){
            result.add(new ArrayList<>(list));
            return;
        }
        
        list.add(nums[i]);
        backtrack(nums, i+1, list);
        list.remove(list.size()-1);
        
        while(i + 1 < nums.length && nums[i] == nums[i+1]) { // to handle duplicates
            i++;
        }
        
        backtrack(nums, i+1, list);
    }
    
}
