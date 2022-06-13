class Solution {
    
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        solve(nums, 0, new ArrayList<>(), list);
        return list;
    }
    
    
    void solve(int arr[], int idx, List<Integer> comb, List<List<Integer>> list){
        
        if(idx == arr.length){
            list.add(new ArrayList<>(comb));
            return;
        }
        
        comb.add(arr[idx]);
        solve(arr, idx + 1, comb, list);
        
        comb.remove(comb.size()-1);
        solve(arr, idx + 1, comb, list);
        
        // The above statement are specially defined in hirerchy because of pass by refernece issue for "comb".
    }
}
