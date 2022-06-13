class Solution {
    static void getSS(int arr[], int idx, List<Integer> comb, List<List<Integer>> list){
        
        if(idx == arr.length){
            list.add(new ArrayList<>(comb));
            return;
        }
        
        comb.add(arr[idx]);
        getSS(arr, idx + 1, comb, list);
        
        
        comb.remove(comb.size()-1);
        getSS(arr, idx + 1, comb, list);
        
        // The above statement are specially defined in hirerchy because of pass by refernece issue for "comb".
        
        
    }
    
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> list = new ArrayList<>();
        
        getSS(nums, 0, new ArrayList<>(), list);
            
        return list;
    }
}
