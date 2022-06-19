class Solution {
    public int subarraySum(int[] arr, int windowSum) {
        
        int sum=0;
        int count=0;
        int remainingSum=0;
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        
        map.put(0, 1);
        
        for(int curr: arr){
            
            sum = sum + curr;
            remainingSum = sum - windowSum;
            
            if(map.containsKey(remainingSum)){
                count = count + map.get(remainingSum);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
            
            
        }
        
        return count;
    }
}
