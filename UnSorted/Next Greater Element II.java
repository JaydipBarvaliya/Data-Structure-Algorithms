class Solution {
    public int[] nextGreaterElements(int[] nums) {



        Stack<Integer> stack = new Stack<>();

        for(int i=nums.length-1; i>=0; i--){
            stack.push(nums[i]);
        }


         for(int i=nums.length-1; i>=0; i--){

            int holderNumber = nums[i];
            while(!stack.isEmpty() && stack.peek()<=nums[i]){
                stack.pop();
            }

            if(!stack.isEmpty()){
                nums[i] = stack.peek();
            }else{
                nums[i] = -1;
            }

            stack.push(holderNumber);
            }

            return nums;
    }
}