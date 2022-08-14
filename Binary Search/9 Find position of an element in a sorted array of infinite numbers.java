class Test{
   
    static int binarySearch(int arr[], int left, int right, int x){
	    
	    
        if (right>=left){

            int mid = left + (right - left)/2;
		
            if (arr[mid] == x)
                return mid;

            if (x < arr[mid])
                return binarySearch(arr, left, mid-1, x);

	    if (x > arr[mid])
		return binarySearch(arr, mid+1, right, x);
        }
        return -1;
    }

    static int findPos(int arr[],int key){
	    
        int low = 0, high = 1;
        int val = arr[0];

        while (val < key){

            low = high;     // store previous high

	    //check that 2*h doesn't exceeds array length to prevent ArrayOutOfBoundException
            if(2*high < arr.length-1)		    
	        high = 2*high;
            else
                high = arr.length-1;
	    		
            val = arr[high]; // update new val
        }
 
        // at this point we have updated low and high indices, thus use binary search between them
        return binarySearch(arr, low, high, key);
    }
	
	
	
	
	
	
	
    public static void main(String[] args){
        int arr[] = new int[]{3, 5, 7, 9, 10, 90, 100, 130, 140, 160, 170};
        
	int ans = findPos(arr,10);	
        if (ans==-1)
            System.out.println("Element not found");
        else
            System.out.println("Element found at index " + ans);
    }
}
