class Main{
    
	static int ceilSearch(int arr[], int start, int end, int key){
	    
        int ceil = -1;   
        
        while(start<=end){
           
           int mid = start + (end-start)/2;
           
           if(arr[mid] == key) return mid;
           else if(arr[mid] > key){
               ceil = mid;
               end = mid - 1;
           }   
           else if(arr[mid] < key)start = mid + 1;
       }
       return ceil;
	}
	
	public static void main (String[] args)
	{
	int arr[] = {1, 2, 8, 10, 10, 12, 19};
	int n = arr.length;
	int x = 20;
	int index = ceilSearch(arr, 0, n-1, x);
	if(index == -1)
		System.out.println("Ceiling of "+x+" doesn't exist in array");
	else
		System.out.println("ceiling of "+x+" is "+arr[index]);
	}
}
