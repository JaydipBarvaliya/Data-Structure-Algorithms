// Time : nLogn -- because of sorting  + n^2 --nested two loop -- one for and other while -- However n^2 is thebigger one 
// Space -- O(n) the space in which we store the result otherwise we did not use any other space except the extra memory used by the sorting algorithm

class Solution {
    public List<List<Integer>> threeSum(int[] nums) {

        Arrays.sort(nums);

        List<List<Integer>> list = new ArrayList<>();

        for(int i=0; i<nums.length; i++){

            if(i>0 && nums[i] == nums[i-1]) continue;

            int j = i+1;
            int k = nums.length-1;

            while(j<k){
                
                int sum = nums[i] + nums[j] + nums[k];
                
                if(sum == 0 ){
                    list.add(Arrays.asList(nums[i],nums[j],nums[k]));
                    j++;
                    k--;
                    while(j<k && nums[j] == nums[j-1]) j++;
                    while(j<k && nums[k] == nums[k+1]) k--;

                }else if(sum < 0 ){
                    j++;
                }else{
                    k--;
                }
            }
        }

        return list;
        
    }
}