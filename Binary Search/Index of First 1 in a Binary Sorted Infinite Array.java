import java.util.*;

class GFG {
	
	public static int indexOfFirstOne(int arr[], int low, int high){
	    
		int mid=0;
		int res=-1;
		
		while (low <= high) {
			mid = (low + high) / 2;
	
	        if (arr[mid] == 0){
	            low = mid + 1;
	        }else {//(arr[mid] == 1)
	            res = mid;
				high = mid - 1;
	        }
		}
		return res;
	}

	public static int posOfFirstOne(int arr[]){

		int low  = 0;
		int high = 1;

		while (arr[high] == 0) {
			low  = high;
			high = 2 * high;
		}
		return indexOfFirstOne(arr, low, high);
	}

	public static void main(String[] args){

		int arr[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1 };
		System.out.println("Index = " + posOfFirstOne(arr));
	}
}
