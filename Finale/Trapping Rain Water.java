class Solution {
    public int trap(int[] arr) {
    
      int len = arr.length;  
        
      int[] LMaxArr = new int[len];
      int[] RMaxArr = new int[len];
        
      int Lmax = 0; 
      int Rmax = 0;
        
        
        for(int i=0; i<len; i++){
            if(arr[i] > Lmax) Lmax = arr[i];
            LMaxArr[i] = Lmax;
            
        }
      
       for(int j=len-1; j>=0; j--){
           if(arr[j] > Rmax) Rmax = arr[j];
           RMaxArr[j] = Rmax;
        }
        
        
        int sum = 0;
        for(int n=0; n<len; n++){
            int capable = Math.min(LMaxArr[n], RMaxArr[n]);
            sum = sum + (capable - arr[n] );
        }
        
        return sum;
    }
}
