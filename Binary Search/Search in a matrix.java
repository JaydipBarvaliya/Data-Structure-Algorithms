class Sol{
    public static int matSearch(int arr[][], int n, int m, int target){
        
        int row=0;
        int col=m-1;
        
        while(row>=0 && row<n && col>=0 && col<m){
            
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
}
