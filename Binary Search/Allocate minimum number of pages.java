class Solution {
    public static int findPages(int[] arr, int totalBooks, int totalStudents){


        if(totalStudents > totalBooks) return -1;
        
        int start = arr[totalBooks-1];
        int end   = IntStream.of(arr).sum();
        int minStressLevelOfAllGroup = Integer.MAX_VALUE;
        
        while(start<=end){
            
            int mid = start + (end-start)/2;
            
            int maxStressLevelPerGroup = isValid(arr, totalBooks, totalStudents, mid);
            
            if(maxStressLevelPerGroup != -1){
                minStressLevelOfAllGroup = Math.min(minStressLevelOfAllGroup, maxStressLevelPerGroup);
                end = mid-1;
            }else{
                start = mid+1;
            }
        }
        
        return minStressLevelOfAllGroup;
    }
    
    public static int isValid(int[] arr, int totalBooks, int totalStudents, int maxStress){
    
        int student = 1;    
        int sum     = 0;
        int maxStressPerGroup = -1;
        
        for(int i=0; i<totalBooks; i++){
            
            sum = sum + arr[i];
            
            if(sum > maxStress){
                maxStressPerGroup = Math.max(maxStressPerGroup, sum - arr[i]);
                student++;
                sum = arr[i];
            }
            
            if(student > totalStudents) return -1;
        }
        
        maxStressPerGroup = Math.max(maxStressPerGroup, sum); // we need to add this statement at last because loop will terminate when I is out of array that time we need to count this case also.
        
        return maxStressPerGroup;
    }
};
