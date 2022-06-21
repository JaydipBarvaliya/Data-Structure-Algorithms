class GFG{
	int binarySearch(int arr[], int start, int end, int key){
	    
	    while(start<=end){
	        
	        int mid = start + (end-start)/2;
	        
	        if(arr[mid] == key){
	            return mid;
	        }else if(mid >= start && arr[mid-1] == key){
	            return mid-1;
	        }else if(mid <= end && arr[mid+1] == key){
	            return mid+1;
	        }
	        
	        
	        if(key > arr[mid]){
	            start = mid + 2;
	        }else if(key < arr[mid]){
	            end = mid - 2;
	        } 
	    }
		return -1;
	}

	public static void main(String args[]){
		GFG ob = new GFG();
		int arr[] = {10, 3, 40, 20, 50, 80, 70};
		int n = arr.length;
		int x = 90;
		int result = ob.binarySearch(arr, 0, n - 1, x);
		if(result == -1)
			System.out.println("Element is not present in array");
		else
			System.out.println("Element is present at index " + result);
	}
}
