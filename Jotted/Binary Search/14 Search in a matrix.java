class Sol{
    public static int matSearch(int arr[][], int n, int m, int target){
        
        int row=0;
        int col=m-1;
        
        //if we add following condition in below statment && col<m
        //but we actually not requierd it as col is always decreased..so it will never
        //hit this condition...
        
        while(row>=0 && row<n && col>=0){  
            
            if(arr[row][col] == target){
                 return 1;  
              
            } else if(target < arr[row][col]){
                 col--;
                
            } else if(target > arr[row][col]){
                 row++;
            }
        }
        return 0;
    }
