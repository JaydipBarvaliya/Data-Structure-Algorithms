class Compute {
    
    public long[] printFirstNegativeInteger(long arr[], int n, int k){
        
        
        long[] result = new long[n-k+1];
        List<Long> nagList = new ArrayList<Long>();
        int i = 0;
        int j = 0;
        int index = 0;
        
        while(j<n){
            
            if(arr[j]<0) nagList.add(arr[j]);
            
            if(j-i+1<k){
                j++;
            }else{
                
                if(nagList.size() == 0){
                    result[index] = 0;
                }else{
                    result[index] = nagList.get(0);
                    if(nagList.get(0) == arr[i]) nagList.remove(0);
                }
                
                i++;
                j++;
                index++;
            }
            
        }
        return result;
    }
}
