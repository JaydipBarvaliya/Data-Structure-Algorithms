class Solution{
    static long maximumSumSubarray(int k, ArrayList<Integer> list, int n){
        
    long max=0;
    int i=0;
    
    for(i=0; i<k; i++){
         max = max + list.get(i);
    }
    
    //This assignment is very importnant as max and sumOfPrevWin both aim is different. 
    long sumOfPreWin = max;
    
    while(i<n){
        
        sumOfPreWin = sumOfPreWin  + list.get(i) - list.get(i-k);
        max = Math.max(sumOfPreWin, max);
        i++;
    }
    
    return max;
    }
}
