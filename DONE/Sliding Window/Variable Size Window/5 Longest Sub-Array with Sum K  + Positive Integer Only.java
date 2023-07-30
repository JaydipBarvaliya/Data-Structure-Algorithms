class Solution{
    
    public static int lenOfLongSubarr (int arr[], int n, int windowSum) {
        
        int i=0;
        int j=0;
        
        int max = Integer.MIN_VALUE;
        int sum=0;
        while(j<n){
            
            sum = sum+ arr[j];
            
            if(sum < windowSum){
                j++;
            }else if(sum == windowSum){
                max = Math.max(max, j-i+1);
                j++;
            }else if(sum > windowSum){
                
                while(sum > windowSum && i<=j){
                    sum = sum - arr[i];
                    i++;
                }
                
                if(sum == windowSum){
                    max = Math.max(max, j-i+1);
                }
                j++;
            }
        }
        
        return max;
    }
}
