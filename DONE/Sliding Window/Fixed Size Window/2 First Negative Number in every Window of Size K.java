class Compute {
    
    public long[] printFirstNegativeInteger(long arr[], int n, int k){
        
        int i = 0;
        int j = 0;
        int index = 0;
        
        List<Long> nagList = new ArrayList<Long>();
        long[] result = new long[n-k+1];
        
        
        while(j<n){
            
            
            if(arr[j] < 0){
                nagList.add(arr[j]);
            }
            
            if(j-i+1<k){
                j++;
            }else{
                
                if(nagList.size()==0){
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

//We are holding the all nagative value in nagative list and everytime window move we check the nagative list if it has any value then pick the
//first value in nagative list otherwise put the 0 in result array and before moving the window just remove the first element in list if applicable