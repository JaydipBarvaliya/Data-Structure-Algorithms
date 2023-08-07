class Solution {
    public int[] twoSum(int[] nums, int target) {


        HashMap<Integer, Integer> map = new HashMap();


        for(int i=0; i<nums.length; i++){

            if(map.containsKey(target - nums[i])) return new int[]{map.get(target - nums[i]), i};
            else map.put(nums[i], i);
        }

        return new int[]{-1, -1};        
    }
}


// class Solution {
//     public int[] twoSum(int[] nums, int targetSum) {

//         int left  = 0;
//         int right = nums.length-1;

//         Arrays.sort(nums);

//         while(left < right){

//             int currentSum = nums[left] + nums[right];
//             if(currentSum == targetSum) return new int[]{left, right};
//             if(targetSum > currentSum) left++; 
//             else right--;
                
//         }
//         return new int[]{-1, -1};
//     }
// }


